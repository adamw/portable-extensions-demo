package pl.softwaremill.demo.beans.extension;

import org.jboss.weld.literal.DefaultLiteral;
import org.jboss.weld.util.reflection.ParameterizedTypeImpl;
import pl.softwaremill.demo.beans.CanAccess;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class CanAccessAlwaysBean implements Bean<CanAccess> {
    private final Class<?> wrappedClass;

    public CanAccessAlwaysBean(Class<?> wrappedClass) {
        this.wrappedClass = wrappedClass;
    }

    @Override
    public CanAccess create(CreationalContext<CanAccess> creationalContext) {
        return new CanAccessAlways();
    }

    @Override
    public Set<Type> getTypes() {
        return Collections.<Type>singleton(new ParameterizedTypeImpl(
                CanAccess.class,
                new Type[] { wrappedClass },
                null));
    }

    @Override
    public void destroy(CanAccess instance, CreationalContext<CanAccess> creationalContext) { }

    @Override
    public Set<Annotation> getQualifiers() {
        return Collections.<Annotation>singleton(DefaultLiteral.INSTANCE);
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return Dependent.class;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return Collections.emptySet();
    }

    @Override
    public Class<?> getBeanClass() {
        return CanAccess.class;
    }

    @Override
    public boolean isAlternative() {
        return false;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return Collections.emptySet();
    }
}
