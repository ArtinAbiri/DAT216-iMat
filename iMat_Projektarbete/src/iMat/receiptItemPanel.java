package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class receiptItemPanel extends AnchorPane {
    //Shared instance of model
    Model model = Model.getInstance();


    @FXML
    Label cartItemName;
    @FXML
    Label cartItemPrice;
    @FXML
    Label cartItemAmount;
    @FXML
    ImageView cartItemImage;
    @FXML
    ImageView cartItemEco;

    ShoppingItem shoppingItem;
    private int numberOfItems;

    public receiptItemPanel(ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receiptItemPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.shoppingItem = shoppingItem;
        this.numberOfItems = (int) shoppingItem.getAmount();

        cartItemName.setText(shoppingItem.getProduct().getName());
        cartItemPrice.setText(String.format("%.2f", shoppingItem.getProduct().getPrice()) + " " + shoppingItem.getProduct().getUnit());
        cartItemAmount.setText(String.valueOf(numberOfItems) + " st");
        cartItemImage.setImage(model.getImage(shoppingItem.getProduct(), 120, 110));
        if (!shoppingItem.getProduct().isEcological()) {
            cartItemEco.setImage(null);
        }
    }
}
