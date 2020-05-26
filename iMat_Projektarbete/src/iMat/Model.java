package iMat;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;



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

}
