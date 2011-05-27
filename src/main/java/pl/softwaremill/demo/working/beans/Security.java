package pl.softwaremill.demo.working.beans;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class Security {
    private static String user;

    public static void login(String user) {
        Security.user = user;
    }

    public static String getUser() {
        return user;
    }
}
