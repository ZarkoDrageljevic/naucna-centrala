package ftn.uns.ac.rs.naucnacentrala.businessrules.services;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.*;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.PaperSubmissionDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.ReviewDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.ReviewerDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.ScientificFieldDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.CoAuthorRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.PaperRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ReviewerRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ScientificFieldRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.BadRequestException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final CoAuthorRepository coAuthorRepository;
    private final ReviewerRepository reviewerRepository;


    public void uploadFile(MultipartFile file, String fileName) {
        try {
            if (!file.isEmpty() && file.getBytes().length >= 5242880) {
                log.info("file size is " + Arrays.toString(file.getBytes()));
            }
            FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/uploadedfiles/" + fileName);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        } catch (Exception Exp) {
            log.info("Upload file failure");

        }
    }

    public Paper saveSubmitedFile(PaperSubmissionDto paperSubmissionDtos, ApplicationUser applicationUser) {

        Paper paper = new Paper();
        paper.setAuthor(applicationUser);
        paper.setTitle(paperSubmissionDtos.getTitle());
        paper.setKeywords(paperSubmissionDtos.getKeywords());
        paper.setPaperAbstract(paperSubmissionDtos.getPaperAbstract());
        paper.setScientificField(toScienceField(paperSubmissionDtos.getScienceField()));
        paper.setCoauthors(paperSubmissionDtos.getCoauthors().stream().map(CoAuthor::new).map(coAuthorRepository::save).collect(Collectors.toList()));
        return paperRepository.save(paper);
    }

    private ScientificField toScienceField(ScientificFieldDto scientificFieldDto) {
        return scientificFieldRepository.findByTitle(scientificFieldDto.getTitle());
    }

    public Boolean checkIfExists(Long paperId) {
        return paperRepository.getOne(paperId) != null;
    }

    public Paper findById(long paperId) {
        return paperRepository.getOne(paperId);
    }

    public void delete(long parseLong) {
        paperRepository.deleteById(parseLong);
    }

    public Paper getPaperWithDetails(long parseLong) {
        Paper paper = paperRepository.getOne(parseLong);
        paper.getScientificField();
        return paper;
    }

    public Paper submitReviewers(long parseLong, List<ReviewerDto> reviewers) {
        Paper paper = paperRepository.getOne(parseLong);

        if (!paper.getReviewers().isEmpty()) {
            if (reviewers.size() != 1) {
                throw new BadRequestException("There is spot for one reviewer");
            }
            ReviewerDto reviewerDto = reviewers.get(0);
            if (paper.getReviewers().stream().anyMatch(r -> r.getId() == reviewerDto.getId())) {
                throw new BadRequestException("Reviewer Already present");
            }
        } else if (reviewers.size() < 2) {
            throw new BadRequestException("Two reviewers needed for paper");
        }

        paper.setReviewers(reviewers.stream().map(reviewerDto -> reviewerRepository.getOne(reviewerDto.getId())).collect(Collectors.toList()));
        return paperRepository.save(paper);
    }

    public void removeReviewer(long paperId, ApplicationUser reviewer) {

        Paper paper = paperRepository.getOne(paperId);
        paper.getReviewers().remove(reviewer);
        paperRepository.save(paper);
    }

    public void savePaperAndReview(long parseLong, Review review) {
        Paper paper = paperRepository.getOne(parseLong);
        paper.getReviews().add(review);
        paperRepository.save(paper);
    }

    public List<ReviewDto> getReviewes(long parseLong) {
        Paper paper = paperRepository.getOne(parseLong);
        return paper.getReviews().stream().map(ReviewDto::new).collect(Collectors.toList());
    }

    public void removeOldReviews(long parseLong) {
        Paper paper = paperRepository.getOne(parseLong);
        paper.getReviews().clear();
        paperRepository.save(paper);
    }
}
