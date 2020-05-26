package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class checkoutController {
    @FXML
    AnchorPane checkoutPart1;

    @FXML
    AnchorPane checkoutPart2;

    @FXML
    AnchorPane checkoutPart3;

    @FXML
    private void loadStoreFromCheckout(ActionEvent event) throws IOException {
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();
    }

    @FXML
    private void loadCheckout1() {
        checkoutPart1.toFront();
    }

    @FXML
    private void loadCheckout2() {
        checkoutPart2.toFront();
    }

    @FXML
    private void loadCheckout3() {
        checkoutPart3.toFront();
    }

    @FXML
    private void completePurchase() {
        //purchaseComplete.toFront();
    }
}
