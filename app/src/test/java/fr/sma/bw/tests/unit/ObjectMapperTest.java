package fr.sma.bw.tests.unit;


import fr.sma.svc.bw.service.model.User;
import fr.sma.bw.mappers.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static  org.hamcrest.MatcherAssert.assertThat;


@Slf4j
public class ObjectMapperTest {

    @Test
    public void testMmapAll() {
    log.info("Conversion");
    final String flux = "{\"id\": 1,\"firstName\": \"KARIM\",\"lastName\": \"ZOUBA\",\"email\": \"karim_zouba@groupe-sma.fr\"}";
    final User user  = ObjectMapperUtils.mapJson(flux, User.class);
    assertThat("L'email de l'utilisateur doit contenir le mot sma",user.getEmail(), containsString("sma"));

    }
}
