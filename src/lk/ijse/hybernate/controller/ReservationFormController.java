package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import lk.ijse.hybernate.bo.BOFactory;
import lk.ijse.hybernate.bo.BOTypes;
import lk.ijse.hybernate.bo.custom.RoomBO;
import lk.ijse.hybernate.bo.custom.impl.PurchaseReserveBOImpl;
import lk.ijse.hybernate.dto.ReservationDTO;
import lk.ijse.hybernate.dto.RoomDTO;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.entity.Room;
import lk.ijse.hybernate.entity.Student;
import lk.ijse.hybernate.view.tdm.ReservationTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public Ellipse lblRoomQty2;
    public Label lblRoomQty3;
    public Label lblRoomQty4;
    public TextField txtSearch;
    public TableColumn colReservationID;
    public JFXButton btnAddToRemain;
    public Label lblReserveID;
    String reservationId;
    // private String reserve_id;

    PurchaseReserveBOImpl purchaseReserveBO = BOFactory.getInstance().getBO(BOTypes.PERCHASE_RESERVE);
    RoomBO roomBO = BOFactory.getInstance().getBO(BOTypes.ROOM);


    public void initialize() {




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
                            new Alert(Alert.AlertType.CONFIRMATION,"Deleted.....").show(); ;
                            tblReservation.getItems().remove(param.getValue());
                            tblReservation.getSelectionModel().clearSelection();
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







//
//                boolean b = false;
//                try {
//                    b = purchaseReserveBO.deleteReservation(selectItem.getReserveID());
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                if (b) {
//                    new Alert(Alert.AlertType.INFORMATION, "Deleted SussesFull").show();
//                    tblReservation.getItems().clear();
//                   // tblOrderId.getItems().clear();
//                    try {
//                        initialize();
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    new Alert(Alert.AlertType.INFORMATION, "Something Went Wrong..").show();
//                }
//
//
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

       /* reserve_id=generateNewOrderId();
        lblReserveID.setText(reserve_id);*/
    }

    public void ReserveOnAction(ActionEvent actionEvent) throws Exception {
        String res_id = "R-001";
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
            new Alert(Alert.AlertType.CONFIRMATION,"Saved.......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void AddToRemainOnAction(ActionEvent actionEvent) {
    }

    public void Search_On_Key_Released(KeyEvent keyEvent) {
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
        }
    }

    public void updateRoomQty(String id) throws SQLException, IOException, ClassNotFoundException {
        RoomDTO roomDTO = purchaseReserveBO.searchRooms(id);
        int newqty=roomDTO.getRoomQty()-Integer.parseInt(txtStudentQty.getText());

        roomDTO.setRoomQty(newqty);
        roomBO.updateRoom(roomDTO);
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
