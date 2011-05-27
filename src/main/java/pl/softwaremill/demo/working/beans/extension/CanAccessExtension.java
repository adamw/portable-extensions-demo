package pl.softwaremill.demo.working.beans.extension;

import pl.softwaremill.demo.working.beans.Secure;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class CanAccessExtension implements Extension {
    private Set<Bean> beansToAdd = new HashSet<Bean>();

    public <X> void processAnnotatedType(@Observes ProcessAnnotatedType<X> event, BeanManager beanManager) {
        Secure secureAnnotation = event.getAnnotatedType().getAnnotation(Secure.class);
        if (secureAnnotation != null) {
            beansToAdd.add(new CanAccessSecureBean(event.getAnnotatedType().getJavaClass(), beanManager));
        } else {
            beansToAdd.add(new CanAccessAlwaysBean(event.getAnnotatedType().getJavaClass()));
        }
    }

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
        for (Bean bean : beansToAdd) {
            abd.addBean(bean);
        }
    }
}
