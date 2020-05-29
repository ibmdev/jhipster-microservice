package fr.sma.bw.tests.hamcrest;

import fr.sma.svc.bw.service.model.User;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import static  org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;


public class UserAssertMatcher {

    public  static void eachUserHasEmailContainsValue(List<User> listeUtilisateurs, String value) {
        assertThat(listeUtilisateurs, (Every.everyItem(HasPropertyWithValue.hasProperty("email", containsString(value)))));
    }
    public static void listContainsUsersInGivenOrder(List<User> listeUtilisateurs, Integer... items) {
        Integer[] ids = listeUtilisateurs.stream().map(user -> user.getId()).toArray(Integer[]::new);
        assertThat(ids, arrayContaining(items));
    }
}
