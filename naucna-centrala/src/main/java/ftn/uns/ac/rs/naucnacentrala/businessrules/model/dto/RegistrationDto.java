package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegistrationDto implements Serializable {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String processInstanceId;


}
