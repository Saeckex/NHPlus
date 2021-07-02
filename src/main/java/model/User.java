package model;

public class User {

    private String user;
    private String passwordHash;
    private String salt;
    private int userID;

    /**
     * Konstruktor um einen User anzulegen.
     * @param user
     * @param passwordHash
     * @param salt
     * @param id
     */

    public User(String user, String passwordHash, String salt, Integer id) {
        this.user = user;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.userID = id;
    }

    /**
     * getter um user zurückzugeben
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * setter um user zu setzen
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }
    /**
     * getter um passwordHash zurückzugeben
     * @return passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }
    /**
     * setter um passwordHash zu setzen
     * @param passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * getter um salt zurückzugeben
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * setter um salt zu setzen
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * getter um userID zurückzugeben
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * gibt einen User als String zurück
     * @return String
     */
    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", password='" + passwordHash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
