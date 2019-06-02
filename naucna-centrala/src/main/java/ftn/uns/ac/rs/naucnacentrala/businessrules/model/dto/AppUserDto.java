package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AppUserDto {
    protected Long id;

    protected String username;

    protected String email;

    protected String firstname;

    protected String lastname;

    protected String address;

    protected UserRole role;


    public AppUserDto(ApplicationUser applicationUser) {
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
        this.email = applicationUser.getEmail();
        this.firstname = applicationUser.getFirstname();
        this.lastname = applicationUser.getLastname();
        this.address = applicationUser.getAddress();
        this.role = applicationUser.getRole();
    }
}
