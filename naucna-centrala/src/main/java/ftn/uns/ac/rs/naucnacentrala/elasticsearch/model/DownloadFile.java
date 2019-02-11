package ftn.uns.ac.rs.naucnacentrala.elasticsearch.model;

public class DownloadFile {
    private String fileName;

    public DownloadFile() {
    }

    public DownloadFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
