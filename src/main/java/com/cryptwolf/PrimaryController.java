package com.cryptwolf;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PrimaryController {
    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    public void initialize() {
        Image leftIcon = new Image(App.class.getResourceAsStream("/com/cryptwolf/icons/file.png"));
        leftButton.setGraphic(new ImageView(leftIcon));

        Image rightIcon = new Image(App.class.getResourceAsStream("/com/cryptwolf/icons/file.png"));
        rightButton.setGraphic(new ImageView(rightIcon));

        leftButton.setOnAction(event -> {
            try {
                Parent landPage = FXMLLoader.load(App.class.getResource("/com/cryptwolf/Land.fxml"));
                Scene landScene = new Scene(landPage);
                Stage appStage = (Stage) leftButton.getScene().getWindow();
                appStage.setScene(landScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}