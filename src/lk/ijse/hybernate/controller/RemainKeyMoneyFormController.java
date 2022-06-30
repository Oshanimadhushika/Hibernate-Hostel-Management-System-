package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hybernate.bo.BOFactory;
import lk.ijse.hybernate.bo.BOTypes;
import lk.ijse.hybernate.bo.custom.ReservationBO;
import lk.ijse.hybernate.bo.custom.RoomBO;
import lk.ijse.hybernate.dto.RemainKeyMnyDTO;
import lk.ijse.hybernate.dto.ReservationDTO;
import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.entity.Room;
import lk.ijse.hybernate.view.tdm.RemainKeyMnyTM;
import lk.ijse.hybernate.view.tdm.ReservationTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RemainKeyMoneyFormController {
    public AnchorPane RemainKeyMoneyFormContext;
    public TableView<RemainKeyMnyTM> tblRemainKeyMoney;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colStatus;


    ReservationBO reservationBO = BOFactory.getInstance().getBO(BOTypes.RESERVATION);

    public void initialize() throws SQLException, IOException, ClassNotFoundException {

        tblRemainKeyMoney.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tblRemainKeyMoney.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        tblRemainKeyMoney.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("status"));

        loadRemainKeyMoneyStudent();
    }
    private void loadRemainKeyMoneyStudent() throws SQLException, IOException, ClassNotFoundException {
        //List<RemainKeyMnyDTO>  remainKeyMny=reservationBO.getRemainKeyMnyStudent();
        ObservableList<RemainKeyMnyTM> remainKeyMnyStudent = reservationBO.getRemainKeyMnyStudent();
        tblRemainKeyMoney.setItems(remainKeyMnyStudent);
        /*for (RemainKeyMnyDTO r : remainKeyMny) {
            String ID = r.getStudentID();
            String name= r.getStudentName();
            String status = r.getStatus();



            RemainKeyMnyTM remainKeyMnyTM = new RemainKeyMnyTM(ID,name,status);
            observableList.add(remainKeyMnyTM);
            tblRemainKeyMoney.setItems(observableList);*/
        }

    }





