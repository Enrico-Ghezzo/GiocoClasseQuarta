open module com.game.megaman2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    //opens com.game.pokemon to javafx.fxml;
    exports com.game.pokemon;
}