package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormDataDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormFieldDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final ProcessService processService;

    public RegistrationController(ProcessService processService) {
        this.processService = processService;
    }

    @PostMapping("{taskId}")
    public ResponseEntity register(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        processService.submitTaskForm(taskId, formFieldDtos);


        return ResponseEntity.ok().build();
    }

    @GetMapping("form")
    public ResponseEntity getRegisterForm() {
        final String processId = processService.startProcess("UserRegistration", null);

        final List<TaskDto> processTasks = processService.getTasks(processId, null);
        final TaskDto task = processTasks.stream().findFirst().get();
        final TaskFormDataDto taskFormData = processService.getTaskFormData(task.getId());

        return ResponseEntity.ok(taskFormData);

    }
}
