package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;


@Document(indexName = "naucnacentrala", type = "paper")
@Setting(settingPath = "/settings.json")
@Mapping(mappingPath = "/mappings.json")
public class IndexUnit {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @Field(type = FieldType.Text, store = true)
    private String text;
    @Field(type = FieldType.Text, store = true)
    private String title;
    @Field(type = FieldType.Text, store = true)
    private String keywords;
    @Id
    @Field(type = FieldType.Text, store = false)
    private String filename;
    @Field(type = FieldType.Text, store = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private String filedate;

    @Field(type = FieldType.Text, store = true)
    private String authorName;
    @Field(type = FieldType.Text, store = true)
    private String magazine;
    @Field(type = FieldType.Text, store = true)
    private String scienceField;

    private String highlights;

    @Field(type = FieldType.Nested, store = true)
    private List<Authors> authors;

    @Field(type = FieldType.Text, store = true)
    private String apstract;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiledate() {
        return filedate;
    }

    public void setFiledate(String filedate) {
        this.filedate = filedate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public String getScienceField() {
        return scienceField;
    }

    public void setScienceField(String scienceField) {
        this.scienceField = scienceField;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    public String getApstract() {
        return apstract;
    }

    public void setApstract(String apstract) {
        this.apstract = apstract;
    }
}
