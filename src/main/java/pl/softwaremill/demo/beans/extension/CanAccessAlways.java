package pl.softwaremill.demo.beans.extension;

import pl.softwaremill.demo.beans.CanAccess;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class CanAccessAlways<T> implements CanAccess<T> {
    @Override
    public boolean canInvokeMethods() {
        return true;
    }
}
