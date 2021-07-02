package model;

/**
 *Modellklasse um gesperrte Patienten anzulegen
 */

import java.time.LocalDate;

public class RemovedPatient extends Patient  {

    private LocalDate toDeleteDate;

    /**
     * erstellt einen gesperrten Patienten
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     */
    public RemovedPatient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber) {
        super(firstName, surname, dateOfBirth, careLevel, roomnumber);
        toDeleteDate=LocalDate.now();
    }

    /**
     * erstellt einen gesperrten Patienten mit Löschdatum
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     * @param deleteDate
     */
    public RemovedPatient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber, LocalDate deleteDate) {
        super(firstName, surname, dateOfBirth, careLevel, roomnumber);
        this.toDeleteDate=deleteDate;
    }

    /**
     * getter gibt Löschdatum zurück
     * @return toDeleteDate
     */
    public LocalDate getToDeleteDate() {
        return toDeleteDate;
    }
}
