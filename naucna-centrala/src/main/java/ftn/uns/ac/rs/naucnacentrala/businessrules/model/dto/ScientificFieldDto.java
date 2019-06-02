package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;


import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ScientificField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ScientificFieldDto {

    private String title;

    public ScientificFieldDto(ScientificField scientificField) {
        this.title = scientificField.getTitle();
    }
}
