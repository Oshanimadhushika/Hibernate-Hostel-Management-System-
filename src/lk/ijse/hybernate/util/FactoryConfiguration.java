package lk.ijse.hybernate.util;


import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.entity.Room;
import lk.ijse.hybernate.entity.Student;
import lk.ijse.hybernate.entity.UserLogin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

  private FactoryConfiguration() throws IOException {
      Configuration configuration = new Configuration();
      Properties p = new Properties();
      p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));

      configuration.setProperties(p);

      configuration.addAnnotatedClass(Student.class);
      configuration.addAnnotatedClass(Room.class);
      configuration.addAnnotatedClass(Reservation.class);
      configuration.addAnnotatedClass(UserLogin.class);


      sessionFactory=configuration.buildSessionFactory();
  }
  public static FactoryConfiguration getInstance() throws IOException {
      return (factoryConfiguration==null)? factoryConfiguration=new FactoryConfiguration() : factoryConfiguration;
  }
  public Session getSession(){
      return sessionFactory.openSession();
  }
    }
