package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class startscreenController implements Initializable {
    @FXML
    AnchorPane startscreenRootPane;

    private Parent storeParent;
    private Scene storeScene;

    @FXML
    private void loadStoreFromStartscreen(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();
    }

    @FXML
    private void loadGuide(ActionEvent event) throws IOException {
        Parent guideParent = FXMLLoader.load(getClass().getResource("guide.fxml"));
        Scene guideScene = new Scene(guideParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(guideScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iMatController iMatController = new iMatController();
        iMatController.hashstart();
        iMatController.clearShoppingCart();
        System.out.println(iMatController.hashproducts.toString());
        try {
             storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
             storeScene = new Scene(storeParent);


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
