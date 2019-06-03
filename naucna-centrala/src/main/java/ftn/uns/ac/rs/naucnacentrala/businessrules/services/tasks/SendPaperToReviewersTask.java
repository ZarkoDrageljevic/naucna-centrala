package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendPaperToReviewersTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}
