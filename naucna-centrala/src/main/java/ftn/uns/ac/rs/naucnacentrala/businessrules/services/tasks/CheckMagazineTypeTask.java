package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Magazine;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.MagazineService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckMagazineTypeTask implements JavaDelegate {

    private final MagazineService magazineService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String magazineId = (String) delegateExecution.getVariable("magazineId");

        final Magazine magazine = magazineService.getMagazine(Long.parseLong(magazineId));

        delegateExecution.setVariable("isOpenAccess", magazine.getOpenAccess());
    }
}
