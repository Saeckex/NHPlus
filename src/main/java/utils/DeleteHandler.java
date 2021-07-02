package utils;

import model.RemovedPatient;

import java.time.LocalDate;

public class DeleteHandler {

    public LocalDate checkForCertainTime(){
        return LocalDate.now();
    }
    public void deleteTenYearsLockedPatient(RemovedPatient patient){

        if (checkForCertainTime().isAfter(patient.getToDeleteDate().plusYears(10))){



        }
        else{

        }


    }
}
