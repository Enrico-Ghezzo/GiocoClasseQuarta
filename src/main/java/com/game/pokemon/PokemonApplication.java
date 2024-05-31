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
    private Entity player, saffi, mancino, biral, zuccon, diStefano, funes, sandi, pesenti, penzo, pagan, steve, groudon, lapras; //definizione delle entità
    private Music gameMusic;    //musica del gioco

    //definizione delle variabili per interagire con le entità
    private Boolean actSaffi = false,
            actMancino = false,
            actBiral = false,
            actZuccon = false,
            actDistefano = false,
            actFunes = false,
            actSandi = false,
            actPesenti = false,
            actPenzo = false,
            actPagan = false,
            actSteve = false,
            actGroudon = false,
            actLapras = false;

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
        //mette la gravità a 0
        PhysicsWorld physicsWorld = getPhysicsWorld();
        physicsWorld.setGravity(0, 0);

        //aggiunge collisionHandler per tutte le entità
        //collisioni saffi
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.SAFFI, PokemonTypes.PLAYER){
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

        //collisioni mancino
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.MANCINO, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actMancino = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actMancino = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actMancino = false;
            }
        });

        //collisioni biral
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.BIRAL, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actBiral = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actBiral = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actBiral = false;
            }
        });

        //collisioni zuccon
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.ZUCCON, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actZuccon = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actZuccon = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actZuccon = false;
            }
        });

        //collisioni di stefano
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.DISTEFANO, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actDistefano = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actDistefano = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actDistefano = false;
            }
        });

        //collisioni funes
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.FUNES, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actFunes = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actFunes = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actFunes = false;
            }
        });

        //collisioni sandi
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.SANDI, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actSandi = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actSandi = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actSandi = false;
            }
        });

        //collisioni pesenti
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.PESENTI, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actPesenti = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actPesenti = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actPesenti = false;
            }
        });

        //collisioni penzo
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.PENZO, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actPenzo = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actPenzo = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actPenzo = false;
            }
        });

        //collisioni player
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.PAGAN, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actPagan = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actPagan = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actPagan = false;
            }
        });

        //collisioni steve
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.STEVE, PokemonTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actSteve = true;
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actSteve = true;
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actSteve = false;
            }
        });

        //collisioni groudon
        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.GROUDON, PokemonTypes.PLAYER){
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actGroudon = true;
                groudon.getComponent(GroudonControl.class).setIsColliding(true);
                groudon.getComponent(GroudonControl.class).attacca(() -> {
                    if(groudon.getComponent(GroudonControl.class).getIsColliding()){
                        player.getComponent(VitaComponent.class).prendiDanno(groudon.getComponent(GroudonControl.class).getDanno());
                    }
                });
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actGroudon = true;
                groudon.getComponent(GroudonControl.class).setIsColliding(true);
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actGroudon = false;
                groudon.getComponent(GroudonControl.class).setIsColliding(false);
            }
        });

        physicsWorld.addCollisionHandler(new CollisionHandler(PokemonTypes.LAPRAS, PokemonTypes.PLAYER){
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                actLapras = true;
                lapras.getComponent(LaprasControl.class).setIsColliding(true);
                lapras.getComponent(LaprasControl.class).attacca(() -> {
                    if(lapras.getComponent(LaprasControl.class).getIsColliding()){
                        player.getComponent(VitaComponent.class).prendiDanno(lapras.getComponent(LaprasControl.class).getDanno());
                    }
                });
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                actLapras = true;
                lapras.getComponent(LaprasControl.class).setIsColliding(true);
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                actLapras = false;
                lapras.getComponent(LaprasControl.class).setIsColliding(false);
            }
        });
    }
//############################################################################################################
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
                if(actMancino){
                    getDialogService().showMessageBox("MANCINO: BERNARDI VAI A VEDERE SE C'E' PESENTI", () -> {});
                }
                if(actBiral){
                    getDialogService().showMessageBox("BIRAL: POPA...  ESSERE INUTILE...", () -> {});
                }
                if(actZuccon){
                    getDialogService().showMessageBox("ZUCCON: *FRASE DA DECIDERE*", () -> {});
                }
                if(actDistefano){
                    getDialogService().showMessageBox("DI STEFANO: HAI FATTO GLI ESERCIZI?", () -> {});
                }
                if(actFunes){
                    getDialogService().showMessageBox("FUNES: APRITE CISCO PACKET TRACER", () -> {});
                }
                if(actSandi){
                    getDialogService().showMessageBox("SANDI: COME SCUSA ?!?!", () -> {});
                }
                if(actPesenti){
                    getDialogService().showMessageBox("PESENTI: NON HO ANCORA CORRETTO LE VERIFICHE", () -> {});
                }
                if(actPenzo){
                    getDialogService().showMessageBox("PENZO: HAI GIUSTIFICATO LE ASSENZE?", () -> {});
                }
                if(actPagan){
                    getDialogService().showMessageBox("PAGAN: HAI GIA' FATTO IL RISCALDAMENTO?", () -> {});
                }
                if(actSteve){
                    getDialogService().showMessageBox("STEVE: VUOI GIOCARE A LOL?", () -> {});
                }
            }
        }, KeyCode.E);

        FXGL.getInput().addAction(new UserAction("Attack") {
            @Override
            protected void onActionBegin() {
                if(actGroudon){
                    groudon.getComponent(VitaComponent.class).prendiDanno(player.getComponent(PlayerControl.class).attacca());
                }
                if(actLapras){
                    lapras.getComponent(VitaComponent.class).prendiDanno(player.getComponent(PlayerControl.class).attacca());
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

        //prende le coordinate dello spawn di tutte le entità
        double[] coordinatePlayer = trovaSpawn(map, "SPAWNPOINT");
        double[] coordinateSaffi = trovaSpawn(map, "SPAWNSAFFI");
        double[] coordinateMancino = trovaSpawn(map, "SPAWNMANCINO");
        double[] coordinateBiral = trovaSpawn(map, "SPAWNBIRAL");
        double[] coordinateZuccon = trovaSpawn(map, "SPAWNZUCCON");
        double[] coordinateDistefano = trovaSpawn(map, "SPAWNDISTEFANO");
        double[] coordinateFunes = trovaSpawn(map, "SPAWNFUNES");
        double[] coordinateSandi = trovaSpawn(map, "SPAWNSANDI");
        double[] coordinatePesenti = trovaSpawn(map, "SPAWNPESENTI");
        double[] coordinatePenzo = trovaSpawn(map, "SPAWNPENZO");
        double[] coordinatePagan = trovaSpawn(map, "SPAWNPAGAN");
        double[] coordinateSteve = trovaSpawn(map, "SPAWNSTEVE");
        double[] coordinateGroudon = trovaSpawn(map, "SPAWNGROUDON");
        double[] coordinateLapras = trovaSpawn(map, "SPAWNLAPRAS");


        //inserisce le entità
        player = getGameWorld().spawn("player", coordinatePlayer[0], coordinatePlayer[1]);
        saffi = getGameWorld().spawn("saffi", coordinateSaffi[0], coordinateSaffi[1]);
        mancino = getGameWorld().spawn("mancino", coordinateMancino[0], coordinateMancino[1]);
        biral = getGameWorld().spawn("biral", coordinateBiral[0], coordinateBiral[1]);
        zuccon = getGameWorld().spawn("zuccon", coordinateZuccon[0], coordinateZuccon[1]);
        diStefano = getGameWorld().spawn("distefano", coordinateDistefano[0], coordinateDistefano[1]);
        funes = getGameWorld().spawn("funes", coordinateFunes[0], coordinateFunes[1]);
        sandi = getGameWorld().spawn("sandi", coordinateSandi[0], coordinateSandi[1]);
        pesenti = getGameWorld().spawn("pesenti", coordinatePesenti[0], coordinatePesenti[1]);
        penzo = getGameWorld().spawn("penzo", coordinatePenzo[0], coordinatePenzo[1]);
        pagan = getGameWorld().spawn("pagan", coordinatePagan[0], coordinatePagan[1]);
        steve = getGameWorld().spawn("steve", coordinateSteve[0], coordinateSteve[1]);
        groudon = getGameWorld().spawn("groudon", coordinateGroudon[0], coordinateGroudon[1]);
        lapras = getGameWorld().spawn("lapras", coordinateLapras[0], coordinateLapras[1]);

        //sistema la telecamera
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

    @Override
    protected void onUpdate(double tpf) {

        //controlla se il player è morto
        if(player.getComponent(VitaComponent.class).getVita()<=0){
            FXGL.getNotificationService().pushNotification("SEI MORTO");
            getGameController().gotoMainMenu();
        }
    }

    //LANCIA IL GIOCO
    public static void main(String[] args) {
        launch(args);
    }
}