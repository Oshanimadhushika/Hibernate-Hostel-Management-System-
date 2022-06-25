package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBoardFormController {
    public AnchorPane DashBoardFormContext;
    public AnchorPane DashBoard2Context;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnLogOut;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime();

    }

    public void ManageStudentOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard2Context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageStudentForm.fxml"));
        DashBoard2Context.getChildren().add(parent);
    }

    public void ManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard2Context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageRoomForm.fxml"));
        DashBoard2Context.getChildren().add(parent);
    }

    public void ReservationOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard2Context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ReservationForm.fxml"));
        DashBoard2Context.getChildren().add(parent);
    }

    public void LOGOUTOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void loadDateAndTime() {
        /* set Date*/
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        /* set Date*/
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateFormat dateFormat = new SimpleDateFormat("hh : mm : ss aa");
            String dateString = dateFormat.format(new Date()).toString();
            lblTime.setText(dateString);
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    private void setUI(String location) throws IOException {
        Stage stage=(Stage) DashBoardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void LogInDetailOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard2Context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/LoginDetailForm.fxml"));
        DashBoard2Context.getChildren().add(parent);
    }
}
