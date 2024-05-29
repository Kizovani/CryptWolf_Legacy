module com.cryptwolf {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.cryptwolf to javafx.fxml;
    exports com.cryptwolf;
}
