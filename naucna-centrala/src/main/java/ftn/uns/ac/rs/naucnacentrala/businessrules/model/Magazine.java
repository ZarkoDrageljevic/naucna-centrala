package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "magazine")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "open_access")
    protected Boolean openAccess;

    @Column
    private String name;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","subscribedMagazines"})
    private List<ApplicationUser> subscriptions;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","subscribedMagazines", "scienceFields", "magazines", "reviewedPapers", "reviews"})
    private List<Reviewer> reviewers = new ArrayList<>();

    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","magazines", "magazineEditor", "subscribedMagazines"})
    private Editor editor;

    @OneToMany(mappedBy = "magazine")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","magazine"})
    private List<MagazineEditor> magazineEditors;
}
