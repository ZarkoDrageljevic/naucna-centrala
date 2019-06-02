package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaperSubmissionDto {

    private String title;
    private String paperAbstract;
    private String keywords;
    private List<CoAuthorDto> coauthors;
    private ScientificFieldDto scienceField;

}
