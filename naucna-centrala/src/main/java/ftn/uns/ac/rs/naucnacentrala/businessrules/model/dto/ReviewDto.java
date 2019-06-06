package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewDto {

    private String comment;
    private String mark;
    private String commentToEditor;

    public ReviewDto(Review review) {
        this.comment = review.getComment();
        this.commentToEditor = review.getCommentToEditor();
        this.mark = review.getMark();
    }
}
