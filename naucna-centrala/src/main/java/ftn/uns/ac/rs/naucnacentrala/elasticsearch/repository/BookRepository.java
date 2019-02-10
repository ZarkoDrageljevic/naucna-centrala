package ftn.uns.ac.rs.naucnacentrala.elasticsearch.repository;

import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.IndexUnit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<IndexUnit, String> {

    List<IndexUnit> findByTitle(String title);

    IndexUnit findByFilename(String filename);
}