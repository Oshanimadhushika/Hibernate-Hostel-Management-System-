package lk.ijse.hybernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane LoginFormContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnlogIn;

    public void LogInOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashBoardForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage=(Stage) LoginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void Eye_On_Mouse_Click(MouseEvent mouseEvent) {
    }
}
