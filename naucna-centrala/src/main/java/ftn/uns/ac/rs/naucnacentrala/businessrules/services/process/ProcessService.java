package ftn.uns.ac.rs.naucnacentrala.businessrules.services.process;


import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormDataDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.TaskFormFieldDto;
import ftn.uns.ac.rs.naucnacentrala.constants.CamundaConstants;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.rest.dto.identity.UserDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.rest.dto.runtime.StartProcessInstanceDto;
import org.camunda.bpm.engine.rest.dto.task.CompleteTaskDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProcessService {

    private final RestTemplate restTemplate;
    @Value("${camunda-rest-base-path}")
    private String basePath;

    public ProcessService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String startProcess(String key, Map<String, VariableValueDto> variables) {
        final StartProcessInstanceDto startProcessInstanceDto = new StartProcessInstanceDto();
        startProcessInstanceDto.setVariables(variables);

        final ProcessInstanceDto response = restTemplate.postForObject(String.format(basePath + CamundaConstants.START_PROCESS, key), startProcessInstanceDto, ProcessInstanceDto.class);
        return response.getId();
    }

    public List<TaskDto> getTasks(String processId, String assignee) {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(basePath + CamundaConstants.TASK);

        if (processId != null) {
            builder.queryParam("processInstanceId", processId);
        }

        if (assignee != null) {
            builder.queryParam("assignee", assignee);
        }

        ParameterizedTypeReference<List<TaskDto>> returnType = new ParameterizedTypeReference<List<TaskDto>>() {
        };
        final List<TaskDto> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, returnType).getBody();
        return res;
    }

    public TaskFormDataDto getTaskFormData(String taskId) {

        String url = String.format(basePath + CamundaConstants.FORM_VARIABLES, taskId);

        ParameterizedTypeReference<Map<String, VariableValueDto>> returnType = new ParameterizedTypeReference<Map<String, VariableValueDto>>() {
        };
        final Map<String, VariableValueDto> res = restTemplate.exchange(url, HttpMethod.GET, null, returnType).getBody();

        final List<TaskFormFieldDto> fieldDtos = res.entrySet().stream().map(el -> new TaskFormFieldDto(el.getKey(), el.getValue())).collect(Collectors.toList());
        return new TaskFormDataDto(taskId, fieldDtos);
    }

//    public void setVariable(String processId, String variable) {
//        VariableValueDto variableValueDto = new VariableValueDto();
//        variableValueDto.setType("String");
//        variableValueDto.setValue(variable);
//
//        String url = String.format(basePath + CamundaConstants.GET_VARIABLE, processId, "userId");
//        String urlProces = String.format(basePath + CamundaConstants.GET_PROCESS, "PaperSubmission");
//
//
//        Object processInstanceDto = restTemplate.getForObject(urlProces, Object.class);
//        restTemplate.put(url, variableValueDto);
//        String string = (String) getVariable("PaperSubmission", "userId");
//    }

    public void submitTaskForm(String taskId, List<TaskFormFieldDto> formFieldDtos) {
        String url = String.format(basePath + CamundaConstants.SUBMIT_FORM, taskId);

        final CompleteTaskDto completeTaskDto = new CompleteTaskDto();
        completeTaskDto.setVariables(formFieldDtos.stream().collect(Collectors.toMap(TaskFormFieldDto::getName, TaskFormFieldDto::getValue)));

        restTemplate.postForLocation(url, completeTaskDto);
    }

    public void createUser(UserDto userDto) {

        restTemplate.postForLocation(basePath + CamundaConstants.CREATE_USER, userDto);
    }

    public TaskDto getTask(String taskId) {
        String url = String.format(basePath + CamundaConstants.GET_TASK, taskId);

        return restTemplate.getForObject(url, TaskDto.class);
    }

    public Object getVariable(String processId, String key) {
        String url = String.format(basePath + CamundaConstants.GET_VARIABLE, processId, key);

        return restTemplate.getForObject(url, VariableValueDto.class).getValue();
    }
}
