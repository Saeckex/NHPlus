package utils;

/**
 * Klasse enthält Methoden zum prüfen und löschen der Datensätze einzelner lockedPatients
 */

import datastorage.DAOFactory;
import datastorage.RemovedPatientDAO;
import model.Patient;
import model.RemovedPatient;

import java.sql.SQLException;
import java.time.LocalDate;

public final class DeleteHandler {
    /**
     * Klasse ist als Singleton definiert um erstellen weiterer Objekte zu unterbinden
     */
    private static DeleteHandler deleteHandler;

  private DeleteHandler(){}

    /**
     * Methode nimmt das Sperrdatum eines gesperrten Patienten, addiert 10 Jahre hinzu
     * und Vergleicht dieses anschließend mit der Systemzeit
     * @param removedpatient
     * @return date.isAfter(todate)
     */
    public boolean checkForCertainTime(RemovedPatient removedpatient){
       LocalDate date= removedpatient.getToDeleteDate();
       LocalDate todate = LocalDate.now();
       date.plusYears(10);
       return date.isAfter(todate);
    }

    /**
     * Methode löscht den Datensatz der der Methode gegeben wird, wenn checkForCertainTime True zurückgibt
     * @param patient
     * @throws SQLException
     */
    public void deleteTenYearsLockedPatient(RemovedPatient patient) throws SQLException {
      if(checkForCertainTime(patient)){
          RemovedPatientDAO removedpatient = DAOFactory.getDAOFactory().createRemovedPatientDAO();
          removedpatient.deleteById(patient.getPid());
      }
    }

    /**
     * getter der den deletehandler zurückgibt
     * @return deleteHandler
     */

    public synchronized static DeleteHandler getDeleteHandler(){
        if(deleteHandler==null){ deleteHandler= new DeleteHandler();}
        return deleteHandler;

    }


}
