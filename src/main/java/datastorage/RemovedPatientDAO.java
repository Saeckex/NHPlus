package datastorage;

import model.RemovedPatient;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Klasse RemovedPatientDAO erbt von DAOIMP
 * Schnittstelle zwischen Datemnbank RemovedPatient und Controller
 *
 */
public class RemovedPatientDAO extends DAOimp<RemovedPatient>{


    public RemovedPatientDAO(Connection conn) {
        super(conn);
    }

    /**
     * Bekommt einen Patient und gibt einen String zurück, der einen Patient in Lockedpatiens anlegt
     * @param removedPatient
     * @return SQL Command String
     */
    @Override
    protected String getCreateStatementString(RemovedPatient removedPatient) {
        return  String.format("INSERT INTO lockedpatiens (firstname, surname, dateOfBirth, carelevel, roomnumber, todeletedate) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                removedPatient.getFirstName(), removedPatient.getSurname(),removedPatient.getDateOfBirth(), removedPatient.getCareLevel(),
                removedPatient.getRoomnumber(), removedPatient.getToDeleteDate());
    }

    /**
     * Bekommt eine ID und gibt einen String zurück, der einen Patienten ausgibt
     * @param key
     * @return SQL Command String
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM lockedpatiens WHERE p_id = %d", key);
    }

    /**
     * bekommt ein result eine Reihe und wandelt das ergebnis in ein Objekt
     * @param result
     * @return p
     * @throws SQLException
     */
    @Override
    protected RemovedPatient getInstanceFromResultSet(ResultSet result) throws SQLException {
        RemovedPatient p = null;
        LocalDate birthDate = DateConverter.convertStringToLocalDate(result.getString(4));
        LocalDate deletionDate = DateConverter.convertStringToLocalDate(result.getString(4));
        p = new RemovedPatient(result.getString(2), result.getString(3), birthDate,
                result.getString(5), result.getString(6), deletionDate);
        return p;
    }

    /**
     * Gibt die gesamte lockedpatiens datenbank zurück
     * @return SQL Command String
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM lockedpatiens";
    }

    /**
     *bekommt ein result und wandelt das ergebnis in ein Objekt um und fügt dieses zu einer Liste hinzu
     * @param result
     * @return list
     * @throws SQLException
     */
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

    /**
     * bekommt einen removedPatient und updatet den Datensatz
     * @param removedPatient
     * @return SQL Command String
     */
    @Override
    protected String getUpdateStatementString(RemovedPatient removedPatient) {
        return String.format("UPDATE lockedpatiens SET firstname = '%s', surname = '%s', dateOfBirth = '%s', carelevel = '%s', " +
                        "roomnumber = '%s', todeletedate = '%s' WHERE p_id = '%s'", removedPatient.getFirstName(), removedPatient.getSurname(),
                removedPatient.getDateOfBirth(),removedPatient.getCareLevel(), removedPatient.getRoomnumber(), removedPatient.getToDeleteDate(),removedPatient.getPid());
    }

    /**
     * bekommt eine ID und gibt einen SQL Command String zurück der den dazugehörigen Datensatz endgültig löscht
     * @param key
     * @return SQL Command String
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM lockedpatiens WHERE p_id=%d", key);
    }
}
