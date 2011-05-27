package pl.softwaremill.demo.beans;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public interface CanAccess<T> {
    public boolean canInvokeMethods();
}
