package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.camunda.bpm.engine.form.FormField;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegistrationFormDto {
    String taskId;
    List<FormField> formFields;
    String processInstanceId;

}
