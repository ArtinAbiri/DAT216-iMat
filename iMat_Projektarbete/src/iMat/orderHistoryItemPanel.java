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

public class orderHistoryItemPanel extends AnchorPane {
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
    @FXML
    Button cartItemAdd;

    ShoppingItem shoppingItem;
    int numberOfItems;

    public orderHistoryItemPanel(ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderHistoryItemPanel.fxml"));
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
        cartItemPrice.setText(String.format("%.2f", shoppingItem.getProduct().getPrice()) + " kr");
        cartItemAmount.setText(String.valueOf(numberOfItems) + " st");
        cartItemImage.setImage(model.getImage(shoppingItem.getProduct(), 120, 110));
        if (!shoppingItem.getProduct().isEcological()) {
            cartItemEco.setImage(null);
        }
        Image plus = new Image("file:iMat_Projektarbete/resources/icons/baseline_add_white_48dp.png");
        ImageView plusimage = new ImageView(plus);
        plusimage.setFitHeight(40);
        plusimage.setFitWidth(40);
        cartItemAdd.setPrefHeight(64);
        cartItemAdd.setPrefWidth(64);
        cartItemAdd.setGraphic(plusimage);
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        model.addToCart(shoppingItem);
        model.shoppingCart.fireShoppingCartChanged(shoppingItem, true);
        iMatController.hashproducts.get(shoppingItem.getProduct().getProductId()).update(shoppingItem);

    }
}
