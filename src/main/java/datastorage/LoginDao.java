package datastorage;

import model.Caregiver;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasse LoginDao erbt von DAOimp
 * Schnittstelle zwischen Datemnbank Login und Controller
 *
 */
public class LoginDao extends DAOimp<User>{

    public LoginDao(Connection conn) {
        super(conn);
    }

    /**
     * Bekommt einen user und gibt einen String zurück, der einen user in login anlegt
     * @param user
     * @return SQL Command String
     */
    @Override
    protected String getCreateStatementString(User user) {
        return String.format("INSERT INTO login (user, passwordhash, salt) VALUES ('%s', '%s', '%s')",
                user.getUser(), user.getPasswordHash(), user.getSalt());
    }
    /**
     * Bekommt eine ID und gibt einen String zurück, der einen User ausgibt
     * @param key
     * @return SQL Command String
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM login WHERE lid = %d", key);
    }
    /**
     *bekommt ein result eine Reihe und wandelt das ergebnis in ein Objekt
     * @param resultSet
     * @return user
     * @throws SQLException
     */
    @Override
    protected User getInstanceFromResultSet(ResultSet resultSet) throws SQLException {
        User user = null;
        user = new User(resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getInt(1));
        return user;
    }
    /**
     * Gibt die gesamte user datenbank zurück
     * @return SQL Command String
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM login";
    }
    /**
     *bekommt ein result und wandelt das ergebnis in ein Objekt um und fügt dieses zu einer Liste hinzu
     * @param resultSet
     * @return list
     * @throws SQLException
     */
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
    /**
     *bekommt einen user und updatet den Datensatz
     * @param user
     * @return SQL Command String
     */
    @Override
    protected String getUpdateStatementString(User user) {

        return String.format("UPDATE login SET User = '%s', passwordhash = '%s', salt = '%s' WHERE lid = '%s' ",
                user.getUser(), user.getPasswordHash(), user.getSalt(), user.getUserID());
    }
    /**
     * bekommt eine ID und gibt einen SQL Command String zurück der den dazugehörigen Datensatz endgültig löscht
     * @param key
     * @return SQL Command String
     */
    protected String getDeleteStatementString(long key) {

        return String.format("Delete FROM login WHERE lid=%d", key);
    }

}
