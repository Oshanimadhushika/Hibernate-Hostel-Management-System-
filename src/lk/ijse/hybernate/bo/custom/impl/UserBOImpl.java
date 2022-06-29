package lk.ijse.hybernate.bo.custom.impl;

import lk.ijse.hybernate.bo.custom.UserBO;
import lk.ijse.hybernate.dao.DAOFactory;
import lk.ijse.hybernate.dao.DAOType;
import lk.ijse.hybernate.dao.custom.StudentDAO;
import lk.ijse.hybernate.dao.custom.UserDAO;
import lk.ijse.hybernate.dto.StudentDTO;
import lk.ijse.hybernate.dto.UserLoginDTO;
import lk.ijse.hybernate.entity.Student;
import lk.ijse.hybernate.entity.UserLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);

    @Override
    public List<UserLoginDTO> getAllUser() throws IOException {
        List<UserLogin> all = userDAO.getAll();
        ArrayList<UserLoginDTO> allUser = new ArrayList<>();

        for(UserLogin u: all){
            allUser.add(new UserLoginDTO(
                    u.getUser_id(),
                    u.getUser_name(),
                    u.getPassword()
                   ));
        }

        return allUser;
    }

    @Override
    public boolean saveUser(UserLoginDTO dto) throws IOException {
        return userDAO.save(new UserLogin(
                dto.getUserID(),
                dto.getUserName(),
                dto.getPassword()


        ));
    }

    @Override
    public boolean updateUser(UserLoginDTO dto) throws IOException {
        return userDAO.update(new UserLogin(
                dto.getUserID(),
                dto.getUserName(),
                dto.getPassword()


        ));
    }

    @Override
    public boolean deleteUser(String id) throws IOException {
        return userDAO.delete(id);
    }
}
