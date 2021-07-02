package utils;

import model.LockedPatient;
import model.Patient;

import java.time.LocalDate;

public class DeleteHandler {

    public LocalDate checkForCertainTime(){
        return LocalDate.now();
    }
    public void deleteTenYearsLockedPatient(LockedPatient patient){

        if (checkForCertainTime().isAfter(patient.getToDeleteDate().plusYears(10))){



        }
        else{

        }


    }
}
