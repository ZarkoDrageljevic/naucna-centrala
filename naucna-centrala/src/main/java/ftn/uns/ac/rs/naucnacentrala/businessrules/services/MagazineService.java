package ftn.uns.ac.rs.naucnacentrala.businessrules.services;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Magazine;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Paper;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.ReviewerDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.MagazineRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MagazineService {

    private final PaperRepository paperRepository;
    private final MagazineRepository magazineRepository;

    public Magazine getMagazine(Long magazineId) {
        return magazineRepository.getOne(magazineId);
    }

    public MagazineDto getMagazineWithDetails(Long magazineId) {
        Magazine magazine = magazineRepository.getOne(magazineId);
        return new MagazineDto(magazine);
    }

    public void subscribeToMagazine(Magazine magazine, ApplicationUser applicationUser) {

        magazine.getSubscriptions().add(applicationUser);
        magazineRepository.save(magazine);

    }

    public List<MagazineDto> getAll() {

        return magazineRepository.findAll().stream().map(MagazineDto::new).collect(Collectors.toList());
    }

    public String getExecutiveEditorOfMagazine(Long magazineId) {
        Magazine magazine = magazineRepository.getOne(magazineId);
        return magazine.getEditor().getUsername();
    }

    public List<ReviewerDto> getReviewers(long magazineId, long paperId, String scienceField) {
        Magazine magazine = magazineRepository.getOne(magazineId);
        Paper paper = paperRepository.getOne(paperId);
        if (scienceField == null || scienceField.equals("")) {

            return magazine.getReviewers().stream().map(ReviewerDto::new).collect(Collectors.toList());
        } else {
            return magazine.getReviewers().stream()
                    .filter(reviewer ->
                            reviewer.getScienceFields().contains(paper.getScientificField()))
                    .map(ReviewerDto::new)
                    .collect(Collectors.toList());
        }
    }
}
