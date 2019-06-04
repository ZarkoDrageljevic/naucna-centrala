package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.*;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ApplicationUserRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.EditorRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ReviewerRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.MagazineService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {


    private final ProcessService processService;
    //    private final ApplicationUserService applicationUserService;
    private final MagazineService magazineService;

    private final EditorRepository editorRepository;
    private final ReviewerRepository reviewerRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final PaperService paperService;

    @GetMapping(value = "/magazine")
    ResponseEntity getMagazine(){
        return ResponseEntity.ok().body(magazineService.getMagazine(1L));
    }

    @GetMapping(value = "/paper")
    ResponseEntity getPaper(){
        return ResponseEntity.ok().body(paperService.findById(5L));
    }


    @GetMapping(value = "/dodaj")
    ResponseEntity asd() {

//        ApplicationUser applicationUser = applicationUserService.findByUsername("Zarko");
//        Magazine magazine = magazineService.getMagazine(1L);
//        magazineService.subscribeToMagazine(magazine,applicationUser);

        applicationUserRepository.save(createAuthor());
        editorRepository.save(createeditor());
        reviewerRepository.save(createRewever());

        return ResponseEntity.ok().build();

    }

    private ApplicationUser createAuthor() {
        ApplicationUser appUser = new ApplicationUser();
        appUser.setFirstname("Nikola");
        appUser.setLastname("Tesla");
        appUser.setUsername("Nidza");
        appUser.setEmail("Emai@mailinator.com");
        appUser.setAddress("adresa");
        appUser.setPassword("123");
        appUser.setRole(UserRole.USER);
        appUser.setVerified(true);
        return appUser;
    }

    private Editor createeditor() {
        Editor appUser = new Editor();
        appUser.setFirstname("Nikola");
        appUser.setLastname("Tesla");
        appUser.setUsername("NidzaEditor");
        appUser.setEmail("Emai@mailinator.com");
        appUser.setAddress("adresa");
        appUser.setPassword("123");
        appUser.setRole(UserRole.EDITOR);
        appUser.setVerified(true);
        appUser.setMagazine(magazineService.getMagazine(1L));
        return appUser;
    }

    private Reviewer createRewever() {
        Reviewer appUser = new Reviewer();
        appUser.setFirstname("Nikola");
        appUser.setLastname("Tesla");
        appUser.setUsername("NidzaReviewer");
        appUser.setEmail("Emai@mailinator.com");
        appUser.setAddress("adresa");
        appUser.setPassword("123");
        appUser.setRole(UserRole.REVIEWER);
        appUser.setVerified(true);
        List<ScientificField> scientificFields = new ArrayList<>();
        ScientificField scientificField = new ScientificField();
        scientificField.setTitle("afsf");
        scientificFields.add(scientificField);
        appUser.setScienceFields(scientificFields);
        return appUser;
    }
}

