package com.cryptwolf;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    }

    
}