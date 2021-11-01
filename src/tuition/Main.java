package tuition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A launcher class to initialize and create the Tuition Manager GUI.
 * @author Sai Maduri, Heer Patel
 *
 */
public class Main extends Application {
    
    /**
     * Initializes the Tuition Manager scene by loading the main-view.fxml file.
     * @param Stage application stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 550);
        stage.setTitle("Tuition Manager");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Calls the JavaFX Application's launch() method, which starts up the project.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}