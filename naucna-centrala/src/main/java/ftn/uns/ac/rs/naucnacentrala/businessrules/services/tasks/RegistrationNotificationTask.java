package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.MailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class RegistrationNotificationTask implements JavaDelegate {

    private final MailService mailService;

    private ApplicationUserService applicationUserService;

    public RegistrationNotificationTask(MailService mailService,ApplicationUserService applicationUserService){
        this.mailService = mailService;
        this.applicationUserService = applicationUserService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        final String username = (String) delegateExecution.getVariable("username");
        final ApplicationUser user = applicationUserService.findByUsername(username);

        mailService.sendMail(user.getEmail(), "Registration Successful", "Registration is successful.");

    }
}
