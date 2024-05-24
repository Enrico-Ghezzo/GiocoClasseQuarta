package com.game.pokemon;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PokemonApplication extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1000);
        settings.setHeight(720);
        settings.setTitle("Pokemon boh");
        settings.setVersion("0.1");
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new FabbricaScene());
    }

    @Override
    protected void initPhysics() {
        PhysicsWorld physicsWorld = getPhysicsWorld();
        physicsWorld.setGravity(0, 0); // Disable gravity by setting it to (0, 0)
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityX(velocity); // set velocity right
                player.setScaleX(-playerScale);
                if (!isRunningRight) {
                    texture.loopAnimationChannel(animRunSide);
                    isRunningRight = true;
                }
            }

            @Override
            protected void onActionEnd() {
                if (isRunningRight) {
                    player.getComponent(PhysicsComponent.class).setVelocityX(0); // stop moving
                    texture.loopAnimationChannel(animIdle);
                    isRunningRight = false;
                }
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityX(-velocity); // set velocity left
                player.setScaleX(playerScale);
                if (!isRunningRight) {
                    texture.loopAnimationChannel(animRunSide);
                    isRunningRight = true;
                }
            }

            @Override
            protected void onActionEnd() {
                if (isRunningRight) {
                    player.getComponent(PhysicsComponent.class).setVelocityX(0); // stop moving
                    texture.loopAnimationChannel(animIdle);
                    isRunningRight = false;

                }
            }
        }, KeyCode.A);

        FXGL.getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityY(-velocity); // set velocity right
                player.setScaleX(-playerScale);
                if (!isRunningRight) {
                    texture.loopAnimationChannel(animRunUp);
                    isRunningRight = true;
                }
            }

            @Override
            protected void onActionEnd() {
                if (isRunningRight) {
                    player.getComponent(PhysicsComponent.class).setVelocityY(0); // stop moving
                    texture.loopAnimationChannel(animIdle);
                    isRunningRight = false;
                }
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityY(velocity); // set velocity right
                player.setScaleX(playerScale);
                if (!isRunningRight) {
                    texture.loopAnimationChannel(animRunDown);
                    isRunningRight = true;
                }
            }

            @Override
            protected void onActionEnd() {
                if (isRunningRight) {
                    player.getComponent(PhysicsComponent.class).setVelocityY(0); // stop moving
                    texture.loopAnimationChannel(animIdle);
                    isRunningRight = false;
                }
            }
        }, KeyCode.S);
    }

    private double playerScale;
    private float velocity;
    private double playerWidth;
    private double playerHeight;
    private AnimationChannel animIdle, animRunSide, animRunUp, animRunDown;
    private AnimatedTexture texture;
    private boolean isRunningRight;

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        playerScale = 2.5f;
        velocity = 200;
    }

    private Entity player;

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new FabbricaEntita());

        FXGL.setLevelFromMap("livello.tmx");

        ArrayList runFrames = new ArrayList<>();
        runFrames.add(FXGL.image("matteolongobardi.png"));
        animIdle = new AnimationChannel(runFrames, Duration.seconds(2));
        texture = new AnimatedTexture(animIdle);

        player = getGameWorld().spawn("player", new SpawnData().put("texture", texture).put("playerScale", playerScale));

        playerWidth = player.getViewComponent().getChildren().get(0).getLayoutBounds().getWidth() * playerScale;
        playerHeight = player.getViewComponent().getChildren().get(0).getLayoutBounds().getHeight() * playerScale;

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsalaterale/corsalaterale" + i + ".png", (playerWidth+1)/playerScale, (playerHeight+1)/playerScale));
        }
        animRunSide = new AnimationChannel(runFrames, Duration.seconds(0.5));

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsasu/corsasu" + i + ".png", (playerWidth+1)/playerScale, (playerHeight+1)/playerScale));
        }
        animRunUp = new AnimationChannel(runFrames, Duration.seconds(0.5));

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsagiu/corsagiu" + i + ".png", (playerWidth+1)/playerScale, (playerHeight+1)/playerScale));
        }
        animRunDown = new AnimationChannel(runFrames, Duration.seconds(0.5));

        //mette i limiti alla schermata
        entityBuilder()
                .buildScreenBoundsAndAttach(playerHeight);
    }

    @Override
    protected void initUI() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}