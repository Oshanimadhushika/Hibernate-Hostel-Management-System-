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
    private  SessionFactory sessionFactory;

  private FactoryConfiguration() {
     /* Configuration configuration = new Configuration();
      Properties p = new Properties();
      p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));

      configuration.setProperties(p);

      configuration.addAnnotatedClass(Student.class);
      configuration.addAnnotatedClass(Room.class);
      configuration.addAnnotatedClass(Reservation.class);
      configuration.addAnnotatedClass(UserLogin.class);*/

      Properties p = new Properties();
      try {
          p.load(ClassLoader.getSystemClassLoader().getResourceAsStream("lk/ijse/hybernate/resources/hibernate.properties"));


      Configuration configuration = new Configuration().mergeProperties(p)
              .addAnnotatedClass(Student.class)
              .addAnnotatedClass(Room.class)
              .addAnnotatedClass(Reservation.class)
              .addAnnotatedClass(UserLogin.class);

sessionFactory=configuration.buildSessionFactory();

      } catch (IOException e) {
          // e.printStackTrace();
  }
  }
  public static FactoryConfiguration getInstance() throws IOException {
      return (factoryConfiguration==null)? factoryConfiguration=new FactoryConfiguration() : factoryConfiguration;
  }
  public Session getSession(){
      return sessionFactory.openSession();
  }
    }
