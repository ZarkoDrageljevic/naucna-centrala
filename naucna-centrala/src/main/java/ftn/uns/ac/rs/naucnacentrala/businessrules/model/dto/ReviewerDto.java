package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Reviewer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewerDto {

    private Long id;

    private String firstname;
    private String lastname;
    private List<ScientificFieldDto> scientificFields = new ArrayList<>();

    public ReviewerDto(Reviewer reviewer) {
        this.id = reviewer.getId();
        this.firstname = reviewer.getFirstname();
        this.lastname = reviewer.getLastname();
        this.scientificFields = reviewer.getScienceFields().stream().map(ScientificFieldDto::new).collect(Collectors.toList());
    }
}
