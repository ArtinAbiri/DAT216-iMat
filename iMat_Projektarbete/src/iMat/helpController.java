package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class helpController {
    @FXML
    private void loadStoreFromHelpscreen(ActionEvent event) throws IOException {
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();
    }
}
