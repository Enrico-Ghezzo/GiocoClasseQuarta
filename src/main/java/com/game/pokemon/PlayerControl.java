package com.game.pokemon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import java.util.ArrayList;

public class PlayerControl extends Component {
    private PhysicsComponent physics;
    private AnimationChannel animIdle, animRunSide, animRunUp, animRunDown;
    private AnimatedTexture texture;
    private double playerScale = 1.5f;
    private float velocity = 100;

    public PlayerControl() {
        //CARICA LE ANIMAZIONI IN MEMORIA
        ArrayList runFrames = new ArrayList<>();
        runFrames.add(FXGL.image("matteolongobardi.png", 14*playerScale, 21*playerScale));
        animIdle = new AnimationChannel(runFrames, Duration.seconds(2));
        texture = new AnimatedTexture(animIdle);
        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsalaterale/corsalaterale" + i + ".png", 14*playerScale, 21*playerScale));
        }
        animRunSide = new AnimationChannel(runFrames, Duration.seconds(1));

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsasu/corsasu" + i + ".png", 14*playerScale, 21*playerScale));
        }
        animRunUp = new AnimationChannel(runFrames, Duration.seconds(1));

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsagiu/corsagiu" + i + ".png", 14*playerScale, 21*playerScale));
        }
        animRunDown = new AnimationChannel(runFrames, Duration.seconds(1));
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture); //mette la texture
    }

    @Override
    public void onUpdate(double tpf) {
        //ANIMAZIONI IN BASE AL MOVIMENTO DEL PERSONAGGIO
        if(physics.getVelocityX() > 0 && physics.getVelocityY() == 0){  //se va a destra
            if(!(texture.getAnimationChannel() == animRunSide)){
                texture.loopAnimationChannel(animRunSide);
            }
            texture.setScaleX(-1);
        }
        if(physics.getVelocityX() < 0 && physics.getVelocityY() == 0){  //se va a sinistra
            if(!(texture.getAnimationChannel() == animRunSide)){
                texture.loopAnimationChannel(animRunSide);
            }
            texture.setScaleX(1);
        }
        if(physics.getVelocityY() < 0 && physics.getVelocityX() == 0){  //se va su
            if(!(texture.getAnimationChannel() == animRunUp)){
                texture.loopAnimationChannel(animRunUp);
            }
        }
        if(physics.getVelocityY() > 0 && physics.getVelocityX() == 0){  //se va giu
            if(!(texture.getAnimationChannel() == animRunDown)){
                texture.loopAnimationChannel(animRunDown);
            }
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
}
