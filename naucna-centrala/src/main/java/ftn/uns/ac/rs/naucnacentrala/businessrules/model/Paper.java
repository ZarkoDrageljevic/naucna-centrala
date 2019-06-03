package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column
    private String keywords;

    @Column
    private String paperAbstract;

    @Column(unique = true, name = "paper_title")
    private String title;

    @ManyToOne
    private ScientificField scientificField;


    @OneToMany
    private List<CoAuthor> coauthors = new ArrayList<>();

    @ManyToOne
    private ApplicationUser author;

    @ManyToMany
    private List<Reviewer> reviewers = new ArrayList<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

}
