package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.services.MagazineService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChoseExecutiveEditorTask implements JavaDelegate {

    private final MagazineService magazineService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long magazineId = Long.parseLong((String) delegateExecution.getVariable("magazineId"));
        delegateExecution.setVariable("executiveEditorId", magazineService.getExecutiveEditorOfMagazine(magazineId));
        System.out.println("Dodao Editora");
    }
}
