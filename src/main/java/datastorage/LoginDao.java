package datastorage;

import model.Caregiver;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDao extends DAOimp<User>{

    public LoginDao(Connection conn) {
        super(conn);
    }


    @Override
    protected String getCreateStatementString(User user) {
        return String.format("INSERT INTO login (user, passwordhash, salt) VALUES ('%s', '%s', '%s')",
                user.getUser(), user.getPasswordHash(), user.getSalt());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM login WHERE lid = %d", key);
    }

    @Override
    protected User getInstanceFromResultSet(ResultSet resultSet) throws SQLException {
        User user = null;
        user = new User(resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getInt(1));
        return user;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM login";
    }

    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User(resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4),resultSet.getInt(1));
            list.add(user);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(User user) {

        return String.format("UPDATE login SET User = '%s', passwordhash = '%s', salt = '%s' WHERE lid = '%s' ",
                user.getUser(), user.getPasswordHash(), user.getSalt(), user.getUserID());
    }

    protected String getDeleteStatementString(long key) {

        return String.format("Delete FROM login WHERE lid=%d", key);
    }

}
