package main.java.fight.model.dao;

import main.java.fight.DB.DbConnection;
import main.java.fight.model.dao.daoJDBC.UserDaoJDBC;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoJDBC(DbConnection.getConnection());
    }
}
