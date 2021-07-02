package model;


import java.time.LocalDate;


public class RemovedPatient extends Patient  {

    private LocalDate toDeleteDate;

    public RemovedPatient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber) {
        super(firstName, surname, dateOfBirth, careLevel, roomnumber);
        LocalDate date=LocalDate.now();


    }

    public RemovedPatient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber, LocalDate deleteDate) {
        super(firstName, surname, dateOfBirth, careLevel, roomnumber);
        this.toDeleteDate=deleteDate;

    }

    public LocalDate getToDeleteDate() {
        return toDeleteDate;
    }
}
