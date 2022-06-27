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

        configuration.addAnnotatedClass(Reservation.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(UserLogin.class);

        sessionFactory = configuration.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
/*
public class FactoryConfiguration {
    private static FactoryConfiguration hibernateUtil;
    private static SessionFactory sessionFactory;

   */
/* public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties dbSetting = new Properties();
                dbSetting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                dbSetting.put(Environment.URL, "jdbc:mysql://localhost:3306/Hostel_System");
                dbSetting.put(Environment.USER, "root");
                dbSetting.put(Environment.PASS, "1234");
                dbSetting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                dbSetting.put(Environment.SHOW_SQL, "true");
                dbSetting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                dbSetting.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(dbSetting);
                configuration
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(Room.class)
                        .addAnnotatedClass(Reservation.class)
                        .addAnnotatedClass(UserLogin.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}*//*


  private FactoryConfiguration() throws IOException {


          Configuration configuration = new Configuration();
          Properties p = new Properties();

          p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));

      configuration.setProperties(p);

          configuration.addAnnotatedClass(Student.class);
          configuration.addAnnotatedClass(Room.class);
          configuration.addAnnotatedClass(Reservation.class);
          configuration.addAnnotatedClass(UserLogin.class);

          sessionFactory = configuration.buildSessionFactory();

      }

  public static FactoryConfiguration getInstance() throws IOException {
      return (hibernateUtil==null)? hibernateUtil=new FactoryConfiguration() : hibernateUtil;
  }
  public Session getSession(){
      return sessionFactory.openSession();
  }
    }
*/
