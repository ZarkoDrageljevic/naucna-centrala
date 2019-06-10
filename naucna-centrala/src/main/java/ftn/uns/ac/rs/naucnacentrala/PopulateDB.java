package ftn.uns.ac.rs.naucnacentrala;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.*;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ApplicationUserRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.MagazineEditorRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.MagazineRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ScientificFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulateDB implements ApplicationRunner {
    private final ApplicationUserRepository applicationUserRepository;
    private final MagazineRepository magazineRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final MagazineEditorRepository magazineEditorRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (!applicationUserRepository.findAll().isEmpty()) {
            return;
        }

        ApplicationUser applicationUser1 = applicationUserRepository.save(createAuthor(1L, "MarijeBursac15",
                "zarko_drageljevic@hotmail.com", "Zarko",
                "Drageljevic", "123", UserRole.USER, "Zarko", true));
        ApplicationUser applicationUser2 = applicationUserRepository.save(createAuthor(2L, "MarijeBursac15", "petarpetar@mailinator.com", "Petar", "Petrovic", "123", UserRole.USER, "Petar", true));
        ApplicationUser applicationUser3 = applicationUserRepository.save(createAuthor(3L, "MarijeBursac15", "mirkomirko@mailinator.com", "Mirko", "Mirkovic", "123", UserRole.USER, "Mirko", true));
        ApplicationUser applicationUser4 = applicationUserRepository.save(createAuthor(4L, "adresa", "Emai@mailinator.com", "Zivan", "Zivkovic", "123", UserRole.USER, "Zivan", true));
        Editor editor1 = applicationUserRepository.save(createeditor(5L, "adresa", "NidzaEditor@mailinator.com", "Nikola", "Nikolic", "123", UserRole.EDITOR, "NidzaEditor", true));
        Editor editor2 = applicationUserRepository.save(createeditor(6L, "adresa", "SimaEditor@mailinator.com", "Sima", "Simic", "123", UserRole.EDITOR, "SimaEditor", true));
        Editor editor3 = applicationUserRepository.save(createeditor(7L, "adresa", "MareEditor@mailinator.com", "Marko", "Markovic", "123", UserRole.EDITOR, "MareEditor", true));
        Reviewer reviewer1 = applicationUserRepository.save(createRewever(8L, "adresa", "VladaReviewer@mailinator.com", "Vlada", "Vladic", "123", UserRole.REVIEWER, "VladaReviewer", true));
        Reviewer reviewer2 = applicationUserRepository.save(createRewever(9L, "adresa", "StankoReviewer@mailinator.com", "Stanko", "Stankovic", "123", UserRole.REVIEWER, "StankoReviewer", true));
        Reviewer reviewer3 = applicationUserRepository.save(createRewever(10L, "adresa", "NemanjaReviewr@mailinator.com", "Nemanja", "Nemanjic", "123", UserRole.REVIEWER, "NemanjaReviewer", true));

        Editor magazine1editor1 = applicationUserRepository.save(createeditor(11L, "adresa", "MyTestEmailEditor11@mailinator.com", "Nikola", "Tesla", "123", UserRole.EDITOR, "Magazine1Editor1", true));
        Editor magazine1editor2 = applicationUserRepository.save(createeditor(12L, "adresa", "MyTestEmailEditor12@mailinator.com", "Sima", "Simic", "123", UserRole.EDITOR, "Magazine1Editor2", true));
        Editor magazine2editor1 = applicationUserRepository.save(createeditor(13L, "adresa", "MyTestEmailEditor21@mailinator.com", "Vuk", "Vukic", "123", UserRole.EDITOR, "Magazine2Editor1", true));
        Editor magazine2editor2 = applicationUserRepository.save(createeditor(14L, "adresa", "MyTestEmailEditor22@mailinator.com", "Milan", "Milanovic", "123", UserRole.EDITOR, "Magazine2Editor2", true));


        Magazine magazine1 = magazineRepository.save(createMagazine(1L, "Magazin1", true, editor1, Arrays.asList(reviewer1, reviewer2, reviewer3), Arrays.asList(applicationUser1, applicationUser2)));
        Magazine magazine2 = magazineRepository.save(createMagazine(2L, "Magazin2", false, editor2, Arrays.asList(reviewer1, reviewer2, reviewer3), Arrays.asList(applicationUser1, applicationUser2)));
        Magazine magazine3 = magazineRepository.save(createMagazine(3L, "Magazin3", true, editor3, Arrays.asList(reviewer1, reviewer2, reviewer3), Arrays.asList(applicationUser3, applicationUser4)));

        ScientificField scientificField1 = scientificFieldRepository.save(createScientificField(1L, "Matematika", Arrays.asList(reviewer1, reviewer2)));
        ScientificField scientificField2 = scientificFieldRepository.save(createScientificField(2L, "Programiranje", Arrays.asList(reviewer1, reviewer2)));
        ScientificField scientificField3 = scientificFieldRepository.save(createScientificField(3L, "Biologija", Arrays.asList(reviewer3, reviewer2)));
        ScientificField scientificField4 = scientificFieldRepository.save(createScientificField(4L, "Hemija", Arrays.asList(reviewer2)));

        MagazineEditor magazineEditor1 = magazineEditorRepository.save(createMagazineEditor(1L, magazine1editor1, magazine1, Arrays.asList(scientificField1, scientificField2)));
        MagazineEditor magazineEditor2 = magazineEditorRepository.save(createMagazineEditor(2L, magazine1editor2, magazine1, Arrays.asList(scientificField3, scientificField4)));
        MagazineEditor magazineEditor3 = magazineEditorRepository.save(createMagazineEditor(3L, magazine2editor1, magazine2, Arrays.asList(scientificField1, scientificField2)));
        MagazineEditor magazineEditor4 = magazineEditorRepository.save(createMagazineEditor(4L, magazine2editor2, magazine2, Arrays.asList(scientificField3, scientificField4)));

    }

    private MagazineEditor createMagazineEditor(Long id, Editor editor, Magazine magazine, List<ScientificField> scientificFields) {
        MagazineEditor magazineEditor = new MagazineEditor();
        magazineEditor.setId(id);
        magazineEditor.setEditor(editor);
        magazineEditor.setMagazine(magazine);
        magazineEditor.setScientificFields(scientificFields);
        return magazineEditor;
    }

    private ScientificField createScientificField(Long id, String title, List<Reviewer> reviewers) {
        ScientificField scientificField = new ScientificField();
        scientificField.setTitle(title);
        scientificField.setId(id);
        scientificField.setReviewers(reviewers);
        scientificField.setPapers(new ArrayList<>());
        return scientificField;
    }

    private ApplicationUser createAuthor(Long id, String address, String email, String firstname,
                                         String lastname, String password,
                                         UserRole userRole, String username, boolean active) {
        ApplicationUser appUser = new ApplicationUser();
        appUser.setId(id);
        appUser.setFirstname(firstname);
        appUser.setLastname(lastname);
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setAddress(address);
        appUser.setPassword(password);
        appUser.setRole(UserRole.USER);
        appUser.setVerified(true);
        return appUser;
    }

    private Editor createeditor(Long id, String address, String email, String firstname,
                                String lastname, String password,
                                UserRole userRole, String username, boolean active) {
        Editor appUser = new Editor();
        appUser.setId(id);
        appUser.setFirstname(firstname);
        appUser.setLastname(lastname);
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setAddress(address);
        appUser.setPassword(password);
        appUser.setRole(UserRole.EDITOR);
        appUser.setVerified(true);
        return appUser;
    }

    private Reviewer createRewever(Long id, String address, String email, String firstname,
                                   String lastname, String password,
                                   UserRole userRole, String username, boolean active) {
        Reviewer appUser = new Reviewer();
        appUser.setId(id);
        appUser.setFirstname(firstname);
        appUser.setLastname(lastname);
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setAddress(address);
        appUser.setPassword(password);
        appUser.setRole(UserRole.REVIEWER);
        appUser.setVerified(true);
        return appUser;
    }

    private Magazine createMagazine(Long id, String name, boolean openaAccess, Editor editor,
                                    List<Reviewer> reviwers, List<ApplicationUser> subscriptions) {
        Magazine magazine = new Magazine();
        magazine.setEditor(editor);
        magazine.setId(id);
        magazine.setOpenAccess(openaAccess);
        magazine.setName(name);
        magazine.setReviewers(reviwers);
        magazine.setSubscriptions(subscriptions);
        return magazine;
    }


}
