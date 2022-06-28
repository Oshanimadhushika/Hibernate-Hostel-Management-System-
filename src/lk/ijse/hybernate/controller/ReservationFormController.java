package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import lk.ijse.hybernate.bo.BOFactory;
import lk.ijse.hybernate.bo.BOTypes;
import lk.ijse.hybernate.bo.custom.StudentBO;
import lk.ijse.hybernate.bo.custom.impl.PurchaseReserveBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationFormController {
    public AnchorPane ReservationFormContext;
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
    public Label lblReserveID;
   // private String reserve_id;


    PurchaseReserveBOImpl purchaseReserveBO = BOFactory.getInstance().getBO(BOTypes.PERCHASE_RESERVE);

    public void initialize(){

       /* reserve_id=generateNewOrderId();
        lblReserveID.setText(reserve_id);*/
    }

    public void ReserveOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void AddToRemainOnAction(ActionEvent actionEvent) {
    }

    public void Search_On_Key_Released(KeyEvent keyEvent) {
    }

    /*public String generateNewOrderId() {

        try {
            return purchaseReserveBO.generateNewOrderID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // System.out.println(e);
        }
        return "OID-001";
    }*/
}
