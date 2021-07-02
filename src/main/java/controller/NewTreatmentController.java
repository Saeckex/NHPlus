package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewTreatmentController {
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;
    @FXML
    private ComboBox<String> comboBox;

    private Caregiver caregiver;
    private ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();
    private ArrayList<Caregiver> caregiverArrayList;
    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;

    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {

        comboBox.setItems(myComboBoxData);
        comboBox.getSelectionModel().select(0);

        this.controller= controller;
        this.patient = patient;
        this.stage = stage;
        createComboBoxData();
        showPatientData();
    }

    private void createComboBoxData() {
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            caregiverArrayList = (ArrayList<Caregiver>) dao.readAll();
            for (Caregiver caregiver : caregiverArrayList) {
                this.myComboBoxData.add(caregiver.getSurname() + " - " + caregiver.getCaregiverID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleComboBox() {
        String p = this.comboBox.getSelectionModel().getSelectedItem();
        String q = p.substring(p.indexOf(" - ")+3);
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            Caregiver caregiver = dao.read(Long.parseLong(q));
            this.caregiver = caregiver;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void showPatientData(){
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    @FXML
    public void handleAdd(){
        try {
            LocalDate date = this.datepicker.getValue();
            LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
            LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
            String description = txtDescription.getText();
            String remarks = taRemarks.getText();
            Treatment treatment = new Treatment(patient.getPid(), caregiver.getCaregiverID(), date,
                    begin, end, description, remarks);
            createTreatment(treatment);
            controller.readAllAndShowInTableView();
            stage.close();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Notwendige Daten für die Behandlung fehlen!");
            alert.setContentText("Vergewissern sie sich, dass sie Datum, Begin, Ende und eine Pfleger angegeben haben.");
            alert.showAndWait();
        }
    }

    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel(){
        stage.close();
    }
}
