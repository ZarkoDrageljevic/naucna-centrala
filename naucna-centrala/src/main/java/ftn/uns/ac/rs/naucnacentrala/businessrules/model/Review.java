package ftn.uns.ac.rs.naucnacentrala.businessrules.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @ManyToOne
    private Reviewer reviewer;

    @Column
    private String comment;

    @Column
    private String mark;

    @Column
    private String commentToEditor;

}
