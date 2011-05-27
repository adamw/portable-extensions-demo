package pl.softwaremill.demo.beans;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author Adam Warski (adam at warski dot org)
 */
@Interceptor
@Secure
public class SecureInterceptor {
    @Inject
    private SecurityCheckingLogic securityCheckingLogic;

    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        if (!securityCheckingLogic.checkAccess()) {
            throw new RuntimeException("HACKER !!!!");
        }

        return ctx.proceed();
    }
}
