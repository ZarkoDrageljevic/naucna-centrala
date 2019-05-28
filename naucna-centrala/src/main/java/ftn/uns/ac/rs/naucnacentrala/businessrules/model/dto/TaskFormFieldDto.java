package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskFormFieldDto {

    private String name;

    private VariableValueDto value;

}
