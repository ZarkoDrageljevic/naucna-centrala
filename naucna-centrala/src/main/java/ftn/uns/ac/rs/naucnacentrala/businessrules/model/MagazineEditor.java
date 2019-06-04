package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MagazineEditor {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","subscriptions", "reviewers", "magazineEditors", "editor"})
    private Magazine magazine;

    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","magazines", "magazineEditor", "subscribedMagazines"})
    private Editor editor;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","reviewers", "magazineEditors", "papers"})
    private List<ScientificField> scientificFields;
}
