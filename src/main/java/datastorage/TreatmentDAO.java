package datastorage;

import model.Treatment;
import utils.DateConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasse TreatmentDAO erbt von DAOimp
 * Schnittstelle zwischen Datemnbank Treatment und Controller
 *
 */
public class TreatmentDAO extends DAOimp<Treatment> {

    public TreatmentDAO(Connection conn) {
        super(conn);
    }
    /**
     * Bekommt ein Treatment und gibt einen String zurück, der ein Treatment in treatments anlegt
     * @param treatment
     * @return SQL Command String
     */
    @Override
    protected String getCreateStatementString(Treatment treatment) {
        return String.format("INSERT INTO treatment (pid, cid, treatment_date, begin, end, description, remarks) VALUES " +
                "(%d, '%s', '%s', '%s', '%s', '%s')", treatment.getPid(), treatment.getCaregiverID(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(),
                treatment.getRemarks());
    }
    /**
     * Bekommt eine ID und gibt einen String zurück, der ein treatment ausgibt
     * @param key
     * @return SQL Command String
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM treatment WHERE tid = %d", key);
    }
    /**
     * bekommt ein result eine Reihe und wandelt das ergebnis in ein Objekt
     * @param result
     * @return p
     * @throws SQLException
     */
    @Override
    protected Treatment getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
        Treatment m = new Treatment(result.getLong(1), result.getLong(2),result.getLong(8),
                date, begin, end, result.getString(6), result.getString(7));
        return m;
    }
    /**
     * Gibt die gesamte treatments datenbank zurück
     * @return SQL Command String
     */
    @Override
    protected String getReadAllStatementString() {

        return "SELECT * FROM treatment";
    }
    /**
     *bekommt ein result und wandelt das ergebnis in ein Objekt um und fügt dieses zu einer Liste hinzu
     * @param result
     * @return list
     * @throws SQLException
     */
    @Override
    protected ArrayList<Treatment> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment t = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
            t = new Treatment(result.getLong(1), result.getLong(2),result.getLong(8),
                    date, begin, end, result.getString(6), result.getString(7));
            list.add(t);
        }
        return list;
    }
    /**
     * bekommt ein treatment und updatet den Datensatz
     * @param treatment
     * @return SQL Command String
     */
    @Override
    protected String getUpdateStatementString(Treatment treatment) {
        return String.format("UPDATE treatment SET pid = %d, cid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                "description = '%s', remarks = '%s' WHERE tid = %d", treatment.getPid(), treatment.getCaregiverID(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(), treatment.getRemarks(),
                treatment.getTid());
    }
    /**
     * bekommt eine ID und gibt einen SQL Command String zurück der den dazugehörigen Datensatz endgültig löscht
     * @param key
     * @return SQL Command String
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM treatment WHERE tid= %d", key);
    }

    /**
     * bekommt eine ID und gibt eine Liste aller Behandlungen eines Patienten zurück
     * @param pid
     * @return
     * @throws SQLException
     */
    public List<Treatment> readTreatmentsByPid(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPid(pid));
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * bekommt eine ID und gibt alle Behandlungen eines Patienten aus
     * @param pid
     * @return
     */
    private String getReadAllTreatmentsOfOnePatientByPid(long pid){
        return String.format("SELECT * FROM treatment WHERE pid = %d", pid);
    }

    /**
     * bekommt eine ID und löscht den dazugehörigen Datensatz aus treatment
     * @param key
     * @throws SQLException
     */
    public void deleteByPid(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE pid= %d", key));
    }
}
