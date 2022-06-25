package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ManageRoomFormController {
    public AnchorPane ManageRoomContext;
    public JFXTextField txtRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomQty;
    public TableView tblRoom;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeymny;
    public TableColumn colRoomQty;

    public void AddNewRoomOnAction(ActionEvent actionEvent) {
    }

    public void SaveRoomOnAction(ActionEvent actionEvent) {
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
    }
}
