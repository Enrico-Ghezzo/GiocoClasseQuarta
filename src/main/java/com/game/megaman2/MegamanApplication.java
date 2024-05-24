package com.game.megaman2;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
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
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new FabbricaScene());
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityX(velocity); // set velocity right
                player.setScaleX(playerScale);
                if (!isRunningRight) {
                    texture.loopAnimationChannel(animRun);
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
                player.setScaleX(-playerScale);
                if (!isRunningRight) {
                    texture.loopAnimationChannel(animRun);
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

        FXGL.getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onActionBegin() {
                        if(player.getBottomY()>= getAppHeight()-playerHeight/2){//verifico se è dove spawna
                        player.getComponent(PhysicsComponent.class).setVelocityY(-jumpVelocity);}
            }
        }, KeyCode.SPACE);
    }

    private double playerScale;
    private float velocity;
    private float jumpVelocity;
    private double playerWidth;
    private double playerHeight;
    private AnimationChannel animIdle, animRun;
    private AnimatedTexture texture;
    private boolean isRunningRight;

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        playerScale = 2.5f;
        velocity = 550;
        jumpVelocity = 350;
    }

    private Entity player;

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new FabbricaEntita());
        //FXGL.setLevelFromMap("livello.tmx");

        ArrayList runFrames = new ArrayList<>();
        runFrames.add(FXGL.image("spawn8.png"));
        animIdle = new AnimationChannel(runFrames, Duration.seconds(2));
        texture = new AnimatedTexture(animIdle);

        player = getGameWorld().spawn("player", new SpawnData().put("texture", texture). put("playerScale", playerScale));

        playerWidth = player.getViewComponent().getChildren().get(0).getLayoutBounds().getWidth() * playerScale;
        playerHeight = player.getViewComponent().getChildren().get(0).getLayoutBounds().getHeight() * playerScale;

        runFrames.clear();
        for (int i = 1; i <= 11; i++) {
            runFrames.add(FXGL.image("corsa/corsa" + i + ".png", (playerWidth+1)/playerScale, (playerHeight+1)/playerScale));
        }

        animRun = new AnimationChannel(runFrames, Duration.seconds(0.5));

        //mette i limiti alla schermata
        entityBuilder()
                .buildScreenBoundsAndAttach(playerHeight);
    }

    @Override
    protected void initUI() {
        //getGameScene().setBackgroundRepeat("sfondo1.jpg");
    }

    public static void main(String[] args) {
        launch(args);
    }
}