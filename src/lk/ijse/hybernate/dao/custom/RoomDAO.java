package lk.ijse.hybernate.dao.custom;

import lk.ijse.hybernate.dao.CrudDAO;
import lk.ijse.hybernate.entity.Room;

import java.io.IOException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    public List getRoomIds() throws IOException;
}
