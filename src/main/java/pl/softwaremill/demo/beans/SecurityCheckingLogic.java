package pl.softwaremill.demo.beans;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class SecurityCheckingLogic {
    public boolean checkAccess() {
        return Security.getUser().contains("admin");
    }
}
