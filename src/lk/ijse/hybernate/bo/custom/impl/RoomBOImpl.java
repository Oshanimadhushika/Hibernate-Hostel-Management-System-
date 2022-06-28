package lk.ijse.hybernate.bo.custom.impl;

import lk.ijse.hybernate.bo.custom.RoomBO;
import lk.ijse.hybernate.bo.custom.StudentBO;
import lk.ijse.hybernate.dao.DAOFactory;
import lk.ijse.hybernate.dao.DAOType;
import lk.ijse.hybernate.dao.custom.RoomDAO;
import lk.ijse.hybernate.dao.custom.StudentDAO;
import lk.ijse.hybernate.dto.RoomDTO;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.entity.Room;
import lk.ijse.hybernate.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOType.ROOM);

    @Override
    public List<RoomDTO> getAllRooms() throws IOException {
        List<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRooms = new ArrayList<>();

        for(Room r: all){
            allRooms.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()
                    ));
        }

        return allRooms;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws IOException {
        return roomDAO.save(new Room(
                dto.getRoomID(),
                dto.getRoomType(),
                dto.getKeyMoney(),
                dto.getRoomQty()

        ));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws IOException {
        return roomDAO.update(new Room(
                dto.getRoomID(),
                dto.getRoomType(),
                dto.getKeyMoney(),
                dto.getRoomQty()

        ));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }
}
