package lk.ijse.hybernate.controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class RemainKeyMoneyFormController {
    public AnchorPane RemainKeyMoneyFormContext;
    public TableView tblRemainKeyMoney;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colStatus;
    public TextField txtSearch;

    public void Search_On_Key_Released(KeyEvent keyEvent) {
    }
}
