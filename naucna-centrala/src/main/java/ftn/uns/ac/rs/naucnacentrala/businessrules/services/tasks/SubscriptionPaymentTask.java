package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Magazine;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.ApplicationUserService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.MagazineService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionPaymentTask implements JavaDelegate {

    private final ApplicationUserService applicationUserService;
    private final MagazineService magazineService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        final String username = (String) delegateExecution.getVariable("userId");
        ApplicationUser applicationUser = applicationUserService.findByUsername(username);

        final Long magazineId = Long.parseLong(delegateExecution.getVariable("magazineId").toString());
        Magazine magazine = magazineService.getMagazine(magazineId);

        magazineService.subscribeToMagazine(magazine,applicationUser);
    }
}
