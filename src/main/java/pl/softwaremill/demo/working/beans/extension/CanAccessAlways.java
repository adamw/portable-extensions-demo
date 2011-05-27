package pl.softwaremill.demo.working.beans.extension;

import pl.softwaremill.demo.working.beans.CanAccess;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class CanAccessAlways<T> implements CanAccess<T> {
    @Override
    public boolean canInvokeMethods() {
        return true;
    }
}
