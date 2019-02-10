package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;


@Document(indexName = "naucnacentrala", type = "book")
@Setting(settingPath = "/settings.json")
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
    private String authorLastName;
    @Field(type = FieldType.Text, store = true)
    private String magazine;
    @Field(type = FieldType.Text, store = true)
    private String scienceField;


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
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

}
