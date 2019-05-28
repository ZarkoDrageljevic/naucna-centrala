package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {


    private final ProcessService processService;

    public TestController(ProcessService processService) {
        this.processService = processService;
    }


}

