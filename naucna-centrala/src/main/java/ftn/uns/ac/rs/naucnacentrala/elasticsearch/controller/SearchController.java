package ftn.uns.ac.rs.naucnacentrala.elasticsearch.controller;


import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.AdvancedQuery;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.ResultData;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.SimpleQuery;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.search.ResultRetriever;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ResultRetriever resultRetriever;

    @PostMapping(value = "/search/match", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchMatchQuery(@RequestBody SimpleQuery simpleQuery) throws Exception {
        System.out.println("SIMPLE");
        System.out.println(simpleQuery.getField()+" " + simpleQuery.getValue());
        org.elasticsearch.index.query.QueryBuilder query = QueryBuilders.matchQuery(simpleQuery.getField(), simpleQuery.getValue());
        List<ResultData> results = resultRetriever.getResults(query);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/phrase", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchPhrase(@RequestBody SimpleQuery simpleQuery) throws Exception {
        System.out.println("PHRAZE");
        System.out.println(simpleQuery.getField()+" " + simpleQuery.getValue());
        org.elasticsearch.index.query.QueryBuilder query = QueryBuilders.matchPhraseQuery(simpleQuery.getField(), simpleQuery.getValue());
        List<ResultData> results = resultRetriever.getResults(query);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/boolean", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchBoolean(@RequestBody AdvancedQuery advancedQuery) throws Exception {
        System.out.println("BOOLEN");
        System.out.println(advancedQuery.getField1()+" " + advancedQuery.getValue1());
        System.out.println(advancedQuery.getOperation());
        System.out.println(advancedQuery.getField2()+" " + advancedQuery.getValue2());

        org.elasticsearch.index.query.QueryBuilder query1 = QueryBuilders.matchQuery(advancedQuery.getField1(), advancedQuery.getValue1());
        org.elasticsearch.index.query.QueryBuilder query2 = QueryBuilders.matchQuery(advancedQuery.getField2(), advancedQuery.getValue2());

        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (advancedQuery.getOperation().equalsIgnoreCase("AND")) {
            builder.must(query1);
            builder.must(query2);
        } else if (advancedQuery.getOperation().equalsIgnoreCase("OR")) {
            builder.should(query1);
            builder.should(query2);
        } else if (advancedQuery.getOperation().equalsIgnoreCase("NOT")) {
            builder.must(query1);
            builder.mustNot(query2);
        }
        List<ResultData> results = resultRetriever.getResults(builder);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
