package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Paper;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.PaperDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.PaperSubmissionDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.ReviewerDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormFieldDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.MagazineService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/paper")
@RequiredArgsConstructor
public class PaperSubmissionController {
    private final ProcessService processService;
    private final ApplicationUserService applicationUserService;
    private final MagazineService magazineService;
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

    @GetMapping("/task/{taskId}")
    public ResponseEntity getPaperByTaskId(@PathVariable String taskId) {
        final TaskDto task = processService.getTask(taskId);
        final String paperId = (String) processService.getVariable(task.getProcessInstanceId(), "paperId");

        return ResponseEntity.ok(new PaperDto(paperService.findById(Long.parseLong(paperId))));
    }

    @PostMapping("/tvalidation/{taskId}")
    public ResponseEntity tvalidation(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        processService.submitTaskForm(taskId, formFieldDtos);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/format-validation/{taskId}")
    public ResponseEntity formatValidation(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        processService.submitTaskForm(taskId, formFieldDtos);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/reject/thematic/{taskId}")
    public ResponseEntity rejectThematic(@PathVariable String taskId, @RequestPart("rejectionReason") String rejectionReason,
                                         @RequestHeader String JWToken) {

        final TaskDto task = processService.getTask(taskId);
        final String paperId = (String) processService.getVariable(task.getProcessInstanceId(), "paperId");

        paperService.delete(Long.parseLong(paperId));

        List<TaskFormFieldDto> formFieldDtos = new ArrayList<>();
        TaskFormFieldDto taskFormFieldDto = new TaskFormFieldDto();
        taskFormFieldDto.setName("rejectionReason");
        VariableValueDto variableValueDto = new VariableValueDto();
        variableValueDto.setValue(rejectionReason);
        taskFormFieldDto.setValue(variableValueDto);
        formFieldDtos.add(taskFormFieldDto);
        processService.submitTaskForm(taskId, formFieldDtos);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("/format-correction/{taskId}")
    public ResponseEntity resubmitFormat(@PathVariable String taskId, @RequestPart("data") PaperSubmissionDto paperSubmissionDtos,
                                         @RequestPart("file") MultipartFile file, @RequestHeader String JWToken) {

        ApplicationUser applicationUser = applicationUserService.findByUsername(tokenUtils.getUsernameFromToken(JWToken));

        paperService.uploadFile(file, paperSubmissionDtos.getTitle());

        final ArrayList<TaskFormFieldDto> taskFormFieldDtos = new ArrayList<>();
        processService.submitTaskForm(taskId, taskFormFieldDtos);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-reviewers/{taskId}")
    private ResponseEntity getMagazineReviewers(@PathVariable String taskId) {
        final TaskDto task = processService.getTask(taskId);
        final String magazineId = (String) processService.getVariable(task.getProcessInstanceId(), "magazineId");


        return ResponseEntity.ok(magazineService.getReviewers(Long.parseLong(magazineId)));
    }

    @PostMapping("/select-reviewers/{taskId}")
    private ResponseEntity selectReviewers(@PathVariable String taskId,
                                           @RequestPart("reviewers") List<ReviewerDto> reviewers) {
        final TaskDto task = processService.getTask(taskId);
        final String paperId = (String) processService.getVariable(task.getProcessInstanceId(), "paperId");

        final Paper paper = paperService.submitReviewers(Long.parseLong(paperId), reviewers);


        final ArrayList<TaskFormFieldDto> taskFormFieldDtos = new ArrayList<>();
        List<String> reviewerUsernames = reviewers.stream().map(reviewerDto -> applicationUserService.findByLongId(reviewerDto.getId())).map(ApplicationUser::getUsername).collect(Collectors.toList());
        VariableValueDto variableValueDto = new VariableValueDto();
        variableValueDto.setValue(reviewerUsernames);
        VariableValueDto variableValueDto2 = new VariableValueDto();
        variableValueDto2.setValue("PT10M");
        taskFormFieldDtos.add(new TaskFormFieldDto("reviewers", variableValueDto));
        taskFormFieldDtos.add(new TaskFormFieldDto("reviewTime", variableValueDto2));
        processService.submitTaskForm(taskId, taskFormFieldDtos);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/submit-review/{taskId}")
    private ResponseEntity submitReview(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        formFieldDtos = formFieldDtos.stream().filter(formFieldDto -> formFieldDto.getName().equals("comment")
                || formFieldDto.getName().equals("commentToEditor") || formFieldDto.getName().equals("mark")
                || formFieldDto.getName().equals("reviewer")).collect(Collectors.toList());

        processService.submitTaskForm(taskId, formFieldDtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-reviews/{taskId}")
    private ResponseEntity getPaperReviews(@PathVariable String taskId) {
        final TaskDto task = processService.getTask(taskId);
        final String paperId = (String) processService.getVariable(task.getProcessInstanceId(), "paperId");


        return ResponseEntity.ok(paperService.getReviewes(Long.parseLong(paperId)));
    }

    @PostMapping("/submit-editor-review/{taskId}")
    private ResponseEntity submitEditorReview(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        formFieldDtos = formFieldDtos.stream().filter(formFieldDto -> formFieldDto.getName().equals("decision")
        ).collect(Collectors.toList());
        VariableValueDto variableValueDto = new VariableValueDto();

        if (formFieldDtos.get(0).getValue().getValue().equals("rejected")) {
            variableValueDto.setValue(Boolean.TRUE);
            formFieldDtos.add(new TaskFormFieldDto("isPaperRejected", variableValueDto));
        } else {
            variableValueDto.setValue(Boolean.FALSE);
            formFieldDtos.add(new TaskFormFieldDto("isPaperRejected", variableValueDto));
        }

        processService.submitTaskForm(taskId, formFieldDtos);
        return ResponseEntity.ok().build();
    }
}
