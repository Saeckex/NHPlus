package model;

/**
 * Die Klasse Caregiver erbt von Person und ermöglicht das Anlegen von Pflegekraeften. Diese werden um eine ID, eine Telefonnummer,
 * sowie einen Aktivitätsstatus und deren Getter und Setter.
 */
public class Caregiver extends Person {

    boolean activity;
    int caregiverID;
    String phoneNumber;
    /**
     * Konstruktor um einen Pfleger anzulegen.
     * @param firstName
     * @param surname
     * @param phoneNumber
     */
    public Caregiver(String firstName, String surname, String phoneNumber) {
        super(firstName, surname);
        this.phoneNumber = phoneNumber;
        setActivity(true);
    }
    /**
     * Konstruktor um einen Pfleger anzulegen.
     * @param firstName
     * @param surname
     * @param phoneNumber
     * @param caregiverID
     */
    public Caregiver(String firstName, String surname, String phoneNumber, int caregiverID) {
        super(firstName, surname);
        this.phoneNumber = phoneNumber;
        this.caregiverID = caregiverID;
        setActivity(true);
    }
    /**
     * Getter um Aktivitätsstatus zurückzugeben
     * @return activity
     */
    public boolean getActivity() {
        return activity;
    }
    /**
     * Setter um Aktivitätsstatus auf activity zu setzen.
     * @param activity als Boolean
     */
    public void setActivity(boolean activity) {
        this.activity = activity;
    }
    /**
     * Setter um Aktivitätsstatus auf den anderen boolean Wert zu setzen.
     * @param activity als Boolean
     */
    public void setActivity(){ this.activity = !this.activity; }

    /**
     * Getter um CaregiverID zurückzugeben
     * @return CaregiverID
     */
    public int getCaregiverID() {
        return caregiverID;
    }
    /**
     * Getter um phoneNumber zurückzugeben
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Setter um phoneNumber zu intitialisieren
     * @param phoneNumber als String
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Getter um activitätsstatus zurückzugeben als String
     * @return "Aktiv" oder "Gesperrt"
     */
    public String getActivityAsString(){
        if (activity){
            return "Aktiv";
        }
        return "Gesperrt";
    }
    /**
     * Methode um ganzen Pfleger als String auszugeben
     * @return ganzer Pfleger
     */
    public String toString() {
        return "Caregiver" + "\nCID: " + this.getCaregiverID() +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nPhoneumber: " + this.getPhoneNumber() +
                "\nActivity: " + this.getActivity() +
                "\n";
    }
}
