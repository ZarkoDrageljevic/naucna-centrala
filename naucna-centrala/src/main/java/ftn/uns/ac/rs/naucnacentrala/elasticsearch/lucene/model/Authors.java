package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model;

public class Authors {
    private String firstname;
    private String lastname;

    public Authors() {
    }

    public Authors(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

