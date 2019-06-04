package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Magazine;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.MagazineEditor;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Paper;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.MagazineService;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckScientificEditorTask implements JavaDelegate {
    private final MagazineService magazineService;
    private final PaperService paperService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String magazineId = (String) delegateExecution.getVariable("magazineId");
        final Magazine magazine = magazineService.getMagazine(Long.parseLong(magazineId));
        String paperId = (String) delegateExecution.getVariable("paperId");
        final Paper paper = paperService.getPaperWithDetails(Long.parseLong(magazineId));

        Optional<MagazineEditor> any = magazine.getMagazineEditors().stream().filter(magazineEditor ->
                magazineEditor.getScientificFields().contains(paper.getScientificField())).findFirst();

        if (any.isPresent()) {
            delegateExecution.setVariable("isScientificEditorPresent", Boolean.TRUE);
            System.out.println("POSTOJI");
        } else {
            delegateExecution.setVariable("isScientificEditorPresent", Boolean.FALSE);
            System.out.println("NE POSTOJI");
        }

        Object afs =  delegateExecution.getVariable("isScientificEditorPresent");
        System.out.println(afs);
    }
}
