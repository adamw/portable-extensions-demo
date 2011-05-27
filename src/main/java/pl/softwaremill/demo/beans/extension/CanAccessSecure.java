package pl.softwaremill.demo.beans.extension;

import pl.softwaremill.demo.beans.CanAccess;
import pl.softwaremill.demo.beans.SecurityCheckingLogic;

import javax.inject.Inject;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class CanAccessSecure<T> implements CanAccess<T> {
    @Inject
    private SecurityCheckingLogic securityCheckingLogic;

    @Override
    public boolean canInvokeMethods() {
        return securityCheckingLogic.checkAccess();
    }
}
