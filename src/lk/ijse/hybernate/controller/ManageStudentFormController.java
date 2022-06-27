package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hybernate.bo.BOFactory;
import lk.ijse.hybernate.bo.BOTypes;
import lk.ijse.hybernate.bo.custom.StudentBO;
import lk.ijse.hybernate.bo.custom.impl.StudentBOImpl;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.view.tdm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageStudentFormController {
    public AnchorPane ManageStudentContext;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtConNo;
    public JFXTextField txtDOB;
    public JFXComboBox<String> cmbGender;
    public TableView<StudentTM> tblStudent;
    public TableColumn colStudentId;
    public TableColumn colAddress;
    public TableColumn colConNo;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableColumn colName;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnAddNewStudent;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize() throws IOException {

        cmbGender.getItems().addAll("Male","Female");

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        //initUI();

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");

            if(newValue != null){
                txtStudentId.setText(newValue.getStudentID());
                txtStudentName.setText(newValue.getStudentName());
                txtAddress.setText(newValue.getAddress());
                txtConNo.setText(newValue.getContactNo());
                txtDOB.setText(String.valueOf(newValue.getDob()));
                cmbGender.setValue(newValue.getGender());

                txtStudentId.setDisable(false);
                txtStudentName.setDisable(false);
                txtAddress.setDisable(false);
                txtConNo.setDisable(false);
                txtDOB.setDisable(false);
                cmbGender.setDisable(false);

                btnSave.setDisable(false);
            }
        });

        loadAllStudents();
    }

    /*public void getAllStudent() throws IOException {
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();

        tblStudent.getItems().clear();

        for (StudentDTO studentDTO : allStudent) {
            tblStudent.getItems().add(new StudentTM(
                    studentDTO.getStudentID(),
                    studentDTO.getStudentName(),
                    studentDTO.getAddress(),
                    studentDTO.getContactNo(),
                    studentDTO.getDob(),
                    studentDTO.getGender()
            ));
        }

    }*/
   /* private void loadAllStudents() throws IOException {
        tblStudent.getItems().clear();

            List<StudentDTO> allStudents = studentBO.getAllStudent();
            for(StudentDTO s : allStudents) {
                tblStudent.getItems().add(new StudentTM(
                        s.getStudentID(),
                        s.getStudentName(),
                        s.getAddress(),
                        s.getContactNo(),
                        s.getDob(),
                        s.getGender()));
            }

           // System.out.println(e);
            //new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

    }*/

    private void loadAllStudents(){
        tblStudent.getItems().clear();
        try {
            List<StudentDTO> allStudents = studentBO.getAllStudent();
            for(StudentDTO s : allStudents) {
                tblStudent.getItems().add(new StudentTM(
                        s.getStudentID(),
                        s.getStudentName(),
                        s.getAddress(),
                        s.getContactNo(),
                        s.getDob(),
                        s.getGender()));
            }
        } catch (Exception e) {
            System.out.println(e);
           // new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }
    private void clearFields(){
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtConNo.clear();
        txtDOB.setText(null);
        cmbGender.setValue(null);
    }

    private void initUI() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtConNo.clear();
        txtDOB.clear();
        txtStudentId.setDisable(true);
        txtStudentName.setDisable(true);
        txtAddress.setDisable(true);
        txtConNo.setDisable(true);
        txtDOB.setDisable(true);
        cmbGender.setDisable(true);
        txtStudentId.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void AddNewStudentOnAction(ActionEvent actionEvent) {
        txtStudentId.setDisable(false);
        txtStudentName.setDisable(false);
        txtAddress.setDisable(false);
        txtConNo.setDisable(false);
        txtDOB.setDisable(false);
        cmbGender.setDisable(false);
        txtStudentId.clear();
       // txtItemCode.setText(generateNewId());
        txtStudentName.clear();
        txtAddress.clear();
        txtConNo.clear();
        txtDOB.clear();
        txtStudentName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStudent.getSelectionModel().clearSelection();
    }

    public void SaveStudentOnAction(ActionEvent actionEvent) throws IOException {

        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String contact_no = txtConNo.getText();
        String dob = txtDOB.getText();
        String gender = String.valueOf(cmbGender.getValue());


        if (btnSave.getText().equalsIgnoreCase("Save")) {
            try {
                if (studentBO.saveStudent(new StudentDTO(id, name, address, contact_no, dob, gender))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    tblStudent.getItems().add(new StudentTM(id, name, address, contact_no, dob, gender));
                    clearFields();
                }
            } catch (Exception e) {
                //System.out.println("Exception 1");
                System.out.println(e);

                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();

            }
        } else {
            try {
                if (studentBO.updateStudent(new StudentDTO(id, name, address, contact_no, dob, gender))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                    loadAllStudents();
                    clearFields();
                }
            } catch (Exception e) {
                // System.out.println("Exception 2");
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }
        /*if(btnSave.getText().equals("Save")){
            boolean b = studentBO.saveStudent(new StudentDTO(
                    txtStudentId.getText(),
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtConNo.getText(),
                    txtDOB.getText(),
                    String.valueOf(cmbGender.getValue())
            ));

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Added SuccessFully").show();

                tblStudent.getItems().add(new StudentTM(
                        txtStudentId.getText(),
                        txtStudentName.getText(),
                        txtAddress.getText(),
                        txtConNo.getText(),
                        txtDOB.getText(),
                        String.valueOf(cmbGender.getValue())
                ));
                clearFields();

            } else {
                new Alert(Alert.AlertType.WARNING, "Something Went Wrong !!").show();
            }
        }else{
            studentBO.updateStudent(new StudentDTO(
                    txtStudentId.getText(),
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtConNo.getText(),
                    txtDOB.getText(),
                    String.valueOf(cmbGender.getValue())
            ));
            btnSave.setText("Save");
            txtStudentId.setEditable(true);
            clearFields();
            loadAllStudents();
        }
    }*/

    public void DeleteOnAction(ActionEvent actionEvent) throws IOException {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
       // System.out.println(selectedItem.get);
        if (studentBO.deleteStudent(selectedItem.getStudentID())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted SuccessFully").show();
            loadAllStudents();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something Went Wring !!").show();

        }

    }


}
