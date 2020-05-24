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

import java.io.IOException;

public class productPanel extends AnchorPane {

    @FXML
    Label productNameLabel;
    @FXML
    Label productPriceLabel;
    @FXML
    Label productAmountLabel;
    @FXML
    ImageView productImage;
    @FXML
    ImageView productEcoImage;
    @FXML
    Button favoButton;
    @FXML
    Button addButton;
    @FXML
    Button removeButton;

    @FXML
    private void handleAddAction(ActionEvent event) {
        numberOfItems = numberOfItems + 1;
        productAmountLabel.setText(String.valueOf(numberOfItems));

    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        if (numberOfItems > 0) {
            numberOfItems = numberOfItems - 1;
        }
        productAmountLabel.setText(String.valueOf(numberOfItems));
    }

    @FXML
    private void handleFavouriteAction(ActionEvent event) {
        if (!favouriteSelected) {
            Image favoSelected = new Image("file:iMat_Projektarbete/resources/icons/favorite_filled.png");
            ImageView favobuttonSelected = new ImageView(favoSelected);
            favobuttonSelected.setFitHeight(50);
            favobuttonSelected.setFitWidth(50);
            favoButton.setGraphic(favobuttonSelected);
            favouriteSelected=true;
            model.iMatDataHandler.addFavorite(this.product);
        } else {
            Image favo = new Image("file:iMat_Projektarbete/resources/icons/baseline_favorite_border_black_48dp.png");
            ImageView favobutton = new ImageView(favo);
            favobutton.setFitHeight(50);
            favobutton.setFitWidth(50);
            favoButton.setGraphic(favobutton);
            favouriteSelected=false;
            model.iMatDataHandler.removeFavorite(this.product);
        }
    }

    private Model model = Model.getInstance();

    private Product product;

    private int numberOfItems = 0;
    private boolean favouriteSelected = false;

    public productPanel(Product product) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        productNameLabel.setText(product.getName());
        productPriceLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        productAmountLabel.setText(String.valueOf(numberOfItems));
        productImage.setImage(model.getImage(product, 205, 160));
        if (!product.isEcological()) {
            productEcoImage.setImage(null);
        }
        Image plus = new Image("file:iMat_Projektarbete/resources/icons/baseline_add_white_48dp.png");
        ImageView plusimage = new ImageView(plus);
        plusimage.setFitHeight(50);
        plusimage.setFitWidth(45);
        addButton.setGraphic(plusimage);
        Image remove = new Image("file:iMat_Projektarbete/resources/icons/baseline_remove_white_48dp.png");
        ImageView removeimage = new ImageView(remove);
        removeimage.setFitHeight(50);
        removeimage.setFitWidth(45);
        removeButton.setGraphic(removeimage);

        if (favouriteSelected){
            Image favoSelected = new Image("file:iMat_Projektarbete/resources/icons/favorite_filled.png");
            ImageView favobuttonSelected = new ImageView(favoSelected);
            favobuttonSelected.setFitHeight(50);
            favobuttonSelected.setFitWidth(50);
            favoButton.setGraphic(favobuttonSelected);
            favouriteSelected=true;
            model.iMatDataHandler.addFavorite(this.product);
        }else{
            Image favo = new Image("file:iMat_Projektarbete/resources/icons/baseline_favorite_border_black_48dp.png");
            ImageView favobutton = new ImageView(favo);
            favobutton.setFitHeight(50);
            favobutton.setFitWidth(50);
            favoButton.setGraphic(favobutton);
        }
    }


}
