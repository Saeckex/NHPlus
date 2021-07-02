package controller;

import datastorage.DAOFactory;
import datastorage.LoginDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class LoginWindowController {
    @FXML
    private TextField txtuser;
    @FXML
    private TextField txtpassword;

    private Stage stage;
    private LoginDao dao = DAOFactory.getDAOFactory().createLoginDAO();
    private User user;
    private MainWindowController mainWindowController;

    public void initializeController(Stage stage, MainWindowController mainWindowController){

        this.stage = stage;
        this.mainWindowController = mainWindowController;
    }

    @FXML
    private void handleLogin() {
        try {
            if (login(txtuser.getText(),txtpassword.getText())) {
                mainWindowController.setLoggedInUser(user);
                stage.close();
                return;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Nicht eingeloggt");
        alert.setContentText("Benutzername oder Passwort falsch");
        alert.showAndWait();
    }

    private boolean login(String user, String password) throws SQLException, NoSuchAlgorithmException {
        List<User> allUser = dao.readAll();
        for(User u : allUser){
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String str = password + u.getSalt();
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            str = new String(hash, StandardCharsets.UTF_8);
            if (u.getPasswordHash().equals(str)){
                this.user = u;
                return true;
            }
        }
        return false;
    }
}
