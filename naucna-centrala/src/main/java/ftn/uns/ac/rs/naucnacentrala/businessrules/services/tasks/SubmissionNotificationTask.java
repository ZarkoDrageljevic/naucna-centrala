package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.MailService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionNotificationTask implements JavaDelegate {
    private final MailService mailService;
    private final ApplicationUserService applicationUserService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        final String editorId = (String) delegateExecution.getVariable("executiveEditorId");
        final String authorUsername = (String) delegateExecution.getVariable("userId");

        final ApplicationUser editor = applicationUserService.findByUsername(editorId);
        final ApplicationUser author = applicationUserService.findByUsername(authorUsername);


        mailService.sendMail(editor.getEmail(), "Paper Submission", "There is new Paper submission");
        mailService.sendMail(author.getEmail(), "Paper Submission", "There is new Paper submission");

        System.out.println("Poslao Mail");
    }
}
