package iMat;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static se.chalmers.cse.dat216.project.ProductCategory.*;


public class Model {
    private static Model instance = null;
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    protected Model() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the single instance of the Model class.
     */
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
            instance.init();
        }
        return instance;
    }

    private void init() {

        iMatDataHandler = IMatDataHandler.getInstance();

    }


    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    public List<Product> findProducts(java.lang.String s) {
        return iMatDataHandler.findProducts(s);
    }

    Customer customer = iMatDataHandler.getCustomer();

    public void updateCustomerInformation(String firstName, String lastName, String address, String postCode, String postArea, String email, String mobilePhoneNumber) {
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setPostCode(postCode);
        customer.setPostAddress(postArea);
        customer.setEmail(email);
        customer.setMobilePhoneNumber(mobilePhoneNumber);
    }

    CreditCard creditCard = iMatDataHandler.getCreditCard();

    public void updateCard(String cardHolder, String cardNumber, int validMonth, int validYear, int cvvCode) {
        creditCard.setHoldersName(cardHolder);
        creditCard.setCardNumber(cardNumber);
        creditCard.setValidMonth(validMonth);
        creditCard.setValidYear(validYear);
        creditCard.setVerificationCode(cvvCode);
    }

    ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

    public void addToCart(ShoppingItem shoppingItem) {
        if (shoppingCart.getItems().contains(shoppingItem)) {
            shoppingItem.setAmount(shoppingItem.getAmount() + 1);
            shoppingCart.fireShoppingCartChanged(shoppingItem, true);
        } else {
            shoppingItem.setAmount(1);
            shoppingCart.fireShoppingCartChanged(shoppingItem, true);
            shoppingCart.addItem(shoppingItem);
        }
    }

        public void removeFromCart(ShoppingItem shoppingItem) {
        if (shoppingItem.getAmount() > 1) {
            shoppingItem.setAmount(shoppingItem.getAmount() - 1);
            shoppingCart.fireShoppingCartChanged(shoppingItem,true);
        } else if (shoppingItem.getAmount() == 1){
            shoppingItem.setAmount(shoppingItem.getAmount() - 1);
            shoppingCart.removeItem(shoppingItem);
        }
    }

    public List<Product> categorySearch(String category) {
        List<Product> returnList = new ArrayList<>();
        switch(category) {
            case "meatAndFish":
                returnList.addAll(iMatDataHandler.getProducts(MEAT));
                returnList.addAll(iMatDataHandler.getProducts(FISH));
                return returnList;
            case "fruitAndVegetables":
                returnList.addAll(iMatDataHandler.getProducts(FRUIT));
                returnList.addAll(iMatDataHandler.getProducts(CITRUS_FRUIT));
                returnList.addAll(iMatDataHandler.getProducts(VEGETABLE_FRUIT));
                returnList.addAll(iMatDataHandler.getProducts(ROOT_VEGETABLE));
                returnList.addAll(iMatDataHandler.getProducts(EXOTIC_FRUIT));
                returnList.addAll(iMatDataHandler.getProducts(CABBAGE));
                returnList.addAll(iMatDataHandler.getProducts(BERRY));
                returnList.addAll(iMatDataHandler.getProducts(MELONS));
                returnList.addAll(iMatDataHandler.getProducts(NUTS_AND_SEEDS));
                returnList.addAll(iMatDataHandler.getProducts(POD));
                returnList.addAll(iMatDataHandler.getProducts(HERB));

                return returnList;
            case "pastaPotatoAndRice":
                returnList.addAll(iMatDataHandler.getProducts(POTATO_RICE));
                returnList.addAll(iMatDataHandler.getProducts(PASTA));
                return returnList;
            case "dairy":
                returnList.addAll(iMatDataHandler.getProducts(DAIRIES));
                return returnList;
            case "bread":
                returnList.addAll(iMatDataHandler.getProducts(BREAD));
                return returnList;
            case "drinks":
                returnList.addAll(iMatDataHandler.getProducts(HOT_DRINKS));
                returnList.addAll(iMatDataHandler.getProducts(COLD_DRINKS));
                return returnList;
            case "ingredients":
                returnList.addAll(iMatDataHandler.getProducts(FLOUR_SUGAR_SALT));
                return returnList;
            case "sweets":
                returnList.addAll(iMatDataHandler.getProducts(SWEET));
                return returnList;
        }
        return null;
    }

    public void placeOrder(boolean clearShoppingCart) {
        iMatDataHandler.placeOrder(clearShoppingCart);
    }

    public List<Order> getOrders() {
        return iMatDataHandler.getOrders();
    }
}
