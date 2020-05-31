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
    Button favouritesButton;
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
    @FXML
    Button meatButton;
    @FXML
    Button fishButton;
    @FXML
    Button potatoRiceButton;
    @FXML
    Button pastaButton;
    @FXML
    Button hotDrinkButton;
    @FXML
    Button coldDrinkButton;
    @FXML
    Button fruitButton;
    @FXML
    Button greensButton;
    @FXML
    Button berryButton;
    @FXML
    Button rootButton;
    @FXML
    Button herbButton;
    @FXML
    Button nutButton;



    //
    @FXML
    Label cartNumberOfItems;

    private Boolean FavouriteButtonSelected = false;

    public void initialize(URL url, ResourceBundle rb) {

        updateProductList(model.getProducts());
        model.shoppingCart.addShoppingCartListener(this);
        System.out.println(hashproducts.toString());
    }

    public void hashstart(){
        List<Product> allProducts = model.getProducts();
        for (Product product : allProducts) {
            hashproducts.put(product.getProductId(),new productPanel(product));
        }
    }

    public void clearShoppingCart() {
        model.shoppingCart.clear();
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(searchBar.getText());
        updateProductList(matches);

        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();
        favouritesButton.getStyleClass().remove("category-item-selected");
        allButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
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
            model.updateShoppingItemAmount(hashproducts.get(productId).shoppingItem);
            productsFlowPane.getChildren().add(hashproducts.get(productId));
        }
    }

    @FXML
    private void favourites(ActionEvent event) {
        updateProductList(model.iMatDataHandler.favorites());
        searchBar.setText(null);
        favouritesButton.getStyleClass().remove("category-item-selected");

        favouritesButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();
        allButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }

    @FXML
    private void allProducts(ActionEvent event) {
        updateProductList(model.getProducts());
        allButton.getStyleClass().remove("category-item-selected");
        searchBar.setText(null);

        allButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }
    ;
    @FXML
    private void subMeat(){
        updateProductList(model.subCategorySearch("meat"));
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        searchBar.setText(null);

        meatButton.getStyleClass().add("subsubcategory-button-on");

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }
    @FXML
    private void subFish(){
        updateProductList(model.subCategorySearch("fish"));
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        searchBar.setText(null);

        fishButton.getStyleClass().add("subsubcategory-button-on");

        meatButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }
    @FXML
    private void subHotDrink(){
        updateProductList(model.subCategorySearch("hotDrink"));
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");

        hotDrinkButton.getStyleClass().add("subsubcategory-button-on");
        searchBar.setText(null);

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }
    @FXML
    private void subColdDrink(){
        updateProductList(model.subCategorySearch("coldDrink"));
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        searchBar.setText(null);

        coldDrinkButton.getStyleClass().add("subsubcategory-button-on");

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    } @FXML
    private void subFruit(){
        updateProductList(model.subCategorySearch("fruit"));
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        searchBar.setText(null);

        fruitButton.getStyleClass().add("subsubcategory-button-on");

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    } @FXML
    private void subGreens(){
        updateProductList(model.subCategorySearch("greens"));
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        searchBar.setText(null);

        greensButton.getStyleClass().add("subsubcategory-button-on");

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }
    @FXML
    private void subPotatoRice(){
        updateProductList(model.subCategorySearch("potato_rice"));
        pastaButton.getStyleClass().remove("subsubcategory-button-on");

        pastaButton.getStyleClass().add("subsubcategory-button-on");
        searchBar.setText(null);

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    } @FXML
    private void subPasta(){
        updateProductList(model.subCategorySearch("pasta"));
        pastaButton.getStyleClass().remove("subsubcategory-button-on");

        pastaButton.getStyleClass().add("subsubcategory-button-on");
        searchBar.setText(null);

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    } @FXML
    private void subBerry(){
        updateProductList(model.subCategorySearch("berry"));
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        searchBar.setText(null);

        berryButton.getStyleClass().add("subsubcategory-button-on");

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    } @FXML
    private void subRoot(){
        updateProductList(model.subCategorySearch("root"));
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        searchBar.setText(null);

        rootButton.getStyleClass().add("subsubcategory-button-on");

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    } @FXML
    private void subHerb(){
        updateProductList(model.subCategorySearch("herbs"));
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        searchBar.setText(null);

        herbButton.getStyleClass().add("subsubcategory-button-on");

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    } @FXML
    private void subNut(){
        updateProductList(model.subCategorySearch("nut"));
        nutButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().add("subsubcategory-button-on");
        searchBar.setText(null);

        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
    }
    @FXML
    private void meatAndFish() {
        updateProductList(model.categorySearch("meatAndFish"));
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        searchBar.setText(null);

        meatAndFishButton.getStyleClass().add("category-item-selected");
        meatButton.toFront();
        fishButton.toFront();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }

    @FXML
    private void fruitAndVegetable() {
        updateProductList(model.categorySearch("fruitAndVegetables"));
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        searchBar.setText(null);

        fruitAndVegetablesButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toFront();
        greensButton.toFront();
        berryButton.toFront();
        rootButton.toFront();
        herbButton.toFront();
        nutButton.toFront();

        allButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }

    @FXML
    private void pastaPotatoAndRice() {
        updateProductList(model.categorySearch("pastaPotatoAndRice"));
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        searchBar.setText(null);

        pastaPotatoRiceButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toFront();
        pastaButton.toFront();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }

    @FXML
    private void dairy() {
        updateProductList(model.categorySearch("dairy"));
        dairyButton.getStyleClass().remove("category-item-selected");
        searchBar.setText(null);

        dairyButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();

        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }

    @FXML
    private void bread() {
        updateProductList(model.categorySearch("bread"));
        breadButton.getStyleClass().remove("category-item-selected");
        searchBar.setText(null);

        breadButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();
        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }

    @FXML
    private void drinks() {
        updateProductList(model.categorySearch("drinks"));
        drinksButton.getStyleClass().remove("category-item-selected");
        searchBar.setText(null);

        drinksButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toFront();
        coldDrinkButton.toFront();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();
        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }

    @FXML
    private void ingredients() {
        updateProductList(model.categorySearch("ingredients"));
        ingredientsButton.getStyleClass().remove("category-item-selected");
        searchBar.setText(null);

        ingredientsButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();
        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
    }

    @FXML
    private void sweets() {
        updateProductList(model.categorySearch("sweets"));
        sweetsButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().add("category-item-selected");
        meatButton.toBack();
        searchBar.setText(null);

        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();
        allButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
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
        meatButton.toBack();
        fishButton.toBack();
        potatoRiceButton.toBack();
        pastaButton.toBack();
        hotDrinkButton.toBack();
        coldDrinkButton.toBack();
        fruitButton.toBack();
        greensButton.toBack();
        berryButton.toBack();
        rootButton.toBack();
        herbButton.toBack();
        nutButton.toBack();
        meatButton.getStyleClass().remove("subsubcategory-button-on");
        fishButton.getStyleClass().remove("subsubcategory-button-on");
        potatoRiceButton.getStyleClass().remove("subsubcategory-button-on");
        pastaButton.getStyleClass().remove("subsubcategory-button-on");
        hotDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        coldDrinkButton.getStyleClass().remove("subsubcategory-button-on");
        fruitButton.getStyleClass().remove("subsubcategory-button-on");
        greensButton.getStyleClass().remove("subsubcategory-button-on");
        berryButton.getStyleClass().remove("subsubcategory-button-on");
        rootButton.getStyleClass().remove("subsubcategory-button-on");
        herbButton.getStyleClass().remove("subsubcategory-button-on");
        nutButton.getStyleClass().remove("subsubcategory-button-on");
        allButton.getStyleClass().remove("category-item-selected");

        allButton.getStyleClass().add("category-item-selected");
        meatAndFishButton.getStyleClass().remove("category-item-selected");
        fruitAndVegetablesButton.getStyleClass().remove("category-item-selected");
        dairyButton.getStyleClass().remove("category-item-selected");
        pastaPotatoRiceButton.getStyleClass().remove("category-item-selected");
        ingredientsButton.getStyleClass().remove("category-item-selected");
        breadButton.getStyleClass().remove("category-item-selected");
        drinksButton.getStyleClass().remove("category-item-selected");
        sweetsButton.getStyleClass().remove("category-item-selected");
        favouritesButton.getStyleClass().remove("category-item-selected");

    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateCartAmount();
        cartSumLabel.setText("Summa: " + df2.format( model.shoppingCart.getTotal()) + " kr");
        updateCartList();
    }

    public void updateCartAmount() {
        cartNumberOfItems.setText(Integer.toString(model.shoppingCart.getItems().size()));
    }
}
