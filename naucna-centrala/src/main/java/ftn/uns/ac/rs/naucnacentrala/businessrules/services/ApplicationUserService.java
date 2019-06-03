package ftn.uns.ac.rs.naucnacentrala.businessrules.services;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.AppUserDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ApplicationUserRepository;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.process.ProcessService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.utils.TokenUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {
    final ApplicationUserRepository appUserRepository;

    private ProcessService processService;

    private TokenUtils tokenUtils;

    public ApplicationUserService(TokenUtils tokenUtils, ApplicationUserRepository appUserRepository, ProcessService processService) {
        this.appUserRepository = appUserRepository;
        this.processService = processService;
        this.tokenUtils = tokenUtils;
    }


    public ApplicationUser createUser(ApplicationUser appUser) {
        appUser = appUserRepository.save(appUser);
        return appUser;
    }

    public ApplicationUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public boolean validateUser(ApplicationUser appUser) {
        ApplicationUser applicationUser = this.appUserRepository.findByUsername(appUser.getUsername());
        return applicationUser == null;
    }

    public ResponseEntity login(String username, String password) {
        ApplicationUser applicationUser = appUserRepository.findByUsernameAndPassword(username, password);

        if (applicationUser == null) throw new RuntimeException();

        String token = tokenUtils.generateToken(applicationUser.getUsername());
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("JWToken", token);
        return new ResponseEntity<>(new AppUserDto(applicationUser), httpHeaders, HttpStatus.OK);

    }

    public ApplicationUser findCurrentUser(String username) {
        return appUserRepository.findByUsername(username);
    }

    public ApplicationUser findById(String editorId) {
        return appUserRepository.getOne(Long.parseLong(editorId));
    }
}
