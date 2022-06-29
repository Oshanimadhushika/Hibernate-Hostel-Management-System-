package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.hybernate.bo.custom.UserBO;
import lk.ijse.hybernate.bo.custom.impl.UserBOImpl;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.dto.UserLoginDTO;
import lk.ijse.hybernate.view.tdm.StudentTM;
import lk.ijse.hybernate.view.tdm.UserTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginDetailFormController {
    public AnchorPane LoginDetailsContext;
    public JFXTextField txtUserID;
    public JFXTextField txtPassward;
    public TableView<UserTM> tblLogInDetail;
    public TableColumn colUserID;
    public TableColumn colUserName;
    public TableColumn colPassward;
    public JFXButton btnAddNewUser;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXTextField txtUserName;

   // UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);

    UserBO userBO=new UserBOImpl();

    public void initialize() throws IOException {


        tblLogInDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userID"));
        tblLogInDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblLogInDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));

        initUI();

        tblLogInDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnDelete.setDisable(false);

            if(newValue != null){
                txtUserID.setText(newValue.getUserID());
                txtUserName.setText(newValue.getUserName());
                txtPassward.setText(newValue.getPassword());


                txtUserID.setDisable(false);
                txtUserName.setDisable(false);
                txtPassward.setDisable(false);


                btnSave.setDisable(false);
            }
        });

        loadAllStudents();
    }
    private void loadAllStudents(){
        tblLogInDetail.getItems().clear();
        try {
            List<UserLoginDTO> userLoginDTOS = userBO.getAllUser();
            for(UserLoginDTO u : userLoginDTOS) {
                tblLogInDetail.getItems().add(new UserTM(
                        u.getUserID(),
                        u.getUserName(),
                        u.getPassword()
                        ));
            }
        } catch (Exception e) {
            // System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }
    private void clearFields(){
        txtUserID.clear();
        txtUserName.clear();
        txtPassward.clear();

    }

    private void initUI() {
        txtUserID.clear();
        txtUserName.clear();
        txtPassward.clear();
        txtUserID.setDisable(true);
        txtUserName.setDisable(true);
        txtPassward.setDisable(true);
        txtUserID.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void AddNewLogDetailOnAction(ActionEvent actionEvent) {
        txtUserID.setDisable(false);
        txtUserName.setDisable(false);
        txtPassward.setDisable(false);
        txtUserID.clear();
        txtUserName.clear();
        txtPassward.clear();
        // txtDOB.clear();
        txtUserID.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblLogInDetail.getSelectionModel().clearSelection();
    }

    public void SaveNewLogDetailOnAction(ActionEvent actionEvent) throws IOException {
        String id = txtUserID.getText();
        String name = txtUserName.getText();
        String password = txtPassward.getText();

        if (!id.matches("^(U00)[0-9]{1,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            txtUserID.requestFocus();
            return;

        }else if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtUserName.requestFocus();
            return;
        } else if (!password.matches("^[A-z]{3,8}[0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtPassward.requestFocus();
            return;
        }
            if (btnSave.getText().equalsIgnoreCase("Save")) {


                if (userBO.saveUser(new UserLoginDTO(id, name, password)))
                {

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    tblLogInDetail.getItems().add(new UserTM(id, name, password));
                    clearFields();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

                }


            } else {
                try {
                    if (userBO.updateUser(new UserLoginDTO(id, name, password))){
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                        loadAllStudents();
                        clearFields();
                    }
                } catch (Exception e) {
                    // System.out.println("Exception 2");
                    //System.out.println(e);
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                }
            }
        }



    public void DeleteOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {


        String id=tblLogInDetail.getSelectionModel().getSelectedItem().getUserID();
        try {
            userBO.deleteUser(id);
            tblLogInDetail.getItems().remove(tblLogInDetail.getSelectionModel().getSelectedItem());
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted..!").show();

            tblLogInDetail.getSelectionModel().clearSelection();
            clearFields();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something Happened.Try again Carefully..!").show();
        }


    }
}
