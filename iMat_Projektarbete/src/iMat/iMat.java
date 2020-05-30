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

     //   IMatDataHandler.getInstance().reset();


        stage.setMinWidth(1600);
        stage.setMinHeight(900);
        stage.setMaxWidth(1600);
        stage.setMaxHeight(900);
        stage.setScene(scene);
        stage.show();
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
