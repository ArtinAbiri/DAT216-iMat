package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class orderHistoryController implements Initializable {
    Model model = Model.getInstance();
    List<Order> previousOrders;




    @FXML
    Accordion orderHistoryAccordion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        previousOrders = model.getOrders();

        Collections.sort(previousOrders, new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {
                return (order2.getOrderNumber() > order1.getOrderNumber()) ? order2.getOrderNumber() : order1.getOrderNumber();
            }
        });

        displayOrders();
    }

    public void displayOrders() {
        orderHistoryAccordion.getPanes().clear();
        for (Order order : previousOrders) {
            orderHistoryAccordion.getPanes().add(new previousOrder(order));
        }
    }

    @FXML
    private void loadStoreFromOrderHistory(ActionEvent event) throws IOException {
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();

    }
}
