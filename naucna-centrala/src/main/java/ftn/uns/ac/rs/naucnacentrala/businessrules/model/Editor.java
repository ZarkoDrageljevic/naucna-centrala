package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","subscriptions", "reviewers", "magazineEditors", "editor"})
    private Magazine magazine;

    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","magazine", "editor", "scientificFields"})
    private MagazineEditor magazineEditor;

}
