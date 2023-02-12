package com.hippotech.api.data;

import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BlogAnalyticsRepository {
    private Logger log;
    private static Connection connection;

    private static Integer nextId = 1;

    public BlogAnalyticsRepository(Logger log) {
        this.log = log;
    }

    public void addSubscriberToBlogStatQueue(String email) {
        String jdbcURL = "jdbc:h2:mem:test";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL);

            log.error("Connected to H2 in-memory database.");

            String sql;
            Statement statement = connection.createStatement();

            sql = "CREATE TABLE IF NOT EXISTS subscribers (ID int primary key, email varchar(500))";
            statement.execute(sql);
            log.error("Created subscriber table.");

            sql = "INSERT INTO subscribers (ID, email) values (" + nextId.toString() + ", '" + email + "')";
            nextId++;
            log.error(sql);
            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                log.error("Inserted a new row.");
            }
            connection.close();
        } catch (Exception e) {
            log.error("Exception thrown processing blog analytics");
            log.error(e.toString());
        }
    }
}