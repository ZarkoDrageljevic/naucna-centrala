package ftn.uns.ac.rs.naucnacentrala.businessrules.repository;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);

    ApplicationUser findByEmail(String email);

    ApplicationUser findByUsernameAndPassword(String username, String password);
}
