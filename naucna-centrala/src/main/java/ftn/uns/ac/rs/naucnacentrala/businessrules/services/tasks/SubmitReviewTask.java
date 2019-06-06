package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Review;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Reviewer;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ReviewService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.MailService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmitReviewTask implements JavaDelegate {

    private final MailService mailService;
    private final ApplicationUserService applicationUserService;
    private final ReviewService reviewService;
    private final PaperService paperService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        final String editorId = (String) delegateExecution.getVariable("executiveEditorId");
        final String paperId = (String) delegateExecution.getVariable("paperId");
        final String reviewerUsername = (String) delegateExecution.getVariable("reviewer");

        final String comment = (String) delegateExecution.getVariable("comment");
        final String commentToEditor = (String) delegateExecution.getVariable("commentToEditor");
        final String mark = (String) delegateExecution.getVariable("mark");

        final ApplicationUser editor = applicationUserService.findByUsername(editorId);
        final Reviewer reviewer = (Reviewer) applicationUserService.findByUsername(reviewerUsername);

        Review review = Review.builder().comment(comment).commentToEditor(commentToEditor).reviewer(reviewer).mark(mark).build();
        review = reviewService.saveReview(review);

        paperService.savePaperAndReview(Long.parseLong(paperId), review);

        mailService.sendMail(editor.getEmail(), "Paper Reviewed", "Paper Reviewed by" + reviewer.getFirstname()
                + " " + reviewer.getLastname());

        mailService.sendMail(reviewer.getEmail(), "Paper Reviewed", "You reviewed paper");

        System.out.println("Submitovano");
    }
}
