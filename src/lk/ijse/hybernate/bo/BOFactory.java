package lk.ijse.hybernate.bo;

import lk.ijse.hybernate.bo.custom.impl.StudentBOImpl;

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
            default:
                return null;
        }
    }
}
