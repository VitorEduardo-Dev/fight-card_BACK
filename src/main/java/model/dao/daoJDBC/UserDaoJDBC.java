package model.dao.daoJDBC;

import DB.DbConnection;
import DB.exceptions.DbException;
import model.dao.UserDao;
import model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements UserDao {
    private Connection conn;
    
    public UserDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(User user) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO tb_user (name_user, password_user, email_user)" +
                    "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if(rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);
                }

                DbConnection.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbConnection.closeStatement(st);
        }
    }

    @Override
    public void update(User user) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE tb_user SET name_user = ?, password_user = ?, email_user = ? " +
                    "WHERE id_user = ?");

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbConnection.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM tb_user WHERE id_user = ?");

            st.setInt(1, id);

            int rows = st.executeUpdate();
            if(rows == 0) {
                throw new DbException("Id not exists.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public User findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_user WHERE id_user = ?");

            st.setInt(1, id);

            rs = st.executeQuery();
            
            if(rs.next()) {
                user = instantiateUser(rs);
            }
            
            return user;
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {;
            DbConnection.closeStatement(st);
            DbConnection.closeResultSet(rs);
        }
    }

    private User instantiateUser (ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id_user"));
        user.setUsername(rs.getString("name_user"));
        user.setPassword(rs.getString("password_user"));
        user.setEmail(rs.getString("email_user"));

        return user;
    }

    public List<User> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_user ORDER BY id_user");

            rs = st.executeQuery();
            List<User> userList =  new ArrayList<>();

            while(rs.next()) {
                User user = instantiateUser(rs);

                userList.add(user);
            }

            return userList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbConnection.closeStatement(st);
            DbConnection.closeResultSet(rs);
        }
    }
}
