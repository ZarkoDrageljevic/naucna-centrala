package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.MailService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotReviewedInTimeTask implements JavaDelegate {
    private final MailService mailService;
    private final ApplicationUserService applicationUserService;
    private final PaperService paperService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        final String editorId = (String) delegateExecution.getVariable("executiveEditorId");
        final String reviewerUsername = (String) delegateExecution.getVariable("reviewer");
        final String paperId = (String) delegateExecution.getVariable("paperId");


        final ApplicationUser editor = applicationUserService.findByUsername(editorId);

        final ApplicationUser reviewer = applicationUserService.findByUsername(reviewerUsername);


        paperService.removeReviewer(Long.parseLong(paperId),reviewer);

        mailService.sendMail(editor.getEmail(), "Paper not reviewed in time", "Paper not Reviewed in time by" + reviewer.getFirstname()
                + " " + reviewer.getLastname());

        mailService.sendMail(reviewer.getEmail(), "Paper not reviewed in time", "You didn't reviewed paper in time");

        System.out.println("Poslao Mail da je otkazano");

    }
}
