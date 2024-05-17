package com.game.megaman2;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;

import java.awt.*;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MegamanApplication extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1920);
        settings.setHeight(1080);
        settings.setFullScreenFromStart(true);
        settings.setFullScreenAllowed(true);
        settings.setTitle("Megaman");
        settings.setVersion("0.1");
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.D, () -> {
            if(!(player.getX() <= 0 || player.getX() >= Toolkit.getDefaultToolkit().getScreenSize().getWidth())){
                player.translateX(velocity); // move right
                player.setScaleX(playerScale);
            }
        });

        onKey(KeyCode.A, () -> {

            if(!(player.getX() < 0 || player.getX() > Toolkit.getDefaultToolkit().getScreenSize().getWidth())){
                player.translateX(-velocity); // move right
                player.setScaleX(-playerScale);
            }
        });

        onKey(KeyCode.W, () -> {
            player.translateY(-velocity); // move up
        });

        onKey(KeyCode.S, () -> {
            player.translateY(velocity); // move down
        });

        onKeyDown(KeyCode.F, () -> {
            play("drop.wav");
        });
    }

    private float playerScale;
    private float velocity;
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        playerScale = 2.5f;
        velocity = 6;
    }

    private Entity player;

    @Override
    protected void initGame() {
        player = entityBuilder()
                .at(300, 300)
                .scale(playerScale, playerScale)
                .view("spawn8.png")
                .buildAndAttach();
    }

    @Override
    protected void initUI() {
        getGameScene().setBackgroundRepeat("sfondo1.jpg");
    }

    public static void main(String[] args) {
        launch(args);
    }
}