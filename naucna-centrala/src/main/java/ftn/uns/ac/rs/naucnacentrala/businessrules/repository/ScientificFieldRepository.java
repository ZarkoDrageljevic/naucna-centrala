package ftn.uns.ac.rs.naucnacentrala.businessrules.repository;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.ScientificField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long> {
    ScientificField findByTitle(String title);

}
