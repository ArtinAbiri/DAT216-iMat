package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
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
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(searchBar.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    private final Model model = Model.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        updateProductsPanel(model.getProducts());
    }

    private void updateProductsPanel(List<Product> products) {
        productsFlowPane.getChildren().clear();

        for (Product product : products) {
            productsFlowPane.getChildren().add(new productPanel(product));
        }
    }
    private void updateProductList(List<Product> products) {

        productsFlowPane.getChildren().clear();

        for (Product product : products) {

            productsFlowPane.getChildren().add(new productPanel(product));
        }

    }
}
