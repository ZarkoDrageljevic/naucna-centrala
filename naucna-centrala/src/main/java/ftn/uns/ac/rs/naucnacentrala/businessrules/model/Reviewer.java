package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.ReviewerDto;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","reviewers", "magazineEditors", "papers"})
    private List<ScientificField> scienceFields = new ArrayList();

    @ManyToMany(mappedBy = "reviewers")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","subscriptions", "reviewers", "magazineEditors", "editor"})
    private List<Magazine> magazines = new ArrayList<>();

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","scientificField", "author", "reviewers", "reviews"})
    private List<Paper> reviewedPapers = new ArrayList<>();

    @OneToMany
    @JsonIgnoreProperties("reviewer")
    private List<Review> reviews = new ArrayList<>();

    public Reviewer(ReviewerDto reviewerDto) {
    }
}
