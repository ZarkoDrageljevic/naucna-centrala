package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "editor")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Editor extends ApplicationUser {

    @OneToOne(mappedBy = "editor")
    private Magazine magazine;

}
