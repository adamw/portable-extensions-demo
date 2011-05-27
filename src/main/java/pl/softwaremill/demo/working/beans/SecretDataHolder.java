package pl.softwaremill.demo.working.beans;

/**
 * @author Adam Warski (adam at warski dot org)
 */
@Secure
public class SecretDataHolder {
    public String getData() {
        return "secret";
    }
}
