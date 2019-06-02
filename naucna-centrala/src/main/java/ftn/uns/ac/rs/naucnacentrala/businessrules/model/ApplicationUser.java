package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApplicationUser {

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

    @ManyToMany(mappedBy = "subscriptions")
    List<Magazine> subscribedMagazines;


}