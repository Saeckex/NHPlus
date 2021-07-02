package datastorage;

import model.Caregiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Klasse erbt von DAOIMP
 * Schnittstelle zwischen Datemnbank RemovedPatient und Controller
 *
 */
public class CaregiverDAO extends DAOimp<Caregiver>{

    public CaregiverDAO(Connection conn) {
        super(conn);
    }
    /**
     * Bekommt einen Pfleger und gibt einen String zurück, der einen Pfleger in Caregiver anlegt
     * @param caregiver
     * @return SQL Command String
     */
    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO caregiver (firstname, surname, phonenumber, activity) VALUES ('%s', '%s', '%s', '%s')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber(), caregiver.getActivity());
    }
    /**
     * Bekommt eine ID und gibt einen String zurück, der einen caregiver ausgibt
     * @param key
     * @return SQL Command String
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
    }
    /**
     * bekommt ein result eine Reihe und wandelt das ergebnis in ein Objekt
     * @param resultSet
     * @return p
     * @throws SQLException
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet resultSet) throws SQLException {
        Caregiver c = null;
        c = new Caregiver(resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4),resultSet.getInt(1));
        c.setActivity(resultSet.getBoolean(5));
        return c;
    }
    /**
     * Gibt die gesamte caregiver datenbank zurück
     * @return SQL Command String
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM caregiver";
    }
    /**
     *bekommt ein result und wandelt das ergebnis in ein Objekt um und fügt dieses zu einer Liste hinzu
     * @param resultSet
     * @return list
     * @throws SQLException
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver c = null;
        while (resultSet.next()) {
            c = new Caregiver(resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4),resultSet.getInt(1));
            c.setActivity(resultSet.getBoolean(5));
            list.add(c);
        }
        return list;
    }
    /**
     * bekommt einen caregiver und updatet den Datensatz
     * @param caregiver
     * @return SQL Command String
     */
    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {

        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', phonenumber = '%s', activity = '%s' WHERE cid = '%s' ",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber(), caregiver.getActivity(), caregiver.getCaregiverID());
    }
    /**
     * bekommt eine ID und gibt einen SQL Command String zurück der den dazugehörigen Datensatz endgültig löscht
     * @param key
     * @return SQL Command String
     */
    @Override // TODO: 28.06.21 only possible if no treatments are done since 10 years
    protected String getDeleteStatementString(long key) {

        return String.format("Delete FROM caregiver WHERE cid=%d", key);
    }
}
