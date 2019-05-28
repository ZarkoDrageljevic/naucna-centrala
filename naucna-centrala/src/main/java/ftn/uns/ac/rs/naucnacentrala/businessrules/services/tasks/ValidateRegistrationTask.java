package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class ValidateRegistrationTask implements JavaDelegate {

    private final ProcessService processService;
    private final ApplicationUserService applicationUserService;

    public ValidateRegistrationTask(ProcessService processService, ApplicationUserService applicationUserService){
        this.processService = processService;
        this.applicationUserService = applicationUserService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("UserRegistration", delegateExecution.getVariable("email"));
        ApplicationUser appUser = new ApplicationUser();
        appUser.setUsername(delegateExecution.getVariable("username").toString());
        appUser.setEmail(delegateExecution.getVariable("email").toString());

        delegateExecution.setVariable("isRegistrationOK" ,applicationUserService.validateUser(appUser));
    }
}
