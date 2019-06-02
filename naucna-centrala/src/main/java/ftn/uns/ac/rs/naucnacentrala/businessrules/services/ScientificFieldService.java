package ftn.uns.ac.rs.naucnacentrala.businessrules.services;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.dto.ScientificFieldDto;
import ftn.uns.ac.rs.naucnacentrala.businessrules.repository.ScientificFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScientificFieldService {

    private final ScientificFieldRepository scienceFieldRepository;

    public List<ScientificFieldDto> findAll(){
        return scienceFieldRepository.findAll().stream().map(ScientificFieldDto::new).collect(Collectors.toList());

    }

}
