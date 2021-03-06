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
public class AssignScientificFieldChefEditorTask implements JavaDelegate {
    private final MagazineService magazineService;
    private final PaperService paperService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String magazineId = (String) delegateExecution.getVariable("magazineId");
        final Magazine magazine = magazineService.getMagazine(Long.parseLong(magazineId));
        String paperId = (String) delegateExecution.getVariable("paperId");
        final Paper paper = paperService.findById(Long.parseLong(magazineId));

        Optional<MagazineEditor> any = magazine.getMagazineEditors().stream().filter(magazineEditor ->
                magazineEditor.getScientificFields().contains(paper.getScientificField())).findFirst();

        if (any.isPresent()) {
            delegateExecution.setVariable("chosenEditor", any.get().getEditor().getUsername());
            System.out.println("Setovan iz naucne oblasti");
        }
    }
}
