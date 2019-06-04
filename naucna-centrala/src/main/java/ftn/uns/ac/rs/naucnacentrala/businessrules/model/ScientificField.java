package ftn.uns.ac.rs.naucnacentrala.businessrules.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ScientificField {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","subscribedMagazines", "scienceFields", "magazines", "reviewedPapers", "reviews"})
    List<Reviewer> reviewers = new ArrayList<>();
    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","magazine", "editor", "scientificFields"})
    List<MagazineEditor> magazineEditors = new ArrayList<>();
    @OneToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","scientificField", "author", "reviewers", "reviews"})
    List<Paper> papers = new ArrayList<>();
    @Column
    private String title;
}
