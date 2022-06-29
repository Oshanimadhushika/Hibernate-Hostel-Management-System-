package lk.ijse.hybernate.bo.custom;

import lk.ijse.hybernate.bo.SuperBO;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.dto.UserLoginDTO;

import java.io.IOException;
import java.util.List;

public interface UserBO extends SuperBO {
    List<UserLoginDTO> getAllUser() throws IOException;

    boolean saveUser(UserLoginDTO dto) throws IOException;

    boolean updateUser(UserLoginDTO dto) throws IOException;

    boolean deleteUser(String id) throws IOException;
}
