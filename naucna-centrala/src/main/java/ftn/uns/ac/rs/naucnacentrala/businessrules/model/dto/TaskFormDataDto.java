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
public class TaskFormDataDto {
    private String taskId;

    private List<TaskFormFieldDto> formFields;

}
