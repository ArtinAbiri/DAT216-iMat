package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class iMatController implements Initializable {
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

    @FXML
    Text logoText;
    @FXML
    TextField searchBar;
    @FXML
    Button searchButton;
    @FXML
    Text minaSidorText;
    @FXML
    Text varukorgText;
    @FXML
    Button varukorgButton;

    @FXML
    FlowPane productsFlowPane;

    @FXML
    Circle shoppingcartPay;

    @FXML
    AnchorPane shoppingCart;
    @FXML
    Button closeCartButtonBig;
    @FXML
    Button closeCartButtonSmall;
    @FXML
    Button favouriteButton;

    private Boolean FavouriteButtonSelected = false;

    public void initialize(URL url, ResourceBundle rb) {
        updateProductList(model.getProducts());
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(searchBar.getText());
        updateProductList(matches);
    }

    @FXML
    private void handleFavouriteProductsAction(ActionEvent event) {
        if(!FavouriteButtonSelected) {
            List<Product> matches = model.iMatDataHandler.favorites();
            updateProductList(matches);
            FavouriteButtonSelected = true;
            favouriteButton.getStyleClass().add("subcategory-button-selected");
        }
        else{
            List<Product> matches = model.findProducts("");
            updateProductList(matches);
            FavouriteButtonSelected = false;
            favouriteButton.getStyleClass().remove("subcategory-button-selected");
        }
    }

    @FXML
    private void handleStartShoppingAction(ActionEvent event) {
        mainScreen.toFront();
    }

    @FXML
    private void handleCloseShoppingCartAction(ActionEvent event) {
        shoppingCart.toBack();
    }

    @FXML
    private void handleOpenShoppingCartAction(ActionEvent event) {
        shoppingCart.toFront();
    }

    private final Model model = Model.getInstance();

    private void updateProductList(List<Product> products) {
        productsFlowPane.getChildren().clear();

        for (Product product : products) {

            productsFlowPane.getChildren().add(new productPanel(product));
        }

    }

    @FXML
    private void loadCheckout(ActionEvent event) throws IOException {
        Parent checkoutParent = FXMLLoader.load(getClass().getResource("checkout.fxml"));
        Scene checkoutScene = new Scene(checkoutParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(checkoutScene);
        window.show();
    }

    @FXML
    private void reloadStore(ActionEvent event) throws IOException {
        updateProductList(model.getProducts());
    }
}
