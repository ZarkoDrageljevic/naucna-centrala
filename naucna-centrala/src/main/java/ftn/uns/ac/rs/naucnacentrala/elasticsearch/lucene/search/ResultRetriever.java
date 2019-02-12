package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.search;

import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing.handlers.DocumentHandler;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing.handlers.PDFHandler;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.Authors;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.IndexUnit;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.ResultData;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.repository.PaperRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultRetriever {

    ElasticsearchTemplate elasticsearchTemplate;
    private PaperRepository paperRepository;

    @Autowired
    public ResultRetriever(PaperRepository paperRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.paperRepository = paperRepository;
    }

    public List<ResultData> getResults(QueryBuilder query) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withHighlightFields(new HighlightBuilder.Field("text"))
                .build();

        Page<IndexUnit> papers = elasticsearchTemplate.queryForPage(searchQuery, IndexUnit.class, new SearchResultMapper() {

            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> clazz, Pageable pageable) {
                List<IndexUnit> paperIndexUnits = new ArrayList<IndexUnit>();
                for (SearchHit searchHit : searchResponse.getHits()) {
                    if (searchResponse.getHits().getHits().length <= 0) {
                        return null;
                    }
                    IndexUnit resultData = extractData(searchHit);
                    paperIndexUnits.add(resultData);
                }

                if (paperIndexUnits.size() > 0) {
                    return new AggregatedPageImpl<T>((List<T>) paperIndexUnits);
                }
                return null;
            }

            private IndexUnit extractData(SearchHit searchHit) {
                IndexUnit resultData = new IndexUnit();
                resultData.setFilename((String) searchHit.getSource().get("filename"));
                resultData.setTitle((String) searchHit.getSource().get("title"));
                resultData.setKeywords((String) searchHit.getSource().get("keywords"));
                resultData.setMagazine((String) searchHit.getSource().get("magazine"));
                resultData.setText((String) searchHit.getSource().get("text"));
                resultData.setAuthorName((String) searchHit.getSource().get("authorName"));
                resultData.setScienceField((String) searchHit.getSource().get("scienceField"));
                resultData.setApstract((String) searchHit.getSource().get("apstract"));
                resultData.setAuthors((List<Authors>) searchHit.getSource().get("authors"));
                if (searchHit.getHighlightFields() != null) {
                    resultData.setHighlights(extractHighlights(searchHit));
                }
                return resultData;
            }

            private String extractHighlights(SearchHit searchHit) {
                StringBuilder highlights = new StringBuilder("...");
                if (searchHit.getHighlightFields().get("text") != null) {
                    Text[] text = searchHit.getHighlightFields().get("text").fragments();
                    for (Text t : text) {
                        highlights.append(t.toString());
                        highlights.append("...");
                    }
                }
                if (searchHit.getHighlightFields().get("title") != null) {
                    Text[] text = searchHit.getHighlightFields().get("naslovRada").fragments();
                    for (Text t : text) {
                        highlights.append(t.toString());
                        highlights.append("...");
                    }
                }
                if (searchHit.getHighlightFields().get("magazine") != null) {
                    Text[] text = searchHit.getHighlightFields().get("nazivCasopisa").fragments();
                    for (Text t : text) {
                        highlights.append(t.toString());
                        highlights.append("...");
                    }
                }
                if (searchHit.getHighlightFields().get("keywords") != null) {
                    Text[] text = searchHit.getHighlightFields().get("keywords").fragments();
                    for (Text t : text) {
                        highlights.append(t.toString());
                        highlights.append("...");
                    }
                }
                return highlights.toString();
            }

        });
        List<ResultData> response = new ArrayList<>();
        if (papers != null) {

            for (IndexUnit index : papers) {
                ResultData resultData = new ResultData(index);
                response.add(resultData);
            }
        }
        return response;
    }

    protected DocumentHandler getHandler(String fileName) {
        if (fileName.endsWith(".pdf")) {
            return new PDFHandler();
        } else {
            return null;
        }
    }
}