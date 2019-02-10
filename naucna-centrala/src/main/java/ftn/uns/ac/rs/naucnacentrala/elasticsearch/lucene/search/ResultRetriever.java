package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.search;

import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing.handlers.DocumentHandler;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing.handlers.PDFHandler;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.IndexUnit;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.RequiredHighlight;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.ResultData;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultRetriever {

    @Autowired
    private BookRepository repository;

    public ResultRetriever() {
    }

    public List<ResultData> getResults(org.elasticsearch.index.query.QueryBuilder query,
                                       List<RequiredHighlight> requiredHighlights) {
        if (query == null) {
            return null;
        }

        List<ResultData> results = new ArrayList<ResultData>();

        for (IndexUnit indexUnit : repository.search(query)) {
            results.add(new ResultData(indexUnit.getTitle(), indexUnit.getKeywords(), indexUnit.getFilename(), ""));
        }


        return results;
    }

    protected DocumentHandler getHandler(String fileName) {
        if (fileName.endsWith(".pdf")) {
            return new PDFHandler();
        } else {
            return null;
        }
    }
}