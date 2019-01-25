package ftn.uns.ac.rs.naucnacentrala.businessrules.controller;

import ftn.uns.ac.rs.naucnacentrala.security.JwtGenerator;
import ftn.uns.ac.rs.naucnacentrala.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {
        System.out.println("???");
        return jwtGenerator.generate(jwtUser);
    }
}
