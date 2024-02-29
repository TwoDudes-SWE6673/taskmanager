package org.swe6673;

import org.swe6673.db.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DatabaseConnection db = new DatabaseConnection();
        db.fetchData();
    }
}