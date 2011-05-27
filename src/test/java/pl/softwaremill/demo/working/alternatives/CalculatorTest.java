package pl.softwaremill.demo.working.alternatives;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class CalculatorTest extends Arquillian {
    @Deployment
    public static JavaArchive createTestArchive() {
        JavaArchive ar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(Operation.class.getPackage());

        // Creating beans.xml
        ar = ar.addManifestResource(
                new ByteArrayAsset("<beans></beans>".getBytes()),
                ArchivePaths.create("beans.xml"));

        return ar;
    }

    @Inject
    private Calculator calculator;

    @Test
    public void testRun() {
        calculator.run();
    }
}

