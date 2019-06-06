package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.MailService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SendPaperToReviewersTask implements JavaDelegate {

    private final MailService mailService;
    private final ApplicationUserService applicationUserService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<String> rewiewers = (List<String>) delegateExecution.getVariable("reviewers");

        for (String username : rewiewers) {
            ApplicationUser user = applicationUserService.findByUsername(username);
            delegateExecution.setVariable("reviewer", username);
            mailService.sendMail(user.getEmail(), "You got new paper to review", "Dear" +
                    username + "You got new paper to review");
        }

    }
}
