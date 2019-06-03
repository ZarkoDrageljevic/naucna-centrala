package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormDataDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {

    private final ProcessService processService;
    private final TokenUtils tokenUtils;

    @GetMapping
    ResponseEntity getUserTasks(@RequestHeader String JWToken) {

        final List<TaskDto> tasks = processService.getTasks(null, tokenUtils.getUsernameFromToken(JWToken));

        return ResponseEntity.ok(tasks);

    }

    @GetMapping("/{taskId}")
    public ResponseEntity getRegisterForm(@PathVariable String taskId) {
        final TaskFormDataDto taskFormData = processService.getTaskFormData(taskId);
        return ResponseEntity.ok(taskFormData);
    }

}
