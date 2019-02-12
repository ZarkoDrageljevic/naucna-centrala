package ftn.uns.ac.rs.naucnacentrala.elasticsearch.controller;


import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.AdvancedQuery;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.ResultData;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.SimpleQuery;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.search.ResultRetriever;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@RestController
public class SearchController {

    @Autowired
    private ResultRetriever resultRetriever;

    @PostMapping(value = "/search/match", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchMatchQuery(@RequestBody SimpleQuery simpleQuery) throws Exception {

        System.out.println("SIMPLE");
        System.out.println(simpleQuery.getField() + " " + simpleQuery.getValue());
        org.elasticsearch.index.query.QueryBuilder query;
        if (simpleQuery.getField().equalsIgnoreCase("authors")) {
            query = authorsQuery(simpleQuery);
        } else {
            query = matchQuery(simpleQuery.getField(), simpleQuery.getValue());
        }
        List<ResultData> results = resultRetriever.getResults(query);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/phrase", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchPhrase(@RequestBody SimpleQuery simpleQuery) throws Exception {
        System.out.println("PHRAZE");
        System.out.println(simpleQuery.getField() + " " + simpleQuery.getValue());
        org.elasticsearch.index.query.QueryBuilder query;
        if (simpleQuery.getField().equalsIgnoreCase("authors")) {
            query = authorsPhrazeQuery(simpleQuery);
        } else {
            query = matchPhraseQuery(simpleQuery.getField(), simpleQuery.getValue());
        }
        List<ResultData> results = resultRetriever.getResults(query);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/boolean", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchBoolean(@RequestBody AdvancedQuery advancedQuery) throws Exception {
        System.out.println("BOOLEN");
        System.out.println(advancedQuery.getField1() + " " + advancedQuery.getValue1());
        System.out.println(advancedQuery.getOperation());
        System.out.println(advancedQuery.getField2() + " " + advancedQuery.getValue2());


        //org.elasticsearch.index.query.QueryBuilder query1 = matchQuery(advancedQuery.getField1(), advancedQuery.getValue1());
        //org.elasticsearch.index.query.QueryBuilder query2 = matchQuery(advancedQuery.getField2(), advancedQuery.getValue2());

        org.elasticsearch.index.query.QueryBuilder query1;
        if (advancedQuery.getField1().equalsIgnoreCase("authors")) {
            query1 = authorsBoolQuery(advancedQuery.getValue1());
        } else {
            query1 = matchQuery(advancedQuery.getField1(), advancedQuery.getValue1());
        }
        org.elasticsearch.index.query.QueryBuilder query2;
        if (advancedQuery.getField2().equalsIgnoreCase("authors")) {
            query2 = authorsBoolQuery(advancedQuery.getValue2());
        } else {
            query2 = matchQuery(advancedQuery.getField2(), advancedQuery.getValue2());
        }


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

    private QueryBuilder authorsBoolQuery(String value1) {
        System.out.println("Author Search");
        NestedQueryBuilder retval = QueryBuilders.nestedQuery(
                "authors", QueryBuilders.boolQuery()
                        .should(matchQuery("authors.firstname", value1))
                        .should(matchQuery("authors.lastname", value1)), ScoreMode.Avg);


        return retval;
    }

    private NestedQueryBuilder authorsQuery(SimpleQuery simpleQuery) {
        System.out.println("Author Search");
        NestedQueryBuilder retval = QueryBuilders.nestedQuery(
                "authors", QueryBuilders.boolQuery()
                        .should(matchQuery("authors.firstname", simpleQuery.getValue()))
                        .should(matchQuery("authors.lastname", simpleQuery.getValue())), ScoreMode.Avg);


        return retval;
    }

    private NestedQueryBuilder authorsPhrazeQuery(SimpleQuery simpleQuery) {
        System.out.println("Author Search");
        NestedQueryBuilder retval = QueryBuilders.nestedQuery(
                "authors", QueryBuilders.boolQuery()
                        .should(matchPhraseQuery("authors.firstname", simpleQuery.getValue()))
                        .should(matchPhraseQuery("authors.lastname", simpleQuery.getValue())), ScoreMode.Avg);


        return retval;
    }

}
