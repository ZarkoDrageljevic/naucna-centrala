package ftn.uns.ac.rs.naucnacentrala.businessrules.model;


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

    @Column
    private String title;

    @ManyToMany
    List<Reviewer> reviewers = new ArrayList<>();

    @OneToMany
    List<Paper> papers = new ArrayList<>();
}
