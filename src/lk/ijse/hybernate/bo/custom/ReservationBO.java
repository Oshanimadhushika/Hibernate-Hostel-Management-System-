package lk.ijse.hybernate.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.hybernate.bo.SuperBO;
import lk.ijse.hybernate.dto.RemainKeyMnyDTO;
import lk.ijse.hybernate.dto.ReservationDTO;
import lk.ijse.hybernate.dto.RoomDTO;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.view.tdm.RemainKeyMnyTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationBO extends SuperBO {
    ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<ReservationDTO> searchReserveDetails(String enteredText) throws SQLException, ClassNotFoundException;

    ObservableList<RemainKeyMnyTM> getRemainKeyMnyStudent() throws SQLException, ClassNotFoundException, IOException;

}
