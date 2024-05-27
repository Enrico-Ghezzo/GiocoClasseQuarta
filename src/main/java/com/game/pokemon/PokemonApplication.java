package com.game.pokemon;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.InputSequence;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PokemonApplication extends GameApplication {

    private Entity player;  //giocatore
    private Music gameMusic;    //musica del gioco

    //INIZIALIZZA LE IMPOSTAZIONI DEL GIOCO
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1000);
        settings.setHeight(720);
        settings.setTitle("Pokemon boh");
        settings.setVersion("0.1");
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new FabbricaScene());
        settings.setIntroEnabled(true);
    }

    //INIZIALIZZA LA FISICA DEL GIOCO
    @Override
    protected void initPhysics() {
        PhysicsWorld physicsWorld = getPhysicsWorld();
        physicsWorld.setGravity(0, 0);
    }

    //INIZIALIZZA L'INPUT DEL PLAYER
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


    //INIZIALIZZA IL GIOCO IN GENERALE
    @Override
    protected void initGame() {
        //crea una nuova fabbrica di entità
        getGameWorld().addEntityFactory(new FabbricaEntita());

        //inserisce la mappa con tutte le sue collisioni
        var map = FXGL.setLevelFromMap("livello.tmx");

        double spawnX = 0, spawnY = 0;
        int i = 0;
        while(true){
            try{
                if(map.getEntities().get(i).getType().toString() == "SPAWNPOINT"){
                    spawnX = map.getEntities().get(i).getX();
                    spawnY = map.getEntities().get(i).getY();
                }
            }
            catch(Exception e){
                break;
            }
            i++;
        }

        //inserisce il player
        player = getGameWorld().spawn("player", spawnX, spawnY);

        //sistema la camera
        getGameScene().getViewport().bindToEntity(player, getAppWidth()/2, getAppHeight()/2);
        getGameScene().getViewport().setZoom(1.5f);

        //aggiunge la musica di sottofondo
        gameMusic = FXGL.getAssetLoader().loadMusic("musicaSottofondo.mp3");
        FXGL.getAudioPlayer().loopMusic(gameMusic);
    }

    //INIZIALIZZA ANCORA ROBA PRIMA DI INITGAME
    @Override
    protected void onPreInit() {
        FXGL.getAudioPlayer().stopAllMusic();
    }

    //LANCIA IL GIOCO
    public static void main(String[] args) {
        launch(args);
    }
}