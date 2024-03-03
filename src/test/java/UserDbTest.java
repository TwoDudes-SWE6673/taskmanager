import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.swe6673.user.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class UserDbTest {

    private Connection conn;
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String DB_USER = "user";
    private static final String DB_USER_EMAIL = "user@test.com";
    private static final String DB_PASSWORD = "pass";


    private static final String INIT_DB_SCHEMA =
            "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255))";

    @BeforeEach
    public void setUp() throws Exception {
        // Connect to H2 in-memory database
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        // Initialize the database schema
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(INIT_DB_SCHEMA);
        }
        conn.close();
    }


    @AfterEach
    public void tearDown() throws Exception {
        // Close all db connections
        conn.close();
    }



    @Test
    public void testUserRegistration() throws Exception {
        //Create new user
        User user = new User("test", "testPassword", "testUser@gmail.com");

        //check if client is registered and assign value
        boolean isregistered = User.register(user.getUsername(), user.getPassword(), user.getEmail());

        assertFalse(isregistered);
    }

    @Test
    void testUsernameIsLowercase() {
        // Given a username that includes uppercase letters
        String upperCasedUsername = "testUser";
        User user = new User(upperCasedUsername, "password", "testUser@example.com");

        // Then the username should be converted to lowercase
        String username = user.getUsername();
        assertEquals(username.toLowerCase(), username);
    }

    @Test
    public void testGetUserByName() {
        User user = new User("test", "testPassword", "testUser@gmail.com");
        User.register(user.getUsername(), user.getPassword(), user.getEmail());
        String username = user.getUsername();
        assertEquals("test", username);
    }


    @Test
    public void deleteUser() {
        User user = new User("test", "testPassword", "testUser@gmail.com");
        User.register(user.getUsername(), user.getPassword(), user.getEmail());
        User.deleteUser(user.getUsername());
        boolean existsAfterDelete = User.exists(user.getUsername());
        assertFalse(existsAfterDelete);
    }

    @Test
    public void addExistingUser() {
        User user = new User("test", "testPassword", "testUser@gmail.com");
        boolean isRegistered = User.register(user.getUsername(), user.getPassword(), user.getEmail());
        assertFalse(isRegistered);
    }

    @Test
    public void testUserLogin() throws Exception {
        User user = new User("test", "testPassword", "testUser@gmail.com");
        User.register(user.getUsername(), user.getPassword(), user.getEmail());
        boolean loginSuccess = User.login(user.getUsername(), user.getPassword());
        assertTrue(loginSuccess);
    }


    @Test
    public void testUserExists() {
        User user = new User("test", "testPassword", "testUser@gmail.com");
        User.register(user.getUsername(), user.getPassword(), user.getEmail());
        boolean exists = User.exists(user.getUsername());
        assertTrue(exists);
    }



}





