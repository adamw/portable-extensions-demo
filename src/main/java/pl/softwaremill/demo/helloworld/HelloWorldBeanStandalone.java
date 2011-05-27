package pl.softwaremill.demo.helloworld;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import pl.softwaremill.common.cdi.util.BeanInject;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class HelloWorldBeanStandalone {
    public static void main(String[] args) {
        WeldContainer weld = new Weld().initialize();
        BeanInject.lookup(weld.getBeanManager(), HelloWorldBean.class).printMessage();
    }
}
