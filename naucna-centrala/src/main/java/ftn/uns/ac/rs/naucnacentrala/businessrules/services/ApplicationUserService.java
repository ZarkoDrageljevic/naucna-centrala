package ftn.uns.ac.rs.naucnacentrala.businessrules.services;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {
    final ApplicationUserRepository appUserRepository;

    @Autowired
    ApplicationUserService(ApplicationUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
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
}
