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
public class ChangesNeededNotificationTask implements JavaDelegate {
    private final MailService mailService;
    private final ApplicationUserService applicationUserService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String decision = (String) delegateExecution.getVariable("decision");
        final String username = (String) delegateExecution.getVariable("userId");
        final ApplicationUser user = applicationUserService.findByUsername(username);

        mailService.sendMail(user.getEmail(), decision + "Changes Needed", "Paper need fixing, format not ok.");
    }
}
