package com.game.pokemon;

import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getNotificationService;

public class GroudonControl extends Component {
    private PhysicsComponent physics;
    private AnimationChannel animIdle, animRunSide, animRunUp, animRunDown, animAttackDown, animAttackSide, animAttackUp, animDeath;
    private AnimatedTexture texture;
    private double playerScale = 1.5f;
    private float velocity = 50;
    private int danno = 20;
    private String direzione = "giu";
    private boolean isColliding = false, isAttacking = false;

    public GroudonControl() {
        //CARICA LE ANIMAZIONI IN MEMORIA
        ArrayList runFrames = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            runFrames.add(FXGL.image("groudon/idle" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animIdle = new AnimationChannel(runFrames, Duration.seconds(1));
        texture = new AnimatedTexture(animIdle);
        texture.loopAnimationChannel(animIdle);
        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/groudonlaterale" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animRunSide = new AnimationChannel(runFrames, Duration.seconds(0.7));

        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/groudonindietro" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animRunUp = new AnimationChannel(runFrames, Duration.seconds(0.7));

        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/groudonavanti" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animRunDown = new AnimationChannel(runFrames, Duration.seconds(0.7));

        runFrames.clear();
        for (int i = 1; i <= 12; i++) {
            runFrames.add(FXGL.image("groudon/morte" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animDeath = new AnimationChannel(runFrames, Duration.seconds(1));

        //ANIMAZIONI ATTACCO
        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/attaccogiu" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animAttackDown = new AnimationChannel(runFrames, Duration.seconds(1.5));

        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/attaccolaterale" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animAttackSide = new AnimationChannel(runFrames, Duration.seconds(1.5));

        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/attaccosu" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animAttackUp = new AnimationChannel(runFrames, Duration.seconds(1.5));


    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture); //mette la texture
    }

    @Override
    public void onUpdate(double tpf) {
        if(entity.getComponent(VitaComponent.class).isDead()){
            physics.setVelocityX(0);
            physics.setVelocityY(0);
            if(texture.getAnimationChannel() != animDeath){
                getNotificationService().pushNotification("HAI UCCISO GROUDON");
                entity.getComponent(VitaComponent.class).muori(texture, animDeath);
            }
        }

        //MOVIMENTO AUTOMATICO DI GROUDON
        if (FXGL.getGameWorld().getEntitiesByType(PokemonTypes.PLAYER).getFirst().getCenter().distance(FXGL.getGameWorld().getEntitiesByType(PokemonTypes.RINGGROUDON).getFirst().getCenter()) <= FXGL.getGameWorld().getEntitiesByType(PokemonTypes.RINGGROUDON).getFirst().getWidth()/2 && !entity.getComponent(VitaComponent.class).isDead()){
            //ASSE X
            if(FXGL.getGameWorld().getEntitiesByType(PokemonTypes.PLAYER).getFirst().getPosition().getX() >= entity.getCenter().getX() && !isColliding){
                physics.setVelocityX(velocity);
            } else if (FXGL.getGameWorld().getEntitiesByType(PokemonTypes.PLAYER).getFirst().getPosition().getX() + FXGL.getGameWorld().getEntitiesByType(PokemonTypes.PLAYER).getFirst().getWidth() <= entity.getCenter().getX() && !isColliding) {
                physics.setVelocityX(-velocity);
            } else{
                physics.setVelocityX(0);
            }

            //ASSE Y
            if(FXGL.getGameWorld().getEntitiesByType(PokemonTypes.PLAYER).getFirst().getPosition().getY() >= entity.getCenter().getY() && !isColliding){
                physics.setVelocityY(velocity);
            } else if (FXGL.getGameWorld().getEntitiesByType(PokemonTypes.PLAYER).getFirst().getPosition().getY() + FXGL.getGameWorld().getEntitiesByType(PokemonTypes.PLAYER).getFirst().getWidth() <= entity.getCenter().getY() && !isColliding) {
                physics.setVelocityY(-velocity);
            } else{
                physics.setVelocityY(0);
            }
        }
        else{
            physics.setVelocityX(0);
            physics.setVelocityY(0);
        }

        //ANIMAZIONI IN BASE AL MOVIMENTO DEL PERSONAGGIO
        if(!isAttacking && !entity.getComponent(VitaComponent.class).isDead()){
            if (physics.getVelocityX() > 0 && physics.getVelocityY() == 0) {  //se va a destra
                if (!(texture.getAnimationChannel() == animRunSide)) {
                    texture.loopAnimationChannel(animRunSide);
                }
                texture.setScaleX(-1);
                direzione = "destra";
            }
            if (physics.getVelocityX() < 0 && physics.getVelocityY() == 0) {  //se va a sinistra
                if (!(texture.getAnimationChannel() == animRunSide)) {
                    texture.loopAnimationChannel(animRunSide);
                }
                texture.setScaleX(1);
                direzione = "sinistra";
            }
            if (physics.getVelocityY() < 0) {  //se va su
                if (!(texture.getAnimationChannel() == animRunUp)) {
                    texture.loopAnimationChannel(animRunUp);
                }
                direzione = "su";
            }
            if (physics.getVelocityY() > 0) {  //se va giu
                if (!(texture.getAnimationChannel() == animRunDown)) {
                    texture.loopAnimationChannel(animRunDown);
                }
                direzione = "giu";
            }
            if (physics.getVelocityX() == 0 && physics.getVelocityY() == 0) {
                if(!(texture.getAnimationChannel() == animIdle)){
                    texture.loopAnimationChannel(animIdle);
                }
                direzione = "giu";
            }
        }
    }

    public boolean isPlayerInRing(){
        return FXGL.getGameWorld().getEntitiesByType(PokemonTypes.PLAYER).getFirst().getCenter().distance(FXGL.getGameWorld().getEntitiesByType(PokemonTypes.RINGGROUDON).getFirst().getCenter()) <= FXGL.getGameWorld().getEntitiesByType(PokemonTypes.RINGGROUDON).getFirst().getWidth()/2 && !entity.getComponent(VitaComponent.class).isDead();
    }

    public void setIsColliding(boolean b){
        isColliding = b;
    }
    public boolean getIsColliding(){
        return isColliding;
    }

    public int getDanno(){
        return danno;
    }

    public void attacca(Runnable damageCallback){
        if(isColliding && !isAttacking){
            isAttacking = true;
            if(direzione == "destra"){
                texture.playAnimationChannel(animAttackSide);
                texture.setScaleX(-1);
                texture.setOnCycleFinished(() -> {
                    texture.loopAnimationChannel(animIdle);
                    texture.setScaleX(1);
                    isAttacking = false;
                    damageCallback.run();
                });
            }
            else if(direzione == "sinistra"){
                texture.playAnimationChannel(animAttackSide);
                texture.setScaleX(1);
                texture.setOnCycleFinished(() -> {
                    texture.loopAnimationChannel(animIdle);
                    texture.setScaleX(-1);
                    isAttacking = false;
                    damageCallback.run();
                });
            }
            else if(direzione == "su"){
                texture.playAnimationChannel(animAttackUp);
                texture.setOnCycleFinished(() -> {
                    texture.loopAnimationChannel(animIdle);
                    isAttacking = false;
                    damageCallback.run();
                });
            }
            else if(direzione == "giu"){
                texture.playAnimationChannel(animAttackDown);
                texture.setOnCycleFinished(() -> {
                    texture.loopAnimationChannel(animIdle);
                    isAttacking = false;
                    damageCallback.run();
                });
            }
        }
    }

}
