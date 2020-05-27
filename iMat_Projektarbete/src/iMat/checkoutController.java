package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.concurrent.Flow;

public class checkoutController {
    private final Model model = Model.getInstance();

    @FXML
    AnchorPane checkoutPart1;
    @FXML
    AnchorPane checkoutPart2;
    @FXML
    AnchorPane checkoutPart3;
    @FXML
    AnchorPane purchaseComplete;

    //checkoutPart1
    @FXML
    FlowPane checkoutCartFlowPane;

    //Receipt
    @FXML
    FlowPane checkoutReceiptFlowPane;

    //Cost labels
    @FXML
    Text checkoutSumLabel;
    @FXML
    Text checkoutTotalLabel;
    @FXML
    Text checkoutSumLabel1;
    @FXML
    Text checkoutTotalLabel1;
    @FXML
    Text checkoutSumLabel2;
    @FXML
    Text checkoutTotalLabel2;
    @FXML
    Text checkoutSumLabel3;
    @FXML
    Text checkoutTotalLabel3;

    //Personal info textfields
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField address;
    @FXML
    TextField postArea;
    @FXML
    TextField postCode;
    @FXML
    TextField email;
    @FXML
    TextField mobilePhoneNumber;

    //Card info textfields
    @FXML
    TextField cardHolder;
    @FXML
    TextField cardNumber;
    @FXML
    TextField validMonth;
    @FXML
    TextField validYear;
    @FXML
    TextField cvvCode;

    //Save card info checkbox
    @FXML
    CheckBox saveCard;

    @FXML
    private void loadStoreFromCheckout(ActionEvent event) throws IOException {
        updateCard();
        updateCustomerInformation();
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();
    }

    @FXML
    private void loadCheckout1() {
        checkoutPart1.toFront();
        updateCartList();
        updateCost();
    }

    @FXML
    private void loadCheckout2() {
        displayCustomerInformation();
        updateCard();
        checkoutPart2.toFront();
    }

    @FXML
    private void loadCheckout3() {
        displayCard();
        checkoutPart3.toFront();
    }

    @FXML
    private void completePurchase() {
        updateCard();
        showPurchase();
        model.placeOrder(true);
        purchaseComplete.toFront();
    }

    @FXML
    private void updateCartList() {
        checkoutCartFlowPane.getChildren().clear();

        for (ShoppingItem shoppingItem : model.shoppingCart.getItems()) {
            checkoutCartFlowPane.getChildren().add(new cartItemPanel(shoppingItem));
        }
    }

    @FXML
    private void updateCost() {
        checkoutSumLabel.setText("Varor: " + model.shoppingCart.getTotal());
        checkoutTotalLabel.setText("Total summa: " + (model.shoppingCart.getTotal() + 49));

        checkoutSumLabel1.setText("Varor: " + model.shoppingCart.getTotal());
        checkoutTotalLabel1.setText("Total summa: " + (model.shoppingCart.getTotal() + 49));

        checkoutSumLabel2.setText("Varor: " + model.shoppingCart.getTotal());
        checkoutTotalLabel2.setText("Total summa: " + (model.shoppingCart.getTotal() + 49));

        checkoutSumLabel3.setText("Varor: " + model.shoppingCart.getTotal());
        checkoutTotalLabel3.setText("Total summa: " + (model.shoppingCart.getTotal() + 49));
    }

    @FXML
    private void updateCustomerInformation() {
        model.updateCustomerInformation(firstName.getText(), lastName.getText(), address.getText(), postCode.getText(), postArea.getText(), email.getText(),  mobilePhoneNumber.getText());
    }
    
    @FXML
    private void displayCustomerInformation() {
        firstName.setText(model.customer.getFirstName());
        lastName.setText(model.customer.getLastName());
        address.setText(model.customer.getAddress());
        postArea.setText(model.customer.getPostAddress());
        postCode.setText(model.customer.getPostCode());
        email.setText(model.customer.getEmail());
        mobilePhoneNumber.setText(model.customer.getMobilePhoneNumber());
    }

    @FXML
    private void updateCard() {
        if(saveCard.isSelected()) {
            model.updateCard(cardHolder.getText(), cardNumber.getText(), Integer.parseInt(validMonth.getText()), Integer.parseInt(validYear.getText()), Integer.parseInt(cvvCode.getText()));
        } else {
            model.updateCard(null, null, 0, 0, 0);
        }
    }

    @FXML
    private void displayCard() {
        cardHolder.setText(model.creditCard.getHoldersName());
        cardNumber.setText(model.creditCard.getCardNumber());
        if (model.creditCard.getValidMonth() == 0) {
            validMonth.setText("");
        } else {
            validMonth.setText(Integer.toString(model.creditCard.getValidMonth()));

        }

        if (model.creditCard.getValidYear() == 0) {
            validYear.setText("");
        } else {
            validYear.setText(Integer.toString(model.creditCard.getValidYear()));

        }

        if (model.creditCard.getVerificationCode() == 0) {
            cvvCode.setText("");
        } else {
            cvvCode.setText(Integer.toString(model.creditCard.getVerificationCode()));
        }
    }

    private void showPurchase() {
        checkoutReceiptFlowPane.getChildren().clear();

        for (ShoppingItem shoppingItem : model.shoppingCart.getItems()) {
            checkoutReceiptFlowPane.getChildren().add(new receiptItemPanel(shoppingItem));
        }
    }
}
