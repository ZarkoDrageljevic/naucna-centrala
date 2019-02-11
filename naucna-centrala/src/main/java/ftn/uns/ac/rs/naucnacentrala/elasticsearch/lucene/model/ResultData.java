package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model;

public final class ResultData {

    private String title;
    private String keywords;
    private String location;
    private String highlight;
    private String filedate;
    private String authorName;
    private String authorLastName;
    private String magazine;
    private String scienceField;
    private String text;
    private String apstract;

    public ResultData(String title, String keywords, String location,
                      String highlight, String filedate, String authorName,
                      String authorLastName, String magazine, String scienceField, String text, String apstract) {
        this.title = title;
        this.keywords = keywords;
        this.location = location;
        this.highlight = highlight;
        this.filedate = filedate;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.magazine = magazine;
        this.scienceField = scienceField;
        this.text = text;
        this.apstract = apstract;
    }

    public ResultData() {
        super();
    }

    public ResultData(IndexUnit index) {
        this.title = index.getTitle();
        this.keywords = index.getKeywords();
        this.location = index.getFilename();
        this.highlight = index.getHighlights();
        this.filedate = index.getFiledate();
        this.authorName = index.getAuthorName();
        this.authorLastName = index.getAuthorLastName();
        this.magazine = index.getMagazine();
        this.scienceField = index.getScienceField();
        this.text = index.getText();
        this.apstract = index.getApstract();
    }

    public String getApstract() {
        return apstract;
    }

    public void setApstract(String apstract) {
        this.apstract = apstract;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

}
