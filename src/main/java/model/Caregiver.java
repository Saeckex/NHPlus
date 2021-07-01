package model;

public class Caregiver extends Person {

    boolean activity;
    int caregiverID;
    String phoneNumber;

    public Caregiver(String firstName, String surname, String phoneNumber) {
        super(firstName, surname);
        this.phoneNumber = phoneNumber;
        setActivity(true);
    }

    public Caregiver(String firstName, String surname, String phoneNumber, int caregiverID) {
        super(firstName, surname);
        this.phoneNumber = phoneNumber;
        this.caregiverID = caregiverID;
        setActivity(true);
    }

    public boolean getActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public void setActivity(){ this.activity = !this.activity; }

    public int getCaregiverID() {
        return caregiverID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActivityAsString(){
        if (activity){
            return "Aktiv";
        }
        return "Gesperrt";
    }

    public String toString() {
        return "Caregiver" + "\nCID: " + this.getCaregiverID() +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nPhoneumber: " + this.getPhoneNumber() +
                "\nActivity: " + this.getActivity() +
                "\n";
    }
}
