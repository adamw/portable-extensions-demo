package pl.softwaremill.demo.working.beans;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.fest.assertions.Assertions.*;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class CanAccessExtensionTest extends Arquillian {
    @Deployment
    public static JavaArchive createTestArchive() {
        JavaArchive ar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(Secure.class.getPackage());

        // Creating beans.xml
        ar = ar.addManifestResource(
                new ByteArrayAsset(
                        ("<beans>" +
                                "<interceptors>" +
                                "<class>pl.softwaremill.demo.working.beans.SecureInterceptor</class></interceptors>" +
                                "</beans>").getBytes()),
                ArchivePaths.create("beans.xml"));

        return ar;
    }

    @Inject
    private CanAccess<PublicDataHolder> canAccessPublic;

    @Inject
    private CanAccess<SecretDataHolder> canAccessSecret;

    @Test
    public void testPublicAsUser() {
        Security.login("me");
        assertThat(canAccessPublic.canInvokeMethods()).isTrue();
    }

    @Test
    public void testPublicAsAdmin() {
        Security.login("admin");
        assertThat(canAccessPublic.canInvokeMethods()).isTrue();
    }

    @Test
    public void testSecretAsUser() {
        Security.login("me");
        assertThat(canAccessSecret.canInvokeMethods()).isFalse();
    }

    @Test
    public void testSecretAsAdmin() {
        Security.login("admin");
        assertThat(canAccessSecret.canInvokeMethods()).isTrue();
    }
}
