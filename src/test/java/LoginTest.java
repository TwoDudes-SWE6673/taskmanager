import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTest {
    // TODO real login service
    private boolean authenticate(String username, String password) {
        // sample account
        return username.equals("test123") && password.equals("123");
    }

    @Test
    public void testLoginWithCorrectCredentials() {
        String username = "test123";
        String password = "123";

        boolean isAuthenticated = authenticate(username, password);

        assertTrue(isAuthenticated, "Authentication failed with correct credentials");
    }

    @Test
    public void testLoginWithIncorrectEmail() {
        String username = "test112233";
        String password = "123";

        boolean isAuthenticated = authenticate(username, password);

        assertFalse(isAuthenticated, "Authentication succeeded with incorrect credentials");
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        String username = "test123";
        String password = "1234";

        boolean isAuthenticated = authenticate(username, password);

        assertFalse(isAuthenticated, "Authentication succeeded with incorrect credentials");
    }

    @Test
    public void testLoginWithIncorrectAll() {
        String username = "test1233";
        String password = "1234";

        boolean isAuthenticated = authenticate(username, password);
        assertFalse(isAuthenticated, "Authentication succeeded with incorrect credentials");
    }
}
