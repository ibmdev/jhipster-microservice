package fr.sma.bw.tests.integrations;

import fr.sma.bw.mappers.ObjectMapperUtils;
import fr.sma.bw.tests.hamcrest.UserAssertMatcher;
import fr.sma.svc.bw.service.model.ListUserResponse;
import fr.sma.svc.bw.service.model.User;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@Slf4j
public class UserControllerTests {

    @LocalServerPort
    private int port;
    private transient TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    private final static String API_USER_SERVICE = "/api/users";

    @Test
    public void testRetrieveUser() throws Exception {
        log.info("Test testRetrieveUser");
        ResponseEntity<ListUserResponse> response =
        restTemplate.getForEntity(createURLWithPort(API_USER_SERVICE),ListUserResponse.class);
        List<User> listeUtilisateurs = ObjectMapperUtils.mapAll(response.getBody(), User.class);
        log.info("Résultat appel service :  " + response.getBody());
        log.info("Nombre d'utilisateurs : " + listeUtilisateurs.size());
        UserAssertMatcher.eachUserHasEmailContainsValue(listeUtilisateurs, "sma");
        UserAssertMatcher.listContainsUsersInGivenOrder(listeUtilisateurs, 1,2,3);
        // String expected = " [{\"id\":1,\"firstName\":\"KARIM\",\"lastName\":\"ZOUBA\",\"email\":\"karim_zouba@groupe-sma.fr\"}]";
        // JSONAssert.assertEquals(expected, response.getBody(), true);

    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + getPort() + uri;
    }
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
    
}
