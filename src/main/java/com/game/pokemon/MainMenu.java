package com.game.pokemon;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class MainMenu extends FXGLMenu {

    public MainMenu() {
        super(MenuType.MAIN_MENU);

        // Load the background image
        Image backgroundImage = FXGL.image("sfondo1.jpg");

        // Create a BackgroundImage
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        // Set the background of the root pane
        getRoot().setBackground(new Background(background));
        //getContentRoot().setBackground(new Background(background));

        Text title = new Text("Megaman");
        title.getStyleClass().add("menu-title");

        Button btnStart = new Button("Start Game");
        btnStart.setOnAction(_ -> fireNewGame());

        Button btnLevel = new Button("Levels");
        //btnLevel.setOnAction();

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(_ -> fireExit());

        VBox menuBox = new VBox(10, title, btnStart, btnLevel, btnExit);
        menuBox.setTranslateX(getAppWidth() / 2 - 50);
        menuBox.setTranslateY(getAppHeight() / 2 - 50);
        getContentRoot().getChildren().add(menuBox);
    }

}