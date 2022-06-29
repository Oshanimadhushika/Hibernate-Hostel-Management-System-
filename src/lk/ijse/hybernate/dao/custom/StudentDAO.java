package lk.ijse.hybernate.dao.custom;

import lk.ijse.hybernate.dao.CrudDAO;
import lk.ijse.hybernate.dao.SuperDAO;
import lk.ijse.hybernate.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {

    public List<String> getStudentIds() throws IOException;
}
