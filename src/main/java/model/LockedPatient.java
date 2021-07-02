package model;


import java.time.LocalDate;
import java.util.Date;


public class LockedPatient extends Patient  {

    private LocalDate toDeleteDate;

    public LockedPatient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber) {
        super(firstName, surname, dateOfBirth, careLevel, roomnumber);
        LocalDate date=LocalDate.now();


        toDeleteDate.plusYears(10);

    }

    public LockedPatient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber,LocalDate deleteDate) {
        super(firstName, surname, dateOfBirth, careLevel, roomnumber);
        this.toDeleteDate=deleteDate;

    }

    public LocalDate getToDeleteDate() {
        return toDeleteDate;
    }
}
