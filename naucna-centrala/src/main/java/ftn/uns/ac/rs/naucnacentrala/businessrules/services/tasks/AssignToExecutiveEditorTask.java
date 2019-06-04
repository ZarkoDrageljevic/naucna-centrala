package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.services.MagazineService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignToExecutiveEditorTask implements JavaDelegate {
    private final MagazineService magazineService;
    private final PaperService paperService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String executiveEditorUsername = (String) delegateExecution.getVariable("executiveEditorId");
        delegateExecution.setVariable("chosenEditor", executiveEditorUsername);
        System.out.println("Setovan iz glavni editor");
    }
}
