package utils;

import datastorage.DAOFactory;
import datastorage.RemovedPatientDAO;
import model.Patient;
import model.RemovedPatient;

import java.sql.SQLException;
import java.time.LocalDate;

public final class DeleteHandler {
    private static DeleteHandler deleteHandler;

  private DeleteHandler(){}

    public boolean checkForCertainTime(RemovedPatient removedpatient){
       LocalDate date= removedpatient.getToDeleteDate();
       LocalDate todate = LocalDate.now();
       date.plusYears(10);
       return date.isAfter(todate);
    }
    public void deleteTenYearsLockedPatient(RemovedPatient patient) throws SQLException {
      if(checkForCertainTime(patient)){
          RemovedPatientDAO removedpatient = DAOFactory.getDAOFactory().createRemovedPatientDAO();
          removedpatient.deleteById(patient.getPid());
      }
    }

    public synchronized static DeleteHandler getDeleteHandler(){
        if(deleteHandler==null){ deleteHandler= new DeleteHandler();}
        return deleteHandler;

    }


}
