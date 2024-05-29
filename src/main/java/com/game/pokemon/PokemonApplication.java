package com.game.pokemon;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PokemonApplication extends GameApplication {

    private Entity player, saffi;  //giocatore
    private Music gameMusic;    //musica del gioco
    private Boolean actSaffi = false;

    //INIZIALIZZA LE IMPOSTAZIONI DEL GIOCO
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1000);
        settings.setHeight(720);
        settings.setTitle("Pokemon boh");
        settings.setVersion("0.1");
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new FabbricaScene());
        //settings.setIntroEnabled(true);
        settings.setDeveloperMenuEnabled(true);
    }

    //INIZIALIZZA LA FISICA DEL GIOCO
    @Override
    protected void initPhysics() {
        PhysicsWorld physicsWorld = getPhysicsWorld();
        physicsWorld.setGravity(0, 0);
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.PLAYER, PokemonTypes.SAFFI){
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actSaffi = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actSaffi = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actSaffi = false;
            }
        });
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

        FXGL.getInput().addAction(new UserAction("Talk") {
            @Override
            protected void onActionBegin() {
                if(actSaffi){
                    getDialogService().showMessageBox("SAFFI: BUONGIORNO SIGNORI!!!!", () -> {});
                }
            }
        }, KeyCode.E);

        FXGL.getInput().addAction(new UserAction("Attack") {
            @Override
            protected void onActionBegin() {
                if(actSaffi){
                    saffi.getComponent(VitaComponent.class).prendiDanno(player.getComponent(PlayerControl.class).attacca());
                }
            }
        }, KeyCode.F);
    }

    //INIZIALIZZA IL GIOCO IN GENERALE
    @Override
    protected void initGame() {
        //crea una nuova fabbrica di entità
        getGameWorld().addEntityFactory(new FabbricaEntita());

        //inserisce la mappa con tutte le sue collisioni
        var map = FXGL.setLevelFromMap("livello.tmx");

        double[] coordinatePlayer = trovaSpawn(map, "SPAWNPOINT");
        double[] coordinateSaffi = trovaSpawn(map, "SPAWNSAFFI");

        //inserisce il player
        player = getGameWorld().spawn("player", coordinatePlayer[0], coordinatePlayer[1]);
        saffi = getGameWorld().spawn("saffi", coordinateSaffi[0], coordinateSaffi[1]);

        //sistema la camera
        getGameScene().getViewport().bindToEntity(player, getAppWidth()/2 - player.getWidth()/2, getAppHeight()/2 - player.getHeight()/2);
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

    private double[] trovaSpawn(Level map, String tipo){
        int i = 0;
        double[] coordinate = new double[2];
        while(true){
            try{
                if(map.getEntities().get(i).getType().toString() == tipo){
                    coordinate[0] = map.getEntities().get(i).getX();
                    coordinate[1] = map.getEntities().get(i).getY();
                    return coordinate;
                }
            }
            catch(Exception e){
                break;
            }
            i++;
        }
        return null;
    }

    //LANCIA IL GIOCO
    public static void main(String[] args) {
        launch(args);
    }
}