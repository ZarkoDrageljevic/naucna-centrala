package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionDataValidationTask implements JavaDelegate {

    private final PaperService paperService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long paperId = Long.parseLong((String) delegateExecution.getVariable("paperId"));
        delegateExecution.setVariable("submissionDataOk", paperService.checkIfExists(paperId));
    }
}
