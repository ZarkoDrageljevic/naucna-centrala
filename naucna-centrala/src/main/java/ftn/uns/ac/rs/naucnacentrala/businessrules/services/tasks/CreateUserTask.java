package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.UserRole;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CreateUserTask implements JavaDelegate {

    private final ProcessService processService;
    private final ApplicationUserService applicationUserService;

    public CreateUserTask(ProcessService processService, ApplicationUserService applicationUserService) {
        this.processService = processService;
        this.applicationUserService = applicationUserService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ApplicationUser appUser = new ApplicationUser();
        appUser.setFirstname(delegateExecution.getVariable("firstname").toString());
        appUser.setLastname(delegateExecution.getVariable("lastname").toString());
        appUser.setUsername(delegateExecution.getVariable("username").toString());
        appUser.setEmail(delegateExecution.getVariable("email").toString());
        appUser.setAddress(delegateExecution.getVariable("address").toString());
        appUser.setPassword(delegateExecution.getVariable("password").toString());
        appUser.setRole(UserRole.USER);
        appUser.setVerified(true);

        applicationUserService.createUser(appUser);
    }
}
