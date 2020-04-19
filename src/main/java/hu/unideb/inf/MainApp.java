package hu.unideb.inf;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/NewUIdesign.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("BRA - Best Restaurant Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.main() serves only as fallback in case the application can not be
 launched through deployment artifacts, e.g., in IDEs with limited FX
 support.
     * NetBeans ignores main().
     *
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
        Connect valami = new Connect();
        ResultSet rs = valami.getData("asztalok", "*");
        while (rs.next()){
                int id = rs.getInt("id");
                int ferohely = rs.getInt("ferohely");
                System.out.format("%d, %d\n", id, ferohely);
            }
        
    }

}
