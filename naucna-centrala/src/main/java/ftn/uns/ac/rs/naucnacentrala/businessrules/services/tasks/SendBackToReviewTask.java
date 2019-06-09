package ftn.uns.ac.rs.naucnacentrala.businessrules.services.tasks;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ApplicationUser;
import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Paper;
import ftn.uns.ac.rs.naucnacentrala.businessrules.services.PaperService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SendBackToReviewTask implements JavaDelegate {

    private final PaperService paperService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String paperId = (String) delegateExecution.getVariable("paperId");

        Paper paper = paperService.removeOldReviews(Long.parseLong(paperId));

        List<String> reviewers = paper.getReviewers().stream()
                .map(ApplicationUser::getUsername).collect(Collectors.toList());

        delegateExecution.setVariable("reviewers", reviewers);

    }
}
