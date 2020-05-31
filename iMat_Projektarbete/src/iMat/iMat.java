package iMat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class iMat extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("startscreen.fxml"));
        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); //våra bilder >>> era bilder. ser ni detta så ge oss femma (för bilderna)
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                IMatDataHandler.getInstance().shutDown();
            }
        }));
    }
}
