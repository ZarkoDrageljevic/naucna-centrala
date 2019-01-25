package ftn.uns.ac.rs.naucnacentrala.security;

public class JwtUser {
    String user;

    public JwtUser() {
    }

    public JwtUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
