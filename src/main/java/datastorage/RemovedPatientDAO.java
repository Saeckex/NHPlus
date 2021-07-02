package datastorage;

import model.RemovedPatient;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RemovedPatientDAO extends DAOimp<RemovedPatient>{


    public RemovedPatientDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(RemovedPatient removedPatient) {
        return String.format("INSERT INTO lockedpatients (firstname, surname, dateOfBirth, carelevel, roomnumber, todeletedate) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')" +
                "WHERE p_id = '%s'", removedPatient.getFirstName(), removedPatient.getSurname(), removedPatient.getCareLevel(),
                removedPatient.getRoomnumber(), removedPatient.getToDeleteDate(), removedPatient.getPid());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM lockedpatients WHERE pid = %d", key);
    }

    @Override
    protected RemovedPatient getInstanceFromResultSet(ResultSet result) throws SQLException {
        RemovedPatient p = null;
        LocalDate birthDate = DateConverter.convertStringToLocalDate(result.getString(4));
        LocalDate deletionDate = DateConverter.convertStringToLocalDate(result.getString(4));
        p = new RemovedPatient(result.getString(2), result.getString(3), birthDate,
                result.getString(5), result.getString(6), deletionDate);
        return p;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM lockedpatients";
    }

    @Override
    protected ArrayList<RemovedPatient> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<RemovedPatient> list = new ArrayList<RemovedPatient>();
        RemovedPatient p = null;
        while (result.next()) {
            LocalDate birthDate = DateConverter.convertStringToLocalDate(result.getString(4));
            LocalDate deletionDate = DateConverter.convertStringToLocalDate(result.getString(4));
            p = new RemovedPatient(result.getString(2), result.getString(3), birthDate,
                    result.getString(5), result.getString(6), deletionDate);
            list.add(p);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(RemovedPatient removedPatient) {
        return String.format("UPDATE  SET firstname = '%s', surname = '%s', dateOfBirth = '%s', carelevel = '%s', " +
                        "roomnumber = '%s', todeletedate = '%s'", removedPatient.getFirstName(), removedPatient.getSurname(),
                removedPatient.getDateOfBirth(),removedPatient.getCareLevel(), removedPatient.getRoomnumber(), removedPatient.getToDeleteDate());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM lockedpatients WHERE p_id=%d", key);
    }
}
