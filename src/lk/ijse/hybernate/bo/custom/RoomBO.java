package lk.ijse.hybernate.bo.custom;

import lk.ijse.hybernate.bo.SuperBO;
import lk.ijse.hybernate.dto.RoomDTO;
import lk.ijse.hybernate.dto.StudentDTO;

import java.io.IOException;
import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> getAllRooms() throws IOException;

    boolean saveRoom(RoomDTO dto) throws IOException;

    boolean updateRoom(RoomDTO dto) throws IOException;

    boolean deleteRoom(String id) throws IOException;
}
