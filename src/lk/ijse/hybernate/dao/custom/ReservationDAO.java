package lk.ijse.hybernate.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.hybernate.dao.CrudDAO;
import lk.ijse.hybernate.dto.RemainKeyMnyDTO;
import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.view.tdm.RemainKeyMnyTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation,String> {

    public ArrayList<Reservation> searchReservation(String enteredText) throws SQLException, ClassNotFoundException;

    public ObservableList<RemainKeyMnyTM> getRemainKeyMoney() throws SQLException, ClassNotFoundException, IOException;

}
