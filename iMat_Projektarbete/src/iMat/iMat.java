package iMat;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class iMat extends Application {

    @FXML
    Button startShopButton;

    @FXML
    Button startGuideButton;

    @FXML
    AnchorPane startScreen;

    @FXML
    AnchorPane mainScreen;

    @FXML
    ScrollPane mainScrollScreen;
    @FXML
    AnchorPane header;

    @FXML
    FlowPane categoryFlowPane;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("iMat/iMat.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Hello world");
        stage.show();
    }

}
