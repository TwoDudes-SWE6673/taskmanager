package org.swe6673.user;

public class User {
    private String username;
    private String password;
    private String email;


    public User(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static boolean exists( String testUser) {
        return true;
    }

    public static boolean validateEmail(String email) {
        return true;
    }

    public static boolean validateUsername(String username) {
        return true;
    }


    public static boolean register(String username, String password, String email) {

        //Check if user is present in DB
        if (exists(username)){
            return true;
        }
        return false; // Return true if registration is successful
    }

    public static boolean login(String username, String password) {
        // Retrieve user from database
        // Compare hashed passwords
        return true; // Return true if login is successful
    }

    public static boolean resetPassword(String username, String newPassword) {
        return true;
    }

    public static void deleteUser(String username) {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



