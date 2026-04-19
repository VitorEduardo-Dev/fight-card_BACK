package main.java.fight.DB;

import main.java.fight.DB.exceptions.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private static Connection conn = null;

    public static Connection getConnection() {
        if(conn == null) {
            try {
                Properties props = new Properties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch(SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    private static Properties propsConnection() {
        try(FileInputStream fs = new FileInputStream("properties.config")) {
                Properties props = new Properties();
                props.load(fs);
                return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
