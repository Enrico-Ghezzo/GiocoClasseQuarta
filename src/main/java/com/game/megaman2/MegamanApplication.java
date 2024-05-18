package com.game.megaman2;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MegamanApplication extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        settings.setHeight((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        settings.setFullScreenFromStart(true);
        settings.setFullScreenAllowed(true);
        settings.setTitle("Megaman");
        settings.setVersion("0.1");
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                if (player.getRightX() + velocity <= getAppWidth() - playerWidth) {
                    player.translateX(velocity); // move right
                    player.setScaleX(playerScale);
                    if (!isRunningRight) {
                        texture.loopAnimationChannel(animRun);
                        isRunningRight = true;
                    }
                }
            }

            @Override
            protected void onActionEnd() {
                if (isRunningRight) {
                    texture.loopAnimationChannel(animIdle);
                    isRunningRight = false;
                }
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                if (player.getRightX() + velocity >= playerWidth) {
                    player.translateX(-velocity); // move left
                    player.setScaleX(-playerScale);
                    if (!isRunningRight) {
                        texture.loopAnimationChannel(animRun);
                        isRunningRight = true;
                    }
                }
            }

            @Override
            protected void onActionEnd() {
                if (isRunningRight) {
                    texture.loopAnimationChannel(animIdle);
                    isRunningRight = false;
                }
            }
        }, KeyCode.A);

        FXGL.getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                if (player.getY() + velocity >= 0) {
                    player.translateY(-velocity); // move up
                    if (!isRunningRight) {
                        texture.loopAnimationChannel(animRun);
                        isRunningRight = true;
                    }
                }
            }

            @Override
            protected void onActionEnd() {
                if (isRunningRight) {
                    texture.loopAnimationChannel(animIdle);
                    isRunningRight = false;
                }
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                if (player.getY() + velocity <= getAppHeight() - playerHeight) {
                    player.translateY(velocity); // move down
                    if (!isRunningRight) {
                        texture.loopAnimationChannel(animRun);
                        isRunningRight = true;
                    }
                }
            }

            @Override
            protected void onActionEnd() {
                if (isRunningRight) {
                    texture.loopAnimationChannel(animIdle);
                    isRunningRight = false;
                }
            }
        }, KeyCode.S);

        onKeyDown(KeyCode.F, () -> {
            play("drop.wav");
        });
    }

    private float playerScale;
    private float velocity;
    private double playerWidth;
    private double playerHeight;
    private AnimationChannel animIdle, animRun;
    private AnimatedTexture texture;
    private boolean isRunningRight;

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        playerScale = 2.5f;
        velocity = 6;
    }

    private Entity player;

    @Override
    protected void initGame() {
        ArrayList runFrames = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            runFrames.add(FXGL.image("corsa/corsa" + i + ".png"));
        }
        animRun = new AnimationChannel(runFrames, Duration.seconds(0.5));
        runFrames.clear();
        runFrames.add(FXGL.image("spawn8.png"));
        animIdle = new AnimationChannel(runFrames, Duration.seconds(2));
        texture = new AnimatedTexture(animIdle);

        player = entityBuilder()
                .at(300, 300)
                .scale(playerScale, playerScale)
                .view(texture)
                .anchorFromCenter()
                .collidable()
                .buildAndAttach();

        playerWidth = player.getViewComponent().getChildren().get(0).getLayoutBounds().getWidth() * playerScale;
        playerHeight = player.getViewComponent().getChildren().get(0).getLayoutBounds().getHeight() * playerScale;
    }

    @Override
    protected void initUI() {
        getGameScene().setBackgroundRepeat("sfondo1.jpg");
    }

    public static void main(String[] args) {
        launch(args);
    }
}