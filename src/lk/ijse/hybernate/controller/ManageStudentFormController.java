package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ManageStudentFormController {
    public AnchorPane ManageStudentContext;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtConNo;
    public JFXTextField txtDOB;
    public JFXComboBox cmbGender;
    public TableView tblStudent;
    public TableColumn colStudentId;
    public TableColumn colAddress;
    public TableColumn colConNo;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableColumn colName;

    public void AddNewStudentOnAction(ActionEvent actionEvent) {
    }

    public void SaveStudentOnAction(ActionEvent actionEvent) {
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
    }
}
