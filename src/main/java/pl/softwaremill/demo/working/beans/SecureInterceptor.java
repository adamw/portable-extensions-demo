package pl.softwaremill.demo.working.beans;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author Adam Warski (adam at warski dot org)
 */
@Interceptor
@Secure
public class SecureInterceptor {
    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        if (!Security.getUser().contains("admin")) {
            throw new RuntimeException("HACKER !!!!");
        }

        return ctx.proceed();
    }
}
