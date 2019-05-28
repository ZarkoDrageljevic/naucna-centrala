package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.MailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class UnsuccessfulRegistrationNotificationTask implements JavaDelegate {
    private final MailService mailService;

    public UnsuccessfulRegistrationNotificationTask(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        final String email = (String) delegateExecution.getVariable( "email");
        mailService.sendMail(email, "Registration Unsuccessful", "Registration is Unsuccessful.");

    }
}
