package lk.ijse.hybernate.bo;

import lk.ijse.hybernate.bo.custom.UserBO;
import lk.ijse.hybernate.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return (null == boFactory) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType){
        switch (boType){
            case STUDENT:
                return (T) new StudentBOImpl();
            case ROOM:
                return (T) new RoomBOImpl();
            case RESERVATION:
                return (T) new ReservationBOImpl();
            case PERCHASE_RESERVE:
                return (T) new PurchaseReserveBOImpl();
            case USER:
                return (T) new UserBOImpl();
            default:
                return null;
        }
    }

}
