module com.cryptwolf {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cryptwolf to javafx.fxml;
    exports com.cryptwolf;
}
