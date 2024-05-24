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
                player.getComponent(PlayerControl.class).right();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerControl.class).ferma();
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerControl.class).left();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerControl.class).ferma();
            }
        }, KeyCode.A);

        FXGL.getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerControl.class).up();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerControl.class).ferma();
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerControl.class).down();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerControl.class).ferma();
            }
        }, KeyCode.S);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {

    }

    private Entity player;

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new FabbricaEntita());

        FXGL.setLevelFromMap("livello.tmx");

        player = getGameWorld().spawn("player");
    }

    @Override
    protected void initUI() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}