package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

    private Model model = Model.getInstance();

    private Product product;

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
        productAmountLabel.setText("0");
        productImage.setImage(model.getImage(product, 205, 160));
        if (!product.isEcological()) {
            productEcoImage.setImage(null);
        }
    }
}
