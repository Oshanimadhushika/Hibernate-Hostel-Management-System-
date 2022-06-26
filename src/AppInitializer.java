import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hybernate.entity.Student;
import lk.ijse.hybernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

import static javafx.application.Application.launch;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/hybernate/view/LoginForm.fxml"))));
        primaryStage.show();
        primaryStage.centerOnScreen();

        Student s1 = new Student();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(s1);

        transaction.commit();
        session.close();
    }
}
