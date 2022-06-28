package lk.ijse.hybernate.dao;

import lk.ijse.hybernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hybernate.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO>T getDAO(DAOType type){
        switch (type){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case ROOM:
                return (T) new RoomDAOImpl();
            default:
                return null;
        }
    }
}
