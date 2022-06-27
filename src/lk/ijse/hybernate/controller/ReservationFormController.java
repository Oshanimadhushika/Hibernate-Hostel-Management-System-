package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;

public class ReservationFormController {
    public AnchorPane ReservationFormContext;
    public JFXTextField txtreservationID;
    public JFXTextField txtStudentName;
    public JFXComboBox cmbStudentID;
    public TableView tblReservation;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colStudentQty;
    public TableColumn colKeyMny;
    public TableColumn colStatus;
    public TableColumn colDelete;
    public JFXComboBox cmbRoomID;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomQty;
    public TextField txtStatus;
    public TextField txtStudentQty;
    public Label lblRoomQty1;
    public Ellipse lblRoomQty2;
    public Label lblRoomQty3;
    public Label lblRoomQty4;
    public TextField txtSearch;
    public TableColumn colReservationID;
    public JFXButton btnAddToRemain;

    public void ReserveOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void AddToRemainOnAction(ActionEvent actionEvent) {
    }

    public void Search_On_Key_Released(KeyEvent keyEvent) {
    }
}
