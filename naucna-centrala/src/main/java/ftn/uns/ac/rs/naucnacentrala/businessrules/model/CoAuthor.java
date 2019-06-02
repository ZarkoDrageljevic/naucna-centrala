package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.CoAuthorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @Column
    String address;
    @Column
    private String firstname;
    @Column
    private String lastname;

    public CoAuthor(CoAuthorDto coAuthorDto) {
        this.firstname = coAuthorDto.getFirstname();
        this.lastname = coAuthorDto.getLastname();
        this.address = coAuthorDto.getAddress();
    }
}
