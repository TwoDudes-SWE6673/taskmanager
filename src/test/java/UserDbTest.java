import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.swe6673.user.User;

import javax.naming.InvalidNameException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        User user = new User("test", "testPassword", "testUser@gmail.com");

        // Assume a method in User class that registers a user
        User.register("test", "testPassword", "testUser@gmail.com");
        // Verify the user was inserted into the database
        assertTrue(User.exists("test"));
    }




    @Test
    public void testGetUserById() {

    }


    @Test
    public void testGetUserByName() {
    }



    @Test
    public void deleteUser() {

    }

    @Test
    public void connectToDatabase() {

    }

    @Test
    public void addExistingUser() {

    }

}





