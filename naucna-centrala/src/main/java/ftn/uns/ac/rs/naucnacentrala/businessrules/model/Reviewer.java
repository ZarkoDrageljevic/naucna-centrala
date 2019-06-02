package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "reviewer")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reviewer extends ApplicationUser {

    @ManyToMany(mappedBy = "reviewers")
    private List<ScientificField> scienceFields = new ArrayList();

    @ManyToMany(mappedBy = "reviewers")
    private List<Magazine> magazines = new ArrayList<>();

    @ManyToMany
    private List<Paper> reviewedPapers = new ArrayList<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

}
