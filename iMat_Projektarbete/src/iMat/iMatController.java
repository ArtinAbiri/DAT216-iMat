package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.desktop.PreferencesEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class iMatController implements Initializable, ShoppingCartListener {

  public final static DecimalFormat df2 = new DecimalFormat("0.00");

    List<Product> PreviousList= new ArrayList<>();
    static HashMap<Integer,productPanel> hashproducts= new HashMap<Integer, productPanel>();

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
    FlowPane cartFlowPane;
    @FXML
    Label cartSumLabel;
    @FXML
    Button closeCartButtonBig;
    @FXML
    Button closeCartButtonSmall;
    @FXML
    Button favouriteButton;

    //Category buttons
    @FXML
    Button allButton;
    @FXML
    Button meatAndFishButton;
    @FXML
    Button fruitAndVegetablesButton;
    @FXML
    Button dairyButton;
    @FXML
    Button pastaPotatoRiceButton;
    @FXML
    Button ingredientsButton;
    @FXML
    Button breadButton;
    @FXML
    Button drinksButton;
    @FXML
    Button sweetsButton;

    //
    @FXML
    Label cartNumberOfItems;


    private Boolean FavouriteButtonSelected = false;

    public void initialize(URL url, ResourceBundle rb) {
        updateProductList(model.getProducts());
        //model.shoppingCart.clear();
        model.shoppingCart.addShoppingCartListener(this);


    }
 public void hashstart(){
        List<Product> allpro = model.getProducts();
        allpro.remove(allpro.size()-1);
        for (Product product : allpro) {

            hashproducts.put( product.getProductId(),new productPanel(product));
        }}
    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(searchBar.getText());
        updateProductList(matches);
    }

    @FXML
    private void handleFavouriteProductsAction(ActionEvent event) {
        if(!FavouriteButtonSelected) {
            List<Product> matches = model.iMatDataHandler.favorites();
            List<Product> catMatches= new ArrayList<>();
            for (Product p:matches) {
                for (Product cp: PreviousList) {
                    if (p.getProductId()==cp.getProductId())
                        catMatches.add(p);
                }
            }
            updateProductList(catMatches);
            FavouriteButtonSelected = true;
            favouriteButton.setStyle("-fx-background-color: #4cc773; -fx-text-fill: white; -fx-border-color: #4cc773; -fx-border-width: 3px; -fx-border-radius: 3;");
    }
        else{
            updateProductList(PreviousList);
            FavouriteButtonSelected = false;
            favouriteButton.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-border-color: #4cc773; -fx-border-width: 3px; -fx-border-radius: 3;");
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
        updateCartList();
    }


    private void updateCartList() {
        cartFlowPane.getChildren().clear();

        for (ShoppingItem shoppingItem : model.shoppingCart.getItems()) {
            cartFlowPane.getChildren().add(new cartItemPanel(shoppingItem));
        }


        cartSumLabel.setText("Summa: " + df2.format( model.shoppingCart.getTotal()) + " kr");
        }


    private final Model model = Model.getInstance();

    @FXML
    private void updateProductList(List<Product> products) {
        productsFlowPane.getChildren().clear();
        List<Integer> id = new ArrayList<>();
        for (Product pro:products) {
            id.add(pro.getProductId());
        }

        for (Integer productId : id) {
            productsFlowPane.getChildren().add(hashproducts.get(productId));
        }
    }

    @FXML
    private void allProducts(ActionEvent event) {
        updateProductList(model.getProducts());
        PreviousList.clear();
        PreviousList.addAll(model.getProducts());
        allButton.getStyleClass().add("category-item-selected");

        meatAndFishButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");

    }

    @FXML
    private void meatAndFish() {
        updateProductList(model.categorySearch("meatAndFish"));
        PreviousList.clear();
        PreviousList.addAll(model.categorySearch("meatAndFish"));
        meatAndFishButton.getStyleClass().add("category-item-selected");

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
    }

    @FXML
    private void fruitAndVegetable() {
        updateProductList(model.categorySearch("fruitAndVegetables"));
        PreviousList.clear();
        PreviousList.addAll(model.categorySearch("fruitAndVegetables"));
        fruitAndVegetablesButton.getStyleClass().add("category-item-selected");

        allButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
    }

    @FXML
    private void pastaPotatoAndRice() {
        updateProductList(model.categorySearch("pastaPotatoAndRice"));
        PreviousList.clear();
        PreviousList.addAll(model.categorySearch("pastaPotatoAndRice"));
        pastaPotatoRiceButton.getStyleClass().add("category-item-selected");

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
    }

    @FXML
    private void dairy() {
        updateProductList(model.categorySearch("dairy"));
        PreviousList.clear();
        PreviousList.addAll(model.categorySearch("dairy"));
        dairyButton.getStyleClass().add("category-item-selected");

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
    }

    @FXML
    private void bread() {
        updateProductList(model.categorySearch("bread"));
        PreviousList.clear();
        PreviousList.addAll(model.categorySearch("bread"));
        breadButton.getStyleClass().add("category-item-selected");

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
    }

    @FXML
    private void drinks() {
        updateProductList(model.categorySearch("drinks"));
        PreviousList.clear();
        PreviousList.addAll(model.categorySearch("drinks"));
        drinksButton.getStyleClass().add("category-item-selected");

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
    }

    @FXML
    private void ingredients() {
        updateProductList(model.categorySearch("ingredients"));
        PreviousList.clear();
        PreviousList.addAll(model.categorySearch("ingredients"));
        ingredientsButton.getStyleClass().add("category-item-selected");

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
    }

    @FXML
    private void sweets() {
        updateProductList(model.categorySearch("sweets"));
        PreviousList.clear();
        PreviousList.addAll(model.categorySearch("sweets"));
        sweetsButton.getStyleClass().add("category-item-selected");

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
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
    private void loadHelp(ActionEvent event) throws IOException {
        Parent helpParent = FXMLLoader.load(getClass().getResource("help.fxml"));
        Scene checkoutScene = new Scene(helpParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(checkoutScene);
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
    private void clearCart(ActionEvent event) throws IOException {
        model.shoppingCart.clear();
        cartSumLabel.setText("Summa: " + df2.format( model.shoppingCart.getTotal()) + " kr");

        updateCartList();
    }

    @FXML
    private void reloadStore(ActionEvent event) throws IOException {
        updateProductList(model.getProducts());
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        cartNumberOfItems.setText(Integer.toString(model.shoppingCart.getItems().size()));
        cartSumLabel.setText("Summa: " + df2.format( model.shoppingCart.getTotal()) + " kr");
        updateCartList();

    }


}
