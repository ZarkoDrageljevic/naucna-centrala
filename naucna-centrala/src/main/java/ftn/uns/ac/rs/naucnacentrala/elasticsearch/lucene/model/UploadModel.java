package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class UploadModel {

    private String keywords;

    private String authorName;

    private String magazine;

    private String scientificField;

    private MultipartFile[] files;

    private String apstrakt;

    private String authors;

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getApstrakt() {
        return apstrakt;
    }

    public void setApstrakt(String apstrakt) {
        this.apstrakt = apstrakt;
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

    public String getScientificField() {
        return scientificField;
    }

    public void setScientificField(String scientificField) {
        this.scientificField = scientificField;
    }


    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                ", keywords='" + keywords + '\'' +
                ", authorName='" + authorName + '\'' +
                ", magazine='" + magazine + '\'' +
                ", scientificField='" + scientificField + '\'' +
                ", files=" + Arrays.toString(files) +
                '}';
    }

}
