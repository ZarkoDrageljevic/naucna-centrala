package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormFieldDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.MagazineService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("magazine")
@RestController
@RequiredArgsConstructor
public class MagazineController {

    private final MagazineService magazineService;
    private final TokenUtils tokenUtils;
    private final ApplicationUserService applicationUserService;
    private final ProcessService processService;


    @GetMapping()
    public ResponseEntity getAllMagazines() {

        return ResponseEntity.ok().body(magazineService.getAll());
    }

    @PostMapping("{taskId}")
    public ResponseEntity register(@PathVariable String taskId, @RequestBody List<TaskFormFieldDto> formFieldDtos) {
        processService.submitTaskForm(taskId, formFieldDtos);
        return ResponseEntity.ok().build();
    }



}
