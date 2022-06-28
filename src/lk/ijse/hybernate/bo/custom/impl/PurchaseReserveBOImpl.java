package lk.ijse.hybernate.bo.custom.impl;

import lk.ijse.hybernate.bo.custom.PurchaseReserveBO;
import lk.ijse.hybernate.dao.DAOFactory;
import lk.ijse.hybernate.dao.DAOType;
import lk.ijse.hybernate.dao.custom.ReservationDAO;
import lk.ijse.hybernate.dao.custom.RoomDAO;
import lk.ijse.hybernate.dao.custom.StudentDAO;
import lk.ijse.hybernate.dto.ReservationDTO;
import lk.ijse.hybernate.dto.RoomDTO;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.entity.Room;
import lk.ijse.hybernate.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseReserveBOImpl implements PurchaseReserveBO {

    RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOType.ROOM);
   StudentDAO studentDAO=DAOFactory.getInstance().getDAO(DAOType.STUDENT);
   ReservationDAO reservationDAO=DAOFactory.getInstance().getDAO(DAOType.RESERVATION);

    @Override
    public boolean purchaseReserve(ReservationDTO dto) throws IOException {
        return reservationDAO.save(new Reservation(
                dto.getRes_id(),
                dto.getDate(),
                dto.getKey_money(),
                dto.getQty(),
                dto.getStatus(),
                dto.getRoomID(),
                dto.getStudentID()
                ));

    }

    @Override
    public RoomDTO searchRooms(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
       return null;

    }

    @Override
    public boolean checkRoomIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.find(id);
    }

    @Override
    public boolean checkStudentIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.find(id);
    }

    @Override
    public String generateNewOrderID() throws IOException {
        return reservationDAO.generateNewID();
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();

        for(Student s: all){
            allStudents.add(new StudentDTO(
                    s.getStudent_id(),
                    s.getStudentName(),
                    s.getStudentAddress(),
                    s.getContac_no(),
                    s.getDob(),
                    s.getGender()));
        }

        return allStudents;
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
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

   /* @Override
    public boolean saveReservation(ReservationDTO dto) throws Exception {
        return reservationDAO.save(new Reservation(
                dto.getRes_id(),
                dto.getDate(),
                dto.getKey_money(),
                dto.getQty(),
                dto.getStatus(),
                dto.getRoomID(),
                dto.getStudentID()


                ));

    }

    @Override
    public boolean updateReservation(ReservationDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteReservation(String bookingId) throws Exception {
        return false;
    }

    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();

        for(Student s: all){
            allStudents.add(new StudentDTO(
                    s.getStudent_id(),
                    s.getStudentName(),
                    s.getStudentAddress(),
                    s.getContac_no(),
                    s.getDob(),
                    s.getGender()));
        }

        return allStudents;
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
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
    }*/
}
