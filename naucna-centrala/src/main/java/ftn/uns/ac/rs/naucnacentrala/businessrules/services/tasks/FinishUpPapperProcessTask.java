package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class FinishUpPapperProcessTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //TODO I dont know what i will do here but we'll see
    }
}
