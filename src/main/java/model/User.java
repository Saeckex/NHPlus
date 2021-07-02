package model;

public class User {

    private String user;
    private String passwordHash;
    private String salt;
    private int userID;

    public User(String user, String passwordHash, String salt, Integer id) {
        this.user = user;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.userID = id;
    }

    public User(String user, String passwordHash, String salt) {
        this.user = user;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", password='" + passwordHash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
