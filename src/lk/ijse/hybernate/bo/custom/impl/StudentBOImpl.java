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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public List<StudentDTO> getAllStudent() throws IOException {

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
    public boolean saveStudent(StudentDTO dto) throws IOException {
        return studentDAO.save(new Student(
                dto.getStudentID(),
                dto.getStudentName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()

        ));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return studentDAO.update(new Student(
                dto.getStudentID(),
                dto.getStudentName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()

        ));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }

   /* @Override
    public String generateStudentId() {
        return null;
    }
*/

}
