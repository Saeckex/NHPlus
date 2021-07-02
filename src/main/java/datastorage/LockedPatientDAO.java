package datastorage;

import model.Caregiver;
import model.LockedPatient;
import model.Patient;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LockedPatientDAO<LockedPatient>extends DAOimp {


    public LockedPatientDAO(Connection conn) {
        super(conn);
    }


    @Override
    protected String getCreateStatementString(model.LockedPatient lockedpatient) {
        return String.format("INSERT INTO lockedpatiens (firstname, surname,dateofbirth,carelevel,roomnumber,todeletedate) VALUES ('%s', '%s', '%s', '%s')",
                lockedpatient.getFirstName(),lockedpatient.getSurname(),lockedpatient.getDateOfBirth(),lockedpatient.getCareLevel(),
                lockedpatient.getRoomnumber(),lockedpatient.getToDeleteDate());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM lockedpatiens WHERE p_id = %d", key);
    }

    @Override
    protected model.LockedPatient getInstanceFromResultSet(ResultSet resultSet) throws SQLException {
        model.LockedPatient c;
        LocalDate date2= DateConverter.convertStringToLocalDate(resultSet.getString(4));
        LocalDate date = DateConverter.convertStringToLocalDate(resultSet.getString(7));
        c = new model.LockedPatient(resultSet.getString(2), resultSet.getString(3),date2,
                resultSet.getString(5), resultSet.getString(6),date);
        return c;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM lockedpatiens";
    }

    @Override
    protected ArrayList<model.LockedPatient> getListFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<model.LockedPatient> list = new ArrayList<model.LockedPatient>();
        model.LockedPatient c = null;
        while (resultSet.next()) {
            LocalDate date2= DateConverter.convertStringToLocalDate(resultSet.getString(4));
            LocalDate date = DateConverter.convertStringToLocalDate(resultSet.getString(7));
            c = new model.LockedPatient(resultSet.getString(2), resultSet.getString(3),date2,
                    resultSet.getString(5), resultSet.getString(6),date);
            list.add(c);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(model.LockedPatient lockedpatient) {

        return String.format("UPDATE lockedpatiens SET firstname = '%s', surname = '%s',dateofbirth = '%s', carelevel = '%s'," +
                        "roomnumber = '%s',todeletedate = '%s' WHERE p_id = '%s' ",
               lockedpatient.getFirstName(),lockedpatient.getSurname(),lockedpatient.getDateOfBirth(),lockedpatient.getCareLevel(),
                lockedpatient.getRoomnumber(),lockedpatient.getToDeleteDate());
    }

    @Override
    protected String getDeleteStatementString(long key) {

        return String.format("Delete FROM lockedpatiens WHERE p_id=%d", key);
    }

}
