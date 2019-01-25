package ftn.uns.ac.rs.naucnacentrala.businessrules.services;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.AppUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.UserRole;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.AppUserRepository;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegistrationService {

    private FormService formService;

    private TaskService taskService;

    private RuntimeService runtimeService;

    private AppUserRepository userRepository;

    private SimpMessagingTemplate template;

    @Autowired
    public RegistrationService(final FormService formService,
                               final TaskService taskService,
                               final RuntimeService runtimeService,
                               final SimpMessagingTemplate template,
                               final AppUserRepository userRepository) {
        this.formService = formService;
        this.taskService = taskService;
        this.runtimeService = runtimeService;
        this.template = template;
        this.userRepository = userRepository;

    }

    public void saveRegistration(String firstname, String lastname, String username, String password, String email, String address) {
        AppUser user = new AppUser();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setVerified(true);
        user.setRole(UserRole.USER);

        userRepository.save(user);
    }


    public boolean validateRegistration(String firstname, String lastname, String username, String password, String email, String address) {
        AppUser userByUsername = this.userRepository.findByUsername(username);
        AppUser userByEmail = this.userRepository.findByEmail(email);

        return (userByUsername == null && userByEmail == null);
    }

    public String unsuccessfullRegistration(){
        return "unsuccessfullRegistration";
    }

    public String successfullRegistration(){
        return "successfullRegistration";
    }
}
