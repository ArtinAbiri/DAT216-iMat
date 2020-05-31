package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class helpController{
    Model model=Model.getInstance();
    @FXML
    TextField searchBar;
    @FXML
    Button searchButton;

    @FXML
    private void loadStoreFromHelpscreen(ActionEvent event) throws IOException {
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();

    }
    @FXML
    private void search(ActionEvent event) throws IOException {
        model.searchText=searchBar.getText();
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();

    }

    @FXML
    private void loadOrderHistory(ActionEvent event) throws IOException {
        Parent helpParent = FXMLLoader.load(getClass().getResource("orderHistory.fxml"));
        Scene checkoutScene = new Scene(helpParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(checkoutScene);
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


}
