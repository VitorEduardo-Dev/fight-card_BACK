package DB;

import DB.exceptions.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbConnection {

    private static Connection conn = null;

    public static Connection getConnection() {
        if(conn == null) {
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	
                Properties props = propsConnection();
                System.out.println("Tentando conectar com o usuário: " + props.getProperty("user"));
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch(SQLException | ClassNotFoundException e) {
            	e.printStackTrace();
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    private static Properties propsConnection() {
    	Properties props = new Properties();
        try(InputStream is = DbConnection.class.getClassLoader().getResourceAsStream("properties-config.properties")) {
        	if(is == null) {
        		throw new IOException("File properties-config.properties not found in classpath");
        	}
            
            props.load(is);
            return props;
        } catch (IOException e) {
        	e.printStackTrace();
            throw new DbException(e.getMessage());
        }
    }

    public static void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            	e.printStackTrace();
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(PreparedStatement st) {
        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
            	e.printStackTrace();
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            	e.printStackTrace();
                throw new DbException(e.getMessage());
            }
        }
    }
}
