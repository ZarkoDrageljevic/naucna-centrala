package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ScientificFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scientific-field")
@RequiredArgsConstructor
public class ScientificFieldController {

    private final ScientificFieldService scienceFieldService;


    @GetMapping
    ResponseEntity getAll(){
        return ResponseEntity.ok().body(scienceFieldService.findAll());
    }
}
