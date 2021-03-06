package iMat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static se.chalmers.cse.dat216.project.ProductCategory.*;


public class Model {
     String searchText;
     boolean openCart;

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
    public List<Product> subCategorySearch(String category) {
        List<Product> returnList = new ArrayList<>();
        switch(category) {
            case "meat":
                returnList.addAll(iMatDataHandler.getProducts(MEAT));
                return returnList;
            case "fish":
                returnList.addAll(iMatDataHandler.getProducts(FISH));
                return returnList;

            case "potato_rice":
                returnList.addAll(iMatDataHandler.getProducts(POTATO_RICE));
                return returnList;
            case "pasta":
                returnList.addAll(iMatDataHandler.getProducts(PASTA));
                return returnList;
            case "hotDrink":
                returnList.addAll(iMatDataHandler.getProducts(HOT_DRINKS));
                return returnList;
            case "coldDrink":
                returnList.addAll(iMatDataHandler.getProducts(COLD_DRINKS));
                return returnList;
            case "fruit":
                returnList.addAll(iMatDataHandler.getProducts(FRUIT));
                returnList.addAll(iMatDataHandler.getProducts(CITRUS_FRUIT));
                returnList.addAll(iMatDataHandler.getProducts(EXOTIC_FRUIT));
                returnList.addAll(iMatDataHandler.getProducts(MELONS));
                return returnList;
            case "greens":
                returnList.addAll(iMatDataHandler.getProducts(POD));
                returnList.addAll(iMatDataHandler.getProducts(CABBAGE));
                returnList.addAll(iMatDataHandler.getProducts(VEGETABLE_FRUIT));

                return returnList;
            case "herbs":
                returnList.addAll(iMatDataHandler.getProducts(HERB));
                return returnList;
            case "nut":
                returnList.addAll(iMatDataHandler.getProducts(NUTS_AND_SEEDS));
                return returnList;
            case "root":
                returnList.addAll(iMatDataHandler.getProducts(ROOT_VEGETABLE));
                return returnList;
            case "berry":
                returnList.addAll(iMatDataHandler.getProducts(BERRY));
                return returnList;

        }            return null;
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

    public boolean isFavorite(Product product) {
        return iMatDataHandler.isFavorite(product);
    }

    public void updateShoppingItemAmount(ShoppingItem shoppingItem) {
        iMatController.hashproducts.get(shoppingItem.getProduct().getProductId()).update(shoppingItem);
    }

    public void resetShoppingItemAmounts() {
        List<Product> products = getProducts();
        List<Integer> id = new ArrayList<>();
        for (Product pro: products) {
            id.add(pro.getProductId());
        }

        for (Integer productId : id) {
            iMatController.hashproducts.get(productId).shoppingItem.setAmount(0);
        }
    }
}
