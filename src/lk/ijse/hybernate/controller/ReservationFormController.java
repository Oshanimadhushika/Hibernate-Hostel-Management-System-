package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.hybernate.bo.BOFactory;
import lk.ijse.hybernate.bo.BOTypes;
import lk.ijse.hybernate.bo.custom.RoomBO;
import lk.ijse.hybernate.bo.custom.impl.PurchaseReserveBOImpl;
import lk.ijse.hybernate.dto.ReservationDTO;
import lk.ijse.hybernate.dto.RoomDTO;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.entity.Room;
import lk.ijse.hybernate.entity.SetLabel;
import lk.ijse.hybernate.entity.Student;
import lk.ijse.hybernate.view.tdm.ReservationTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationFormController {
    public AnchorPane ReservationFormContext;
    public JFXTextField txtStudentName;
    public JFXComboBox cmbStudentID;
    public TableView<ReservationTM>tblReservation;
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
    public Label lblRoomQty2;
    public Label lblRoomQty3;
    public Label lblRoomQty4;
    public TableColumn colReservationID;
    public JFXButton btnAddToRemain;
    public Label lblReserveID;
    public Text lblRoomId1;
    public Text lblRoomId2;
    public Text lblRoomid3;
    public Text lblRoomId4;
    String reservationId;
    int preQty;
    ArrayList<RoomDTO> allrooms;

    PurchaseReserveBOImpl purchaseReserveBO = BOFactory.getInstance().getBO(BOTypes.PERCHASE_RESERVE);
    RoomBO roomBO = BOFactory.getInstance().getBO(BOTypes.ROOM);


    public void initialize() {

        RF();

        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reserveID"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomID"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("studentQty"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservation.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                if(tblReservation.getSelectionModel().getSelectedItem()!=null){
                    try {
                        if(purchaseReserveBO.deleteReservation(reservationId)){
                            new Alert(Alert.AlertType.CONFIRMATION,"Deleted.....").show();

                            ReservationDTO reservationDTO = purchaseReserveBO.searchReservation(reservationId);
                            Room roomID = reservationDTO.getRoomID();

                            int q=roomID.getQty()+preQty;
                            System.out.println(q);

                            RoomDTO roomDTO=new RoomDTO(roomID.getRoom_type_id(),roomID.getType(),roomID.getKey_money(),q);

                            roomBO.updateRoom(roomDTO);

                            tblReservation.getItems().remove(param.getValue());
                            tblReservation.getSelectionModel().clearSelection();
                            clearFields();

                        }else {

                            new Alert(Alert.AlertType.ERROR,"Try Again.....").show(); ;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR,"Please Select Row....").show(); ;
                }








            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        try {
            loadAllReservation();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadStudentIds();
        loadRoomIds();

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) newValue);
                txtStudentName.setText(studentDTO.getStudentName());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        cmbRoomID.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) newValue);
                txtRoomType.setText(roomDTO.getRoomType());
                txtRoomQty.setText((String.valueOf(roomDTO.getRoomQty())));
                txtKeyMoney.setText(roomDTO.getKeyMoney());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));


    }

    private  void RF(){

        reservationId=generateNewOrderId();
        lblReserveID.setText(reservationId);

    }
    private void clearFields(){
        cmbStudentID.setValue(null);
        txtStudentName.clear();
        cmbRoomID.setValue(null);
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtRoomQty.clear();
        txtStatus.clear();
        txtStudentQty.clear();
    }

    public void ReserveOnAction(ActionEvent actionEvent) throws Exception {
        String res_id = lblReserveID.getText();
        LocalDate date = DashBoardFormController.date;
        StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) cmbStudentID.getValue());
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) cmbRoomID.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtStudentQty.getText());


        ReservationDTO reservationDTO = new ReservationDTO(res_id, date, student, room, key_money, status, qty);
        if(purchaseReserveBO.purchaseReserveSave(reservationDTO)){
            updateRoomQty((String) cmbRoomID.getValue());
            loadAllReservation();
            RF();
            new Alert(Alert.AlertType.CONFIRMATION,"Saved.......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
        }



    }

    public void updateOnAction(ActionEvent actionEvent) throws Exception {
        String res_id = lblReserveID.getText();
        LocalDate date = DashBoardFormController.date;
        StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) cmbStudentID.getValue());
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) cmbRoomID.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtStudentQty.getText());


        ReservationDTO reservationDTO = new ReservationDTO(res_id, date, student, room, key_money, status, qty);

        if(purchaseReserveBO.UpdateReservation(reservationDTO)){
            loadAllReservation();


            int b=preQty-Integer.parseInt(txtStudentQty.getText());



            RoomDTO roomDTO1=new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),b);

            roomBO.updateRoom(roomDTO1);


            new Alert(Alert.AlertType.CONFIRMATION,"Updated.......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
        }
    }

    public void AddToRemainOnAction(ActionEvent actionEvent) throws IOException {

        ReservationFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/RemainKeyMoneyForm.fxml"));
        ReservationFormContext.getChildren().add(parent);
    }




    public void loadStudentIds() {
        try {
            cmbStudentID.getItems().addAll(purchaseReserveBO.getStudentIds());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRoomIds() {
        try {
            cmbRoomID.getItems().addAll(purchaseReserveBO.getRoomIds());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAllReservation() throws Exception {
        ObservableList<ReservationTM> observableList = FXCollections.observableArrayList();
        List<ReservationDTO> list = purchaseReserveBO.getAllReservation();

        for (ReservationDTO r : list) {
            String reserveID = r.getRes_id();
            Room room = r.getRoomID();
            String roomID = room.getRoom_type_id();
            String roomType = room.getType();
            int studentQty = r.getQty();
            double keyMoney = r.getKey_money();
            String status = r.getStatus();


            ReservationTM reservationTM = new ReservationTM(reserveID, roomID, roomType, studentQty, keyMoney, status);
            observableList.add(reservationTM);
            tblReservation.setItems(observableList);
        }
    }

    public void reservationTableClicked(MouseEvent mouseEvent) throws SQLException, IOException, ClassNotFoundException {
        if (tblReservation.getSelectionModel().getSelectedItem() != null) {
            ReservationTM selectedItem = tblReservation.getSelectionModel().getSelectedItem();
            reservationId = selectedItem.getReserveID();

            ReservationDTO reservationDTO = purchaseReserveBO.searchReservation(reservationId);
            Student student = reservationDTO.getStudentID();
            Room roomID = reservationDTO.getRoomID();
            preQty=roomID.getQty()+selectedItem.getStudentQty();
            cmbStudentID.setValue(student.getStudent_id());
            txtStudentName.setText(student.getStudentName());

            lblReserveID.setText(selectedItem.getReserveID());

            cmbRoomID.setValue(selectedItem.getRoomID());
            txtRoomType.setText(selectedItem.getRoomType());
            txtKeyMoney.setText(String.valueOf(selectedItem.getKeyMoney()));
            txtRoomQty.setText(String.valueOf(roomID.getQty()));

            txtStatus.setText(selectedItem.getStatus());
            txtStudentQty.setText(String.valueOf(selectedItem.getStudentQty()));
        }
    }

    public void updateRoomQty(String id) throws SQLException, IOException, ClassNotFoundException {
        RoomDTO roomDTO = purchaseReserveBO.searchRooms(id);
        int newqty=roomDTO.getRoomQty()-Integer.parseInt(txtStudentQty.getText());

        roomDTO.setRoomQty(newqty);
        roomBO.updateRoom(roomDTO);
    }



    public String generateNewOrderId() {

        try {
            return purchaseReserveBO.generateNewOrderID();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();

        return "R001";
    }

    /*private void loadAllLabel() throws SQLException, IOException, ClassNotFoundException {
      String room_qty1=lblRoomQty1.getText();
      String room_id1=lblRoomId1.getText();

        String room_qty2=lblRoomQty2.getText();
        String room_id2=lblRoomId2.getText();

        String room_qty3=lblRoomQty3.getText();
        String room_id3=lblRoomid3.getText();

        String room_qty4=lblRoomQty4.getText();
        String room_id4=lblRoomId4.getText();


        ArrayList<SetLabel> allLabel = new ArrayList<>();
        allLabel.add(new SetLabel(room_id1,room_qty1));
        allLabel.add(new SetLabel(room_id2,room_qty2 ));
        allLabel.add(new SetLabel( room_id3,room_qty3));
        allLabel.add(new SetLabel(room_id4,room_qty4));

        for (int i = 0; i < allLabel.size(); i++) {
            if (i< allrooms.size()){
                SetLabel.get(i).getrm_ID.setText(allrooms.get(i).getRoomID()+"  "+allrooms.get(i).getRoomType());
                SetLabel.get(i).getAvailable().setText(allrooms.get(i).getRoomQty()==0 ? "No" : "Yes");
            }else {
                SetLabel.get(i).getTypeAndId().setText("No");
                SetLabel.get(i).getRental().setText("No");
                SetLabel.get(i).getAvailable().setText("No");
            }
        }

    }*/
}
