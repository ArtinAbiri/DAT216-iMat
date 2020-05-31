package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class previousOrder extends TitledPane {
    private Order order;
    @FXML
    FlowPane previousOrderFlowPane;


    @FXML
    ScrollPane previousOrderScrollPane;
    Model model = Model.getInstance();

    public previousOrder(Order order) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("previousOrder.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.order = order;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(order.getDate());

        this.setText("Datum: " + dateString + ", Ordernummer: " + order.getOrderNumber());
//        previousPurchaseImage.setImage(new Image("file:iMat_Projektarbete/resources/icons/baseline_add_white_48dp.png"));

        previousOrderFlowPane.getChildren().clear();

        for (ShoppingItem item : order.getItems()) {
            previousOrderFlowPane.getChildren().add(new orderHistoryItemPanel(item));
        }
    }

    @FXML
    private void handleAddALLAction() {
        for(ShoppingItem item : order.getItems()){
            ShoppingItem addItem = iMatController.hashproducts.get(item.getProduct().getProductId()).shoppingItem;
            for (int i = 0; i < item.getAmount(); i++) {
                model.addToCart(addItem);
            }
            System.out.println(item.getProduct().getName());
            //model.shoppingCart.fireShoppingCartChanged(item, true);
            iMatController.hashproducts.get(item.getProduct().getProductId()).update(item);
        }
    }
}
