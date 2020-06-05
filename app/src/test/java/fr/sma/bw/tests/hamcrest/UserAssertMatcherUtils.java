package fr.sma.bw.tests.hamcrest;

import fr.sma.svc.bw.service.model.User;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import static  org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;


public final class UserAssertMatcherUtils {

    private UserAssertMatcherUtils() {}
	public static void eachUserHasEmailContainsValue(final List<User> listeUtilisateurs, final String value) {
        assertThat(listeUtilisateurs,Every.everyItem(HasPropertyWithValue.hasProperty("email", containsString(value))));
    }
    public static void listContainsUsersInGivenOrder(final List<User> listeUtilisateurs, final Integer... items) {
        final Integer[] ids = listeUtilisateurs.stream().map(user -> user.getId()).toArray(Integer[]::new);
        assertThat(ids, arrayContaining(items));
    }
}
