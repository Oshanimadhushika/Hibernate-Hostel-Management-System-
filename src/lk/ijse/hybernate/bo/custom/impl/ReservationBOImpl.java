package lk.ijse.hybernate.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hybernate.bo.custom.ReservationBO;
import lk.ijse.hybernate.dao.DAOFactory;
import lk.ijse.hybernate.dao.DAOType;
import lk.ijse.hybernate.dao.custom.ReservationDAO;
import lk.ijse.hybernate.dao.custom.RoomDAO;
import lk.ijse.hybernate.dao.custom.StudentDAO;
import lk.ijse.hybernate.dto.RemainKeyMnyDTO;
import lk.ijse.hybernate.dto.ReservationDTO;
import lk.ijse.hybernate.dto.RoomDTO;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.entity.Room;
import lk.ijse.hybernate.entity.Student;
import lk.ijse.hybernate.view.tdm.RemainKeyMnyTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO=DAOFactory.getInstance().getDAO(DAOType.RESERVATION);

    @Override
    public ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ReservationDTO> searchReserveDetails(String enteredText) throws SQLException, ClassNotFoundException {
      return  null;
    }

    @Override
    public ObservableList<RemainKeyMnyTM> getRemainKeyMnyStudent() throws SQLException, IOException, ClassNotFoundException {
        return reservationDAO.getRemainKeyMoney();
    }


}
