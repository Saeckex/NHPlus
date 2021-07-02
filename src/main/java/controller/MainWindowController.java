package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Treatment;
import model.User;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;
    private User loggedInUser;

    @FXML
    private void handleShowLogin(ActionEvent e) {
        if (!checkLogin()) {
            loginWindow();
            return;
        }
        throwAllertAlreadyLoggedIn();
        return;
    }

    @FXML
    private void handleShowNewUser(ActionEvent e){
        if (checkLogin()) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/CreateUserView"));
                AnchorPane pane = loader.load();
                Scene scene = new Scene(pane);

                Stage stage = new Stage();
                AddUserController controller = loader.getController();

             //   controller.initializeController(stage);

                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        throwAllertNotLoggedIn();
        return;
    }

    @FXML
    private void handleShowAllPatient(ActionEvent e) {
        if (checkLogin()) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
            try {
                mainBorderPane.setCenter(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            AllPatientController controller = loader.getController();
            return;
        }
        throwAllertNotLoggedIn();
        return;
    }

    @FXML
    private void handleShowAllTreatments(ActionEvent e) {
        if (checkLogin()) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
            try {
                mainBorderPane.setCenter(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            AllTreatmentController controller = loader.getController();
            return;
        }
        throwAllertNotLoggedIn();
        return;
    }

    @FXML
    private void handleShowAllCaregivers(ActionEvent e) {
        if (checkLogin()) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllCaregiverView.fxml"));
            try {
                mainBorderPane.setCenter(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            AllCareGiverController controller = loader.getController();
            return;
        }
        throwAllertNotLoggedIn();
        return;
    }

    @FXML
    private void loggOut(){
        loggedInUser = null;
    }

    private boolean checkLogin(){
        if (loggedInUser != null){
            return true;
        }
        return false;
    }

    public void loginWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginWindow.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            Stage stage = new Stage();
            LoginWindowController controller = loader.getController();

            controller.initializeController(stage, this);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void setLoggedInUser(User user){
        this.loggedInUser = user;
    }

    private void throwAllertNotLoggedIn() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Nicht eingeloggt");
        alert.setContentText("Bitte loggen sie sich ein.");
        alert.showAndWait();
    }

    private void throwAllertAlreadyLoggedIn(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Schon eingeloggt");
        alert.setContentText("Bitte loggen sie sich erst aus.");
        alert.showAndWait();
    }
}
