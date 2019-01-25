package ftn.uns.ac.rs.naucnacentrala.security;

import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    public static final String URI = "/login";

    public static String HEADER = "jwtoken";

    public static String PREFIX = "Bearer";

    public static String AUTH = "AUTH";

    public static String USER = "USER";

    public static long EXPIRATION = 31556952000L;

    public static String TRANSSECRKEY = "super_secred";
}
