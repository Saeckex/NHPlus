package controller;

import datastorage.DAOFactory;
import datastorage.LoginDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.Caregiver;
import model.User;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class AllUserController {

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, Integer> colID;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colPasswordHash;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txfPasswordOne;
    @FXML
    private TextField txfPasswordTwo;

    private LoginDao dao;
    private Stage stage;
    private MainWindowController mainWindowController;
    private ObservableList<User> tableViewContent = FXCollections.observableArrayList();

    public void initilizeController(Stage stage, MainWindowController mainWindowController){
        readAllAndShowInTableView();

        this.mainWindowController = mainWindowController;
        this.stage = stage;

        this.colID.setCellValueFactory(new PropertyValueFactory<User, Integer>("userID"));

        this.colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("user"));
        this.colUsername.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colPasswordHash.setCellValueFactory(new PropertyValueFactory<User, String>("passwordHash"));

        this.tableView.setItems(this.tableViewContent);
    }

    @FXML
    private void handleAdd()  {
        String userName = this.txtUserName.getText();
        String passwordOne = this.txfPasswordOne.getText();
        String passwordTwo = this.txfPasswordTwo.getText();
        String salt;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (!passwordOne.equals(passwordTwo)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Passwörter nicht identisch");
            alert.setContentText("Ein Passwort nicht eingetragen oder nicht gleich");
            alert.showAndWait();
            clearTextfields();
            return;
        }

        byte[] hash = digest.digest(passwordOne.getBytes(StandardCharsets.UTF_8));
        passwordOne = new String(hash, StandardCharsets.UTF_8);

        try {
            User u = new User(userName, passwordOne, null);
            dao.create(u);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    @FXML
    private void handleDeleteRow() {
        User user = this.tableView.getSelectionModel().getSelectedItem();
        try{
            dao.deleteById(user.getUserID());
            this.tableView.getItems().remove(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void handleOnEditUsername(TableColumn.CellEditEvent<User, String> event){
        event.getRowValue().setUser(event.getNewValue());
        doUpdate(event);
    }

    private void readAllAndShowInTableView() {
        this.tableViewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createLoginDAO();
        List<User> allUser;
        try {
            allUser = dao.readAll();
            for (User user : allUser) {
                this.tableViewContent.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doUpdate(TableColumn.CellEditEvent<User, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearTextfields(){
        txfPasswordOne.clear();
        txfPasswordTwo.clear();
        txtUserName.clear();
    }
}
