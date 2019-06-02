package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormDataDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormFieldDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.request.LoginRequest;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import ftn.uns.ac.rs.naucnacentrala.security.JwtGenerator;
import ftn.uns.ac.rs.naucnacentrala.security.JwtUser;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ProcessService processService;
    private final ApplicationUserService applicationUserService;
    private final JwtGenerator jwtGenerator;

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {
        System.out.println("???");
        return jwtGenerator.generate(jwtUser);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return applicationUserService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/registration/{taskId}")
    public ResponseEntity register(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        processService.submitTaskForm(taskId, formFieldDtos);


        return ResponseEntity.ok().build();
    }

    @GetMapping("/registration/form")
    public ResponseEntity getRegisterForm() {
        final String processId = processService.startProcess("UserRegistration", null);

        final List<TaskDto> processTasks = processService.getTasks(processId, null);
        final TaskDto task = processTasks.stream().findFirst().get();
        final TaskFormDataDto taskFormData = processService.getTaskFormData(task.getId());

        return ResponseEntity.ok(taskFormData);

    }
}
