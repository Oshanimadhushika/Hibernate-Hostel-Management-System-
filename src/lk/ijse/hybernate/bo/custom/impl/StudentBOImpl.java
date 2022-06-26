package lk.ijse.hybernate.bo.custom.impl;

import lk.ijse.hybernate.bo.BOFactory;
import lk.ijse.hybernate.bo.BOTypes;
import lk.ijse.hybernate.bo.custom.StudentBO;
import lk.ijse.hybernate.dao.DAOFactory;
import lk.ijse.hybernate.dao.DAOType;
import lk.ijse.hybernate.dao.custom.StudentDAO;
import lk.ijse.hybernate.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.entity.Student;

public class StudentBOImpl implements StudentBO {

    StudentDAOImpl studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean add(StudentDTO studentDTO) throws Exception {
       /* return studentDAO.add(new Student(
                studentDTO.getStudentID(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getDob(),
                studentDTO.getGender()
        ));*/
        return false;
    }

    @Override
    public boolean update(StudentDTO studentDTO) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }
}
