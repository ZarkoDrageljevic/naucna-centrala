package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.RegistrationDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.RegistrationFormDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskDto;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private IdentityService identityService;
    private TaskService taskService;
    private FormService formService;
    private RuntimeService runtimeService;
    private RepositoryService repositoryService;

    public RegistrationController(IdentityService identityService, TaskService taskService, FormService formService,
                                  RuntimeService runtimeService, RepositoryService repositoryService) {

        this.identityService = identityService;
        this.taskService = taskService;
        this.formService = formService;
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
    }

    @GetMapping
    public @ResponseBody
    RegistrationFormDto startRegistrationProcess() {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("RegistracijaUsera");
        runtimeService.setVariable(pi.getProcessInstanceId(), "processInstanceId", true);
        //TODO PROMENI SVA IMENA

        Task task = taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list().get(0);
        TaskFormData formData = formService.getTaskFormData(task.getId());
        List<FormField> properties = formData.getFormFields();

        return new RegistrationFormDto(task.getId(), properties, pi.getProcessInstanceId());
    }

    @PostMapping("")
    public ResponseEntity register(@RequestBody RegistrationDto dto) {
        HashMap<String, Object> map = this.mapListToDto(dto);
        Task task = taskService.createTaskQuery().processInstanceId(dto.getProcessInstanceId()).list().get(0);
        String processInstanceId = task.getProcessInstanceId();

        runtimeService.setVariable(processInstanceId, "registration", dto);
        formService.submitTaskForm(task.getId(), map);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("task")
    public ResponseEntity getUserTasks(@RequestParam("username") String username) {
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(username)
                .list();

        if (tasks.size() > 0) {
            Task nextTask = tasks.get(0);
            TaskDto t = new TaskDto(nextTask.getId(), nextTask.getName(), username);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    private HashMap<String, Object> mapListToDto(RegistrationDto list) {
        ObjectMapper mapper = new ObjectMapper();
        HashMap map = mapper.convertValue(list, HashMap.class);
        return map;
    }

}
