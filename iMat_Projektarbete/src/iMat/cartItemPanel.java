package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class cartItemPanel extends AnchorPane {
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
    @FXML
    Button cartItemRemove;

    ShoppingItem shoppingItem;
    private int numberOfItems;

    public cartItemPanel(ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartItemPanel.fxml"));
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
        cartItemAmount.setText(String.valueOf(numberOfItems));
        cartItemImage.setImage(model.getImage(shoppingItem.getProduct(), 120, 110));
        if (!shoppingItem.getProduct().isEcological()) {
            cartItemEco.setImage(null);
        }
        Image plus = new Image("file:iMat_Projektarbete/resources/icons/baseline_add_white_48dp.png");
        ImageView plusimage = new ImageView(plus);
        plusimage.setFitHeight(50);
        plusimage.setFitWidth(45);
        cartItemAdd.setGraphic(plusimage);
        Image remove = new Image("file:iMat_Projektarbete/resources/icons/baseline_remove_white_48dp.png");
        ImageView removeimage = new ImageView(remove);
        removeimage.setFitHeight(50);
        removeimage.setFitWidth(45);
        cartItemRemove.setGraphic(removeimage);
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        model.addToCart(shoppingItem);
        cartItemAmount.setText(Integer.toString( (int) shoppingItem.getAmount()));
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        model.removeFromCart(shoppingItem);
        cartItemAmount.setText(Integer.toString( (int) shoppingItem.getAmount()));
    }
}
