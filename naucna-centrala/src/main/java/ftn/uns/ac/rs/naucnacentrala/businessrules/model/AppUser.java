package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "username", unique = true, nullable = false)
    @Size(min = 3)
    protected String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @Size(min = 3)
    protected String password;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    protected String email;

    @Column(name = "firstname", nullable = true)
    protected String firstname;

    @Column(name = "lastname", nullable = true)
    protected String lastname;

    @Column(name = "address")
    protected String address;

    @Column(name = "verified")
    protected Boolean verified;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    protected UserRole role;

    public AppUser() {

    }

    public AppUser(Long id, @Size(min = 3) String username, @Size(min = 3) String password, @Email String email,
                   String firstname, String lastname, Boolean verified, UserRole role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.verified = verified;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}