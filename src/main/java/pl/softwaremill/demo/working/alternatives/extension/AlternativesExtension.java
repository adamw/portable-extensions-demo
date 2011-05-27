package pl.softwaremill.demo.working.alternatives.extension;

import com.google.common.io.Resources;
import org.jboss.seam.solder.reflection.annotated.AnnotatedTypeBuilder;
import pl.softwaremill.demo.working.alternatives.Addition;
import pl.softwaremill.demo.working.alternatives.Multiplication;
import pl.softwaremill.demo.working.alternatives.Operation;
import pl.softwaremill.demo.working.alternatives.Subtraction;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.util.AnnotationLiteral;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class AlternativesExtension implements Extension {
    private Class<? extends Operation> opClass;

    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery beforeBeanDiscovery) throws IOException {
        switch(Resources.readLines(Resources.getResource("operation-config.txt"), Charset.defaultCharset()).get(0).charAt(0)) {
            case '+': opClass = Addition.class; break;
            case '-': opClass = Subtraction.class; break;
            case '*': opClass = Multiplication.class; break;
        }
    }

    public <X> void processAnnotatedType(@Observes ProcessAnnotatedType<X> event) {
        AnnotatedType<X> annotatedType = event.getAnnotatedType();

        System.out.println("Processing " + annotatedType);

        if (Operation.class.isAssignableFrom(annotatedType.getJavaClass())) {
            if (annotatedType.getJavaClass().equals(opClass)) {
                removeAlternative(event);
            } else {
                addAlternative(event);
            }
        }
    }

    private <X> void addAlternative(ProcessAnnotatedType<X> event) {
        System.out.println("Removing @Alternative from " + event.getAnnotatedType());

        AnnotatedTypeBuilder<X> builder = new AnnotatedTypeBuilder<X>().readFromType(event.getAnnotatedType());
        builder.addToClass(new AlternativeImpl());
        event.setAnnotatedType(builder.create());
    }

    private <X> void removeAlternative(ProcessAnnotatedType<X> event) {
        System.out.println("Adding @Alternative to " + event.getAnnotatedType());

        AnnotatedTypeBuilder<X> builder = new AnnotatedTypeBuilder<X>().readFromType(event.getAnnotatedType());
        builder.removeFromClass(Alternative.class);
        event.setAnnotatedType(builder.create());
    }

    private static class AlternativeImpl extends AnnotationLiteral<Alternative> implements Alternative {};
}
