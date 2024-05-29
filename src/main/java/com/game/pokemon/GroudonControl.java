package com.game.pokemon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

import java.util.ArrayList;

public class GroudonControl extends Component {
    private PhysicsComponent physics;
    private AnimationChannel animIdle, animRunSide, animRunUp, animRunDown, animAttackDown, animAttackSide, animAttackUp;
    private AnimatedTexture texture;
    private double playerScale = 1.5f;
    private float velocity = 100;
    private int danno = 10;
    private String direzione = "giu";

    public GroudonControl() {
        //CARICA LE ANIMAZIONI IN MEMORIA
        ArrayList runFrames = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            runFrames.add(FXGL.image("groudon/idle" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animIdle = new AnimationChannel(runFrames, Duration.seconds(0.5));
        texture = new AnimatedTexture(animIdle);
        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/groudonavanti" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animRunSide = new AnimationChannel(runFrames, Duration.seconds(1));

        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/groudonlaterale" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animRunUp = new AnimationChannel(runFrames, Duration.seconds(1));

        runFrames.clear();
        for (int i = 1; i <= 3; i++) {
            runFrames.add(FXGL.image("groudon/groudonlaterale" + i + ".png", 40*playerScale, 53*playerScale));
        }
        animRunDown = new AnimationChannel(runFrames, Duration.seconds(1));

        /*
        //ANIMAZIONI ATTACCO
        runFrames.clear();
        for (int i = 1; i <= 4; i++) {
            runFrames.add(FXGL.image("canna/cannagiu" + i + ".png", 14*playerScale, 21*playerScale));
        }
        animAttackDown = new AnimationChannel(runFrames, Duration.seconds(0.4));

        runFrames.clear();
        for (int i = 1; i <= 4; i++) {
            runFrames.add(FXGL.image("canna/cannalaterale" + i + ".png", 14*playerScale, 21*playerScale));
        }
        animAttackSide = new AnimationChannel(runFrames, Duration.seconds(0.4));

        runFrames.clear();
        for (int i = 1; i <= 4; i++) {
            runFrames.add(FXGL.image("canna/cannasu" + i + ".png", 14*playerScale, 21*playerScale));
        }
        animAttackUp = new AnimationChannel(runFrames, Duration.seconds(0.4));

         */

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture); //mette la texture
    }

    @Override
    public void onUpdate(double tpf) {
        //ANIMAZIONI IN BASE AL MOVIMENTO DEL PERSONAGGIO
        if (texture.getAnimationChannel() != animAttackDown || texture.getAnimationChannel() != animAttackSide || texture.getAnimationChannel() != animAttackUp) {
            if(physics.getVelocityX() > 0 && physics.getVelocityY() == 0){  //se va a destra
                if(!(texture.getAnimationChannel() == animRunSide)){
                    texture.loopAnimationChannel(animRunSide);
                }
                texture.setScaleX(-1);
                direzione = "destra";
            }
            if(physics.getVelocityX() < 0 && physics.getVelocityY() == 0){  //se va a sinistra
                if(!(texture.getAnimationChannel() == animRunSide)){
                    texture.loopAnimationChannel(animRunSide);
                }
                texture.setScaleX(1);
                direzione = "sinistra";
            }
            if(physics.getVelocityY() < 0 && physics.getVelocityX() == 0){  //se va su
                if(!(texture.getAnimationChannel() == animRunUp)){
                    texture.loopAnimationChannel(animRunUp);
                }
                direzione = "su";
            }
            if(physics.getVelocityY() > 0 && physics.getVelocityX() == 0){  //se va giu
                if(!(texture.getAnimationChannel() == animRunDown)){
                    texture.loopAnimationChannel(animRunDown);
                }
                direzione = "giu";
            }
        }
        else if (texture.getAnimationChannel() != animIdle){
            texture.loopAnimationChannel(animIdle);
        }

    }

    //FUNZIONI PER IL MOVIMENTO
    public void right(){
        physics.setVelocityX(velocity); // set velocity right
    }

    public void left(){
        physics.setVelocityX(-velocity); // set velocity right
    }

    public void up(){
        physics.setVelocityY(-velocity); // set velocity right
    }

    public void down(){
        physics.setVelocityY(velocity); // set velocity right
    }

    public void ferma(){
        physics.setVelocityX(0);
        physics.setVelocityY(0);
        texture.loopAnimationChannel(animIdle);
    }

    public int attacca(){
        if(texture.getAnimationChannel() != animAttackDown || texture.getAnimationChannel() != animAttackSide || texture.getAnimationChannel() != animAttackUp){
            if(direzione == "destra"){
                texture.playAnimationChannel(animAttackSide);
                texture.setScaleX(1);
                texture.setOnCycleFinished(() -> {
                    texture.loopAnimationChannel(animIdle);
                    texture.setScaleX(-1);
                });
            }
            else if(direzione == "sinistra"){
                texture.playAnimationChannel(animAttackSide);
                texture.setScaleX(-1);
                texture.setOnCycleFinished(() -> {
                    texture.loopAnimationChannel(animIdle);
                    texture.setScaleX(1);
                });
            }
            else if(direzione == "su"){
                texture.playAnimationChannel(animAttackUp);
                texture.setScaleX(1);
                texture.setOnCycleFinished(() -> {
                    texture.loopAnimationChannel(animIdle);
                });
            }
            else if(direzione == "giu"){
                texture.playAnimationChannel(animAttackDown);
                texture.setOnCycleFinished(() -> {
                    texture.loopAnimationChannel(animIdle);
                });
            }
            return danno;
        }
        return 0;
    }
}
