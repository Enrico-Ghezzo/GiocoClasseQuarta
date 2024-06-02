package com.game.pokemon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Classe per il controllo del player
 */
public class PlayerControl extends Component {
    private PhysicsComponent physics;
    private AnimationChannel animIdle, animRunSide, animRunUp, animRunDown, animAttackDown, animAttackSide, animAttackUp;
    private AnimatedTexture texture;
    private double playerScale = 1.5f;
    private float velocity = 150;
    private int danno = 10;
    private String direzione = "giu";
    private boolean isStopped = true;

    public PlayerControl() {
        //CARICA LE ANIMAZIONI IN MEMORIA
        ArrayList runFrames = new ArrayList<>();
        runFrames.add(FXGL.image("matteolongobardi.png", 14 * playerScale, 21 * playerScale));
        animIdle = new AnimationChannel(runFrames, Duration.seconds(2));
        texture = new AnimatedTexture(animIdle);
        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsalaterale/corsalaterale" + i + ".png", 14 * playerScale, 21 * playerScale));
        }
        animRunSide = new AnimationChannel(runFrames, Duration.seconds(1));

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsasu/corsasu" + i + ".png", 14 * playerScale, 21 * playerScale));
        }
        animRunUp = new AnimationChannel(runFrames, Duration.seconds(1));

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsagiu/corsagiu" + i + ".png", 14 * playerScale, 21 * playerScale));
        }
        animRunDown = new AnimationChannel(runFrames, Duration.seconds(1));

        //ANIMAZIONI ATTACCO
        runFrames.clear();
        for (int i = 1; i <= 4; i++) {
            runFrames.add(FXGL.image("canna/cannagiu" + i + ".png", 14 * playerScale, 21 * playerScale));
        }
        animAttackDown = new AnimationChannel(runFrames, Duration.seconds(0.4));

        runFrames.clear();
        for (int i = 1; i <= 4; i++) {
            runFrames.add(FXGL.image("canna/cannalaterale" + i + ".png", 14 * playerScale, 21 * playerScale));
        }
        animAttackSide = new AnimationChannel(runFrames, Duration.seconds(0.4));

        runFrames.clear();
        for (int i = 1; i <= 4; i++) {
            runFrames.add(FXGL.image("canna/cannasu" + i + ".png", 14 * playerScale, 21 * playerScale));
        }
        animAttackUp = new AnimationChannel(runFrames, Duration.seconds(0.4));

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture); //mette la texture
    }

    @Override
    public void onUpdate(double tpf) {
        //ANIMAZIONI IN BASE AL MOVIMENTO DEL PERSONAGGIO
        if (texture.getAnimationChannel() != animAttackDown || texture.getAnimationChannel() != animAttackSide || texture.getAnimationChannel() != animAttackUp) {
            String direction = getDirection();
            switch (direction) {
                case "destra":
                    if (!(texture.getAnimationChannel() == animRunSide) || isStopped) {
                        texture.loopAnimationChannel(animRunSide);
                        isStopped = false;
                    }
                    texture.setScaleX(-1);
                    direzione = "destra";
                    break;
                case "sinistra":
                    if (!(texture.getAnimationChannel() == animRunSide) || isStopped) {
                        texture.loopAnimationChannel(animRunSide);
                        isStopped = false;
                    }
                    texture.setScaleX(1);
                    direzione = "sinistra";
                    break;
                case "su":
                    if (!(texture.getAnimationChannel() == animRunUp) || isStopped) {
                        texture.loopAnimationChannel(animRunUp);
                        isStopped = false;
                    }
                    direzione = "su";
                    break;
                case "giu":
                    if (!(texture.getAnimationChannel() == animRunDown) || isStopped) {
                        texture.loopAnimationChannel(animRunDown);
                        isStopped = false;
                    }
                    direzione = "giu";
                    break;
            }
        }
    }

    private String getDirection() {
        if (physics.getVelocityX() > 0 && physics.getVelocityY() == 0) {
            return "destra";
        }
        if (physics.getVelocityX() < 0 && physics.getVelocityY() == 0) {
            return "sinistra";
        }
        if (physics.getVelocityY() < 0 && physics.getVelocityX() == 0) {
            return "su";
        }
        if (physics.getVelocityY() > 0 && physics.getVelocityX() == 0) {
            return "giu";
        }
        return "fermo";
    }

    //FUNZIONI PER IL MOVIMENTO
    public void right() {
        physics.setVelocityX(velocity); // set velocity right
    }

    public void left() {
        physics.setVelocityX(-velocity); // set velocity right
    }

    public void up() {
        physics.setVelocityY(-velocity); // set velocity right
    }

    public void down() {
        physics.setVelocityY(velocity); // set velocity right
    }

    public void ferma() {
        physics.setVelocityX(0);
        physics.setVelocityY(0);
        texture.stop();
        isStopped = true;
    }

    public int attacca() {
        if (texture.getAnimationChannel() != animAttackDown || texture.getAnimationChannel() != animAttackSide || texture.getAnimationChannel() != animAttackUp) {
            switch (direzione) {
                case "destra":
                    texture.playAnimationChannel(animAttackSide);
                    texture.setScaleX(1);
                    texture.setOnCycleFinished(() -> {
                        texture.loopAnimationChannel(animRunSide);
                        texture.setScaleX(-1);
                        texture.stop();
                    });
                    break;
                case "sinistra":
                    texture.playAnimationChannel(animAttackSide);
                    texture.setScaleX(-1);
                    texture.setOnCycleFinished(() -> {
                        texture.loopAnimationChannel(animRunSide);
                        texture.setScaleX(1);
                        texture.stop();
                    });
                    break;
                case "su":
                    texture.playAnimationChannel(animAttackUp);
                    texture.setScaleX(1);
                    texture.setOnCycleFinished(() -> {
                        texture.loopAnimationChannel(animRunUp);
                        texture.stop();
                    });
                    break;
                case "giu":
                    texture.playAnimationChannel(animAttackDown);
                    texture.setOnCycleFinished(() -> {
                        texture.loopAnimationChannel(animRunDown);
                        texture.stop();
                    });
                    break;
            }
            return danno;
        }
        return 0;
    }
}