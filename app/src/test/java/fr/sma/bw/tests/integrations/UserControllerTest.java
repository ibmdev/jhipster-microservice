package fr.sma.bw.tests.integrations;

import fr.sma.bw.constants.EnumConstants;
import fr.sma.bw.mappers.ObjectMapperUtils;
import fr.sma.bw.tests.hamcrest.UserAssertMatcherUtils;
import fr.sma.svc.bw.service.model.ListUserResponse;
import fr.sma.svc.bw.service.model.User;
import lombok.extern.slf4j.Slf4j;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@Slf4j
public class UserControllerTest {

    @LocalServerPort
    private int port;
    final private TestRestTemplate restTemplate = new TestRestTemplate();
    private final static String API_USER_SERVICE = "/api/users";
    

    @Test
    public void testRetrieveUser() throws Exception {
        log.info("Test testRetrieveUser");
        final ResponseEntity<ListUserResponse> response =
        restTemplate.getForEntity(createURLWithPort(API_USER_SERVICE),ListUserResponse.class);
        final List<User> listeUtilisateurs = ObjectMapperUtils.mapAll(response.getBody(), User.class);
        final ResponseEntity<List> journaux = restTemplate.getForEntity(createURLWithPort(EnumConstants.SERVICE_JOURNAUX_ENDPOINT),List.class);
        if(log.isInfoEnabled()) {
        	log.info("Résultat appel service :  " + response.getBody());
        	log.info("Nombre d'utilisateurs : " + listeUtilisateurs.size());
        	log.info("Contenu de la table de Journaux : " + journaux.getBody());
        }
        assertThat("Vérifier que tous les emails contiennent le mot sma", listeUtilisateurs, Every.everyItem(HasPropertyWithValue.hasProperty("email", containsString("sma"))));
        UserAssertMatcherUtils.eachUserHasEmailContainsValue(listeUtilisateurs, "sma");
        UserAssertMatcherUtils.listContainsUsersInGivenOrder(listeUtilisateurs, 1,2,3);
    }
    private String createURLWithPort(final String uri) {
        return "http://localhost:" + getPort() + uri;
    }
	public int getPort() {
		return port;
	}
	public void setPort(final int port) {
		this.port = port;
	}
    
}
