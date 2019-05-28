package ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskDto {

	String taskId;
	String name;
	String assignee;


}
