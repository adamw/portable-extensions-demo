package pl.softwaremill.demo.beans;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.fest.assertions.Assertions.*;
import static org.testng.Assert.*;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class DataHolderTest extends Arquillian {
    @Deployment
    public static JavaArchive createTestArchive() {
        JavaArchive ar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(Secure.class.getPackage());

        // Creating beans.xml
        ar = ar.addManifestResource(
                new ByteArrayAsset(
                        ("<beans>" +
                                "<interceptors>" +
                                "<class>pl.softwaremill.demo.beans.SecureInterceptor</class></interceptors>" +
                                "</beans>").getBytes()),
                ArchivePaths.create("beans.xml"));

        return ar;
    }

    @Inject
    private PublicDataHolder publicDataHolder;

    @Inject
    private SecretDataHolder secretDataHolder;

    @Test
    public void testPublicAsUser() {
        Security.login("me");
        assertThat(publicDataHolder.getData()).isEqualTo("public");
    }

    @Test
    public void testPublicAsAdmin() {
        Security.login("admin");
        assertThat(publicDataHolder.getData()).isEqualTo("public");
    }

    @Test
    public void testSecretAsUser() {
        Security.login("me");
        try {
            secretDataHolder.getData();
            fail();
        } catch (Exception e) {
            // Ok
        }
    }

    @Test
    public void testSecretAsAdmin() {
        Security.login("admin");
        assertThat(secretDataHolder.getData()).isEqualTo("secret");
    }
}
