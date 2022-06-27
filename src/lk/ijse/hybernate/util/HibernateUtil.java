package lk.ijse.hybernate.util;


import lk.ijse.hybernate.entity.Reservation;
import lk.ijse.hybernate.entity.Room;
import lk.ijse.hybernate.entity.Student;
import lk.ijse.hybernate.entity.UserLogin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;


public class HibernateUtil {
    // private static HibernateUtil factoryConfiguration;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
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
}

  /*private HibernateUtil() {


          Configuration configuration = new Configuration();
          Properties p = new Properties();
      try {
          p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
      } catch (IOException e) {
          e.printStackTrace();
      }
      configuration.setProperties(p);

          configuration.addAnnotatedClass(Student.class);
          configuration.addAnnotatedClass(Room.class);
          configuration.addAnnotatedClass(Reservation.class);
          configuration.addAnnotatedClass(UserLogin.class);

          sessionFactory = configuration.buildSessionFactory();

      }

  public static HibernateUtil getInstance() throws IOException {
      return (factoryConfiguration==null)? factoryConfiguration=new HibernateUtil() : factoryConfiguration;
  }
  public Session getSession(){
      return sessionFactory.openSession();
  }
    }
*/