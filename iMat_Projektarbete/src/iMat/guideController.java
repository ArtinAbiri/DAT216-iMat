package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class guideController {
    @FXML
    AnchorPane start;
    @FXML
    AnchorPane step1;
    @FXML
    AnchorPane step2;
    @FXML
    AnchorPane step3;
    @FXML
    AnchorPane step4;
    @FXML
    AnchorPane step5;
    @FXML
    AnchorPane step6;
    @FXML
    AnchorPane step7;
    @FXML
    AnchorPane step8;
    @FXML
    AnchorPane step9;
    @FXML
    AnchorPane step10;
    @FXML
    AnchorPane end;

    @FXML
    private void loadGuideStart() {
        start.toFront();
    }

    @FXML
    private void loadStep1() {
        step1.toFront();
    }

    @FXML
    private void loadStep2() {
        step2.toFront();
    }

    @FXML
    private void loadStep3() {
        step3.toFront();
    }

    @FXML
    private void loadStep4() {
        step4.toFront();
    }

    @FXML
    private void loadStep5() {
        step5.toFront();
    }

    @FXML
    private void loadStep6() {
        step6.toFront();
    }

    @FXML
    private void loadStep7() {
        step7.toFront();
    }

    @FXML
    private void loadStep8() {
        step8.toFront();
    }

    @FXML
    private void loadStep9() {
        step9.toFront();
    }

    @FXML
    private void loadStep10() {
        step10.toFront();
    }
    @FXML
    private void loadEnd() {
        end.toFront();
    }

    @FXML
    private void loadStart(javafx.event.ActionEvent event) throws IOException {
        Parent startParent = FXMLLoader.load(getClass().getResource("startScreen.fxml"));
        Scene startScene = new Scene(startParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(startScene);
        window.show();
    }

    @FXML
    private void loadStore(javafx.event.ActionEvent event) throws IOException {
        Parent storeParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene storeScene = new Scene(storeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(storeScene);
        window.show();
    }

}
