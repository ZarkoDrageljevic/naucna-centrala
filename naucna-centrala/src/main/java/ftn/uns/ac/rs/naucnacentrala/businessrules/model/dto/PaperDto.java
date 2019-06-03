package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Paper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaperDto {

    private String title;
    private String paperAbstract;
    private String keywords;
    private ScientificFieldDto scienceField;

    public PaperDto(Paper paper) {
        this.title = paper.getTitle();
        this.paperAbstract = paper.getPaperAbstract();
        this.keywords = paper.getKeywords();
        this.scienceField = new ScientificFieldDto(paper.getScientificField());
    }
}
