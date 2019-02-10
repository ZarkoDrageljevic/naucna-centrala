package ftn.uns.ac.rs.naucnacentrala.elasticsearch.model;

public class Naucnik {

    private String name;

    private String lastname;

    private String field;

    private String employee;

    public Naucnik() {
    }

    public Naucnik(String name, String lastname, String field, String employee) {
        this.name = name;
        this.lastname = lastname;
        this.field = field;
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
