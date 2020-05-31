package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static iMat.iMatController.df2;

public class checkoutController implements Initializable, ShoppingCartListener {
    private final Model model = Model.getInstance();
    @FXML
    Text kundvagnstext;
    @FXML
    TextField searchBar;
    @FXML
    Button searchButton;
    @FXML
    Text errorPhone;
    @FXML
    Text errorFirstName;
    @FXML
    Text errorLastName;
    @FXML

    Text errorEmail;
    @FXML
    Text errorPostal;
    @FXML
    Text errorPostalCode;
    @FXML
    Text errorAddress;

    @FXML
    Text errorCardName;
    @FXML
    Text errorCardNumber;
    @FXML
    Text errorVerificationCode;

    @FXML
    AnchorPane checkoutPart1;
    @FXML
    AnchorPane checkoutPart2;
    @FXML
    AnchorPane checkoutPart3;
    @FXML
    AnchorPane purchaseComplete;
    @FXML
    AnchorPane header;

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
    ComboBox<String> validMonth;
    @FXML
    ComboBox<String> validYear;
    @FXML
    TextField cvvCode;


    //Save card info checkbox
    @FXML
    CheckBox saveCard;

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
    private void searchEnd(ActionEvent event) throws IOException {
        model.shoppingCart.clear();
        model.searchText=searchBar.getText();
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();

    }

    @FXML
    private void loadStoreFromCheckout(ActionEvent event) throws IOException {
        updateCustomerInformation();
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();
    }

    @FXML
    private void loadStoreFromCheckoutEnd(ActionEvent event) throws IOException {
        updateCustomerInformation();
        model.shoppingCart.clear();
        model.resetShoppingItemAmounts();

        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();
    }

    @FXML
    private void loadHelpCheckout(ActionEvent event) throws IOException {
        Parent helpParent = FXMLLoader.load(getClass().getResource("help.fxml"));
        Scene checkoutScene = new Scene(helpParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(checkoutScene);
        window.show();
    }

    @FXML
    private void loadHelpCheckoutEnd(ActionEvent event) throws IOException {
        model.shoppingCart.clear();
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
    private void loadOrderHistoryFromEnd(ActionEvent event) throws IOException {
        model.shoppingCart.clear();
        Parent helpParent = FXMLLoader.load(getClass().getResource("orderHistory.fxml"));
        Scene checkoutScene = new Scene(helpParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(checkoutScene);
        window.show();
    }

    @FXML
    private void loadCheckout1() {
        updateCartList();
        updateCost();
        checkoutPart1.toFront();
        header.toFront();
        kundvagnstext.toBack();
    }

    @FXML
    private void loadCheckout2() {
        if(model.shoppingCart.getItems().size()==0){
            kundvagnstext.toFront();
            kundvagnstext.setText("Varukorgen är tom, lägg till varor för att gå vidare");
        } else {
            displayCustomerInformation();
//            updateCard();
            checkoutPart2.toFront();
            header.toFront();
        }
    }

    @FXML
    private void loadCheckout3() {
        if (checkifLegalCheckout2()) {
            displayCard();
            checkoutPart3.toFront();
            header.toFront();
        }
    }

    @FXML
    private void completePurchase() {
        if (checkifLegalCheckout3()) {
            updateCardIfSelected();
            showPurchase();
            model.placeOrder(false);
            purchaseComplete.toFront();
        }
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
        checkoutSumLabel.setText("Varor: " + df2.format(model.shoppingCart.getTotal()) + ":-");
        checkoutTotalLabel.setText("Total summa: " + (df2.format(49 + model.shoppingCart.getTotal())) + ":-");

        checkoutSumLabel1.setText("Varor: " + df2.format(model.shoppingCart.getTotal()) + ":-");
        checkoutTotalLabel1.setText("Total summa: " + (df2.format(49 + model.shoppingCart.getTotal())) + ":-");

        checkoutSumLabel2.setText("Varor: " + df2.format(model.shoppingCart.getTotal()) + ":-");
        checkoutTotalLabel2.setText("Total summa: " + (df2.format(49 + model.shoppingCart.getTotal())) + ":-");

        checkoutSumLabel3.setText("Varor: " + df2.format(model.shoppingCart.getTotal()) + ":-");
        checkoutTotalLabel3.setText("Total summa: " + (df2.format(49 + model.shoppingCart.getTotal())) + ":-");
    }

    @FXML
    private void updateCustomerInformation() {
        model.updateCustomerInformation(firstName.getText(), lastName.getText(), address.getText(), postCode.getText(), postArea.getText(), email.getText(), mobilePhoneNumber.getText());
    }

    @FXML
    private void threenumberlock() {
        if (cvvCode.getText().length() > 2) {
            cvvCode.deleteText(2, 3);
        }
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
    private void updateCardIfSelected() {
        if (saveCard.isSelected()) {
            model.updateCard(cardHolder.getText(), cardNumber.getText(), Integer.parseInt(validMonth.getValue()), Integer.parseInt(validYear.getValue()), Integer.parseInt(cvvCode.getText()));
        } else {
            model.updateCard("", "", 01, 2021, 0);
        }
    }

    @FXML
    private void updateCard() {
        if (cardHolder.getText() != null || cardNumber.getText() != null || validMonth.getValue() != "01" || validYear.getValue() != "2021" || cvvCode.getText() != "") {
            System.out.println(cardHolder.getText());
            System.out.println(cardNumber.getText());
            System.out.println(Integer.parseInt(validMonth.getValue()));
            model.updateCard(cardHolder.getText(), cardNumber.getText(), Integer.parseInt(validMonth.getValue()), Integer.parseInt(validYear.getValue()), Integer.parseInt(cvvCode.getText()));
        }
    }

    @FXML
    private void displayCard() {
        cardHolder.setText(model.creditCard.getHoldersName());
        cardNumber.setText(model.creditCard.getCardNumber());
        validYear.setValue(Integer.toString(model.creditCard.getValidYear()));

        if (model.creditCard.getValidMonth() == 1) {
            validMonth.setValue("01");
        } else {
            validMonth.setValue(Integer.toString(model.creditCard.getValidMonth()));
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

    private Boolean checkIfIllegalNumber(String str) {

        if (str == null || str.isEmpty()) return true;
        int num = 0;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                num++;
            }
        }
        return (num > 0);
    }

    private boolean checkIfIllegalLetter(String str) {

        if (str == null || str.isEmpty()) return true;
        int num = 0;
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                num++;
            }
        }
        return (num > 0);
    }

    private boolean checkIfIllegalPhone(String str) {

        int num = 0;
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                num++;
            }
        }
        return (num > 0);
    }

    private boolean checkIfIllegalEmail(String str) {
        if (str == null || str.isEmpty()) return true;
        int num = 0;
        for (char c : str.toCharArray()) {
            if ((Character.isLetter(c) && num == 0) || (Character.isLetter(c) && num == 0)) {
                num++;
            }
            if ((Character.toString(c).equals("@")) && num == 1) {
                num++;
            }
            if ((Character.isLetter(c) && num == 2) || (Character.isLetter(c) && num == 2)) {
                num++;
            }
            if ((Character.toString(c).equals(".")) && num == 3) {
                num++;
            }

            if ((Character.isLetter(c) && num == 4) || (Character.isLetter(c) && num == 4)) {
                num++;
            }
        }
        return (num < 5);
    }

    private boolean checkIfIllegalAdress(String str) {

        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean checkifLegalCheckout2() {
        int num = 0;
        if (checkIfIllegalNumber(firstName.getText())) {
            firstName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            num++;
            errorFirstName.toFront();
        } else {
            firstName.setStyle("-fx-border-width: 0px ;");
            errorFirstName.toBack();
        }

        if (checkIfIllegalNumber(lastName.getText())) {
            lastName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            num++;
            errorLastName.toFront();
        } else {
            lastName.setStyle("-fx-border-width: 0px ;");
            errorLastName.toBack();
        }

        if (checkIfIllegalNumber(postArea.getText())) {
            postArea.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            num++;
            errorPostal.toFront();
        } else {
            postArea.setStyle("-fx-border-width: 0px ;");
            errorPostal.toBack();
        }

        if (checkIfIllegalAdress(address.getText())) {
            address.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            num++;
            errorAddress.toFront();
        } else {
            address.setStyle("-fx-border-width: 0px ;");
            errorAddress.toBack();
        }

        if (checkIfIllegalLetter(postCode.getText()) || postCode.getText().length() != 5) {
            postCode.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            num++;
            errorPostalCode.toFront();

        } else {
            postCode.setStyle("-fx-border-width: 0px ;");
            errorPostalCode.toBack();

        }
        if (checkIfIllegalPhone(mobilePhoneNumber.getText())) {
            mobilePhoneNumber.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            num++;
            errorPhone.toFront();
        } else {
            mobilePhoneNumber.setStyle("-fx-border-width: 0px ;");
            errorPhone.toBack();
        }

        if (checkIfIllegalEmail(email.getText())) {
            email.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            num++;
            errorEmail.toFront();
        } else {
            email.setStyle("-fx-border-width: 0px ;");
            errorEmail.toBack();
        }

        return num == 0;
    }

    private boolean checkifLegalCheckout3() {
        int num = 0;
        if (checkIfIllegalNumber(cardHolder.getText())) {
            cardHolder.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            errorCardName.toFront();
            num++;
        } else {
            cardHolder.setStyle("-fx-border-width: 0px ;");
            errorCardName.toBack();
        }

        if (checkIfIllegalLetter(cardNumber.getText()) || cardNumber.getText().length() != 16) {
            cardNumber.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            errorCardNumber.toFront();
            num++;
        } else {
            cardNumber.setStyle("-fx-border-width: 0px ;");
            errorCardName.toBack();
        }


        if (checkIfIllegalLetter(cvvCode.getText()) && !(cvvCode.getText().length() == 3)) {
            cvvCode.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            errorVerificationCode.toFront();
            num++;
        } else {
            cvvCode.setStyle("-fx-border-width: 0px ;");
            errorCardName.toBack();
        }

        return num == 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model.shoppingCart.addShoppingCartListener(this);
        updateCost();
        updateCartList();
        validMonth.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        validYear.getItems().addAll("2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039");

    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateCost();
        updateCartList();
    }
}
