package ftn.uns.ac.rs.naucnacentrala.elasticsearch.model;

public class DeleteRequest {

    private String filename;

    public DeleteRequest() {
    }

    public DeleteRequest(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
