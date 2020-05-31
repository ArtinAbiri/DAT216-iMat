package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class orderHistoryController implements Initializable, ShoppingCartListener {
    Model model = Model.getInstance();
    List<Order> previousOrders;

    @FXML
    Label cartNumberOfItems;

    @FXML
    TextField searchBar;
    @FXML
    Button searchButton;
    @FXML
    Accordion orderHistoryAccordion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        previousOrders = model.getOrders();

        Collections.sort(previousOrders, new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {
                return (order2.getDate().compareTo(order1.getDate()));
            }
        });

        model.shoppingCart.addShoppingCartListener(this);
        updateCartAmount();
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
    private void loadHelp(ActionEvent event) throws IOException {
        Parent helpParent = FXMLLoader.load(getClass().getResource("help.fxml"));
        Scene checkoutScene = new Scene(helpParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(checkoutScene);
        window.show();
    }

    public void updateCartAmount() {
        cartNumberOfItems.setText(Integer.toString(model.shoppingCart.getItems().size()));
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateCartAmount();
    }

    @FXML
    private void loadStore(ActionEvent event) throws IOException {
        model.openCart = true;
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();
    }
}
