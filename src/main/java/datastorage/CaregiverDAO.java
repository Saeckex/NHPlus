package datastorage;

import model.Caregiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaregiverDAO extends DAOimp<Caregiver>{

    public CaregiverDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO caregiver (firstname, surname, phonenumber, activity) VALUES ('%s', '%s', '%s', '%s')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber(), caregiver.getActivity());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet resultSet) throws SQLException {
        Caregiver c = null;
        c = new Caregiver(resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4),resultSet.getInt(1));
        c.setActivity(resultSet.getBoolean(5));
        return c;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM caregiver";
    }

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

    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {

        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', phonenumber = '%s', activity = '%s' WHERE cid = '%s' ",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber(), caregiver.getActivity(), caregiver.getCaregiverID());
    }

    @Override // TODO: 28.06.21 only possible if no treatments are done since 10 years
    protected String getDeleteStatementString(long key) {

        return String.format("Delete FROM caregiver WHERE cid=%d", key);
    }
}
