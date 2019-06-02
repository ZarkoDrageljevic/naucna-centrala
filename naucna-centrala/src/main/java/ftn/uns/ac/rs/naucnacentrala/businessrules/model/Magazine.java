package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

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
    private List<ApplicationUser> subscriptions;

    @ManyToMany
    private List<Reviewer> reviewers = new ArrayList<>();

    @OneToOne
    private Editor editor;
}
