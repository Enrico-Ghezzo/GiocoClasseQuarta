module com.game.megaman2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.game.megaman2 to javafx.fxml;
    exports com.game.megaman2;
}