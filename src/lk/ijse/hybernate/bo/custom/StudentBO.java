package lk.ijse.hybernate.bo.custom;

import lk.ijse.hybernate.bo.SuperBO;
import lk.ijse.hybernate.dto.StudentDTO;

public interface StudentBO extends SuperBO {
    public boolean add(StudentDTO studentDTO) throws Exception;

    public boolean update(StudentDTO studentDTO) throws Exception;

    public boolean delete(String id) throws Exception;
}
