package lk.ijse.hybernate.dao.custom.impl;

import lk.ijse.hybernate.dao.custom.ReservationDAO;
import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.entity.Student;
import lk.ijse.hybernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ResevationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reservation";
        Query query=session.createQuery(hql);
        List<Reservation> reservations=query.list();

        transaction.commit();
        session.close();
        return reservations;
    }

    @Override
    public boolean save(Reservation entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Reservation entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Reservation reservation = session.load(Reservation.class, s);

        session.delete(reservation);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean find(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws IOException  {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC").setMaxResults(1);
        List list = query.list();
        session.close();

        String newUserId = "";

        String lastUserId = list.toString();
        String[] split = lastUserId.split("[A-z]");
        if(split.length==0){
            return "R001";
        }else{
            Integer integer = Integer.valueOf(split[2]);
            ++integer;

            if(!list.isEmpty()){
                if (integer>=100) {
                    newUserId = "R" + String.valueOf(integer) ;
                }else if(integer>=10){
                    newUserId = "R0" + String.valueOf(integer);
                }else{
                    newUserId = "R00" + String.valueOf(integer);
                }
                return newUserId;

            }else{
                return "R001";
        }

        }

    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation= session.get(Reservation.class,s);



        transaction.commit();
        session.close();
        return reservation;
    }



}
