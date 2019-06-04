package ftn.uns.ac.rs.naucnacentrala.businessrules.services;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.CoAuthor;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Paper;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ScientificField;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.PaperSubmissionDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.ScientificFieldDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.CoAuthorRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.PaperRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ScientificFieldRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final CoAuthorRepository coAuthorRepository;


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
}
