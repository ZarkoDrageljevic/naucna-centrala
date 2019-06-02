package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Paper;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.PaperSubmissionDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormFieldDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/paper")
@RequiredArgsConstructor
public class PaperSubmissionController {
    private final ProcessService processService;
    private final ApplicationUserService applicationUserService;
    private final PaperService paperService;
    private final TokenUtils tokenUtils;


    @PostMapping("/start-paper-process")
    public ResponseEntity startProcess(@RequestHeader String JWToken) {
        if (!tokenUtils.validateToken(JWToken))
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        String userUsername = tokenUtils.getUsernameFromToken(JWToken);
        final ApplicationUser applicationUser = applicationUserService.findCurrentUser(userUsername);

        Map<String, VariableValueDto> variables = new HashMap<>();
        final VariableValueDto variableValueDto = new VariableValueDto();
        variableValueDto.setValue(applicationUser.getUsername());
        variables.put("userId", variableValueDto);
        final String processId = processService.startProcess("PaperSubmission", variables);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/choose-magazine/{taskId}")
    public ResponseEntity chooseMagazine(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        processService.submitTaskForm(taskId, formFieldDtos);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/new/{taskId}")
    public ResponseEntity submitNewPaper(@PathVariable String taskId, @RequestPart("data") PaperSubmissionDto paperSubmissionDtos,
                                         @RequestPart("file") MultipartFile file, @RequestHeader String JWToken) {

        ApplicationUser applicationUser = applicationUserService.findByUsername(tokenUtils.getUsernameFromToken(JWToken));

        paperService.uploadFile(file, paperSubmissionDtos.getTitle());
        Paper paper = paperService.saveSubmitedFile(paperSubmissionDtos, applicationUser);

        List<TaskFormFieldDto> formFieldDtos = new ArrayList<>();
        TaskFormFieldDto taskFormFieldDto = new TaskFormFieldDto();
        taskFormFieldDto.setName("paperId");
        VariableValueDto variableValueDto = new VariableValueDto();
        variableValueDto.setValue(paper.getId().toString());
        taskFormFieldDto.setValue(variableValueDto);
        formFieldDtos.add(taskFormFieldDto);

        processService.submitTaskForm(taskId, formFieldDtos);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/submit-paper/{taskId}")
    public ResponseEntity submitPaper(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        processService.submitTaskForm(taskId, formFieldDtos);
        return ResponseEntity.noContent().build();
    }


}
