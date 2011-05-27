package pl.softwaremill.demo.beans.extension;

import org.jboss.seam.solder.reflection.annotated.AnnotatedTypeBuilder;
import org.jboss.weld.literal.DefaultLiteral;
import org.jboss.weld.util.reflection.ParameterizedTypeImpl;
import pl.softwaremill.demo.beans.CanAccess;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class CanAccessSecureBean implements Bean<CanAccessSecure> {
    private final Class<?> wrappedClass;
    private final BeanManager beanManager;

    public CanAccessSecureBean(Class<?> wrappedClass, BeanManager beanManager) {
        this.wrappedClass = wrappedClass;
        this.beanManager = beanManager;
    }

    @Override
    public CanAccessSecure create(CreationalContext<CanAccessSecure> creationalContext) {
        CanAccessSecure instance = new CanAccessSecure();
        AnnotatedType<CanAccessSecure> canAccessSecureType =
                new AnnotatedTypeBuilder<CanAccessSecure>().readFromType(CanAccessSecure.class).create();
        beanManager.createInjectionTarget(canAccessSecureType).inject(instance, creationalContext);

        return instance;
    }

    @Override
    public Set<Type> getTypes() {
        return Collections.<Type>singleton(new ParameterizedTypeImpl(
                CanAccess.class,
                new Type[] { wrappedClass },
                null));
    }

    @Override
    public void destroy(CanAccessSecure instance, CreationalContext<CanAccessSecure> creationalContext) { }

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
