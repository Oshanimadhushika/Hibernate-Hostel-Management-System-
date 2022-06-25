package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class LoginDetailFormController {
    public AnchorPane LoginDetailsContext;
    public JFXTextField txtUserID;
    public JFXTextField txtPassward;
    public JFXTextField txtRoomQty;
    public TableView tblLogInDetail;
    public TableColumn colUserID;
    public TableColumn colUserName;
    public TableColumn colPassward;

    public void AddNewLogDetailOnAction(ActionEvent actionEvent) {
    }

    public void SaveNewLogDetailOnAction(ActionEvent actionEvent) {
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
    }
}
