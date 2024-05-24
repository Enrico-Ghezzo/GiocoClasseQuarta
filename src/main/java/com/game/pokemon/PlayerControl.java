package com.game.pokemon;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;

import java.util.ArrayList;

public class PlayerControl extends Component {
    private PhysicsComponent physics;
    private AnimationChannel animIdle, animRunSide, animRunUp, animRunDown;
    private AnimatedTexture texture;
    private double playerScale = 2.5f;
    private float velocity = 200;

    public PlayerControl() {
        ArrayList runFrames = new ArrayList<>();
        runFrames.add(FXGL.image("matteolongobardi.png"));
        animIdle = new AnimationChannel(runFrames, Duration.seconds(2));
        texture = new AnimatedTexture(animIdle);
        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsalaterale/corsalaterale" + i + ".png"));
        }
        animRunSide = new AnimationChannel(runFrames, Duration.seconds(0.5));

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsasu/corsasu" + i + ".png"));
        }
        animRunUp = new AnimationChannel(runFrames, Duration.seconds(0.5));

        runFrames.clear();
        for (int i = 1; i <= 6; i++) {
            runFrames.add(FXGL.image("corsagiu/corsagiu" + i + ".png"));
        }
        animRunDown = new AnimationChannel(runFrames, Duration.seconds(0.5));
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture); //mette la texture
    }

    public void right(){
        physics.setVelocityX(velocity); // set velocity right
        if(!(texture.getAnimationChannel() == animRunSide)){
            texture.loopAnimationChannel(animRunSide);
        }

        texture.setScaleX(-playerScale);
    }

    public void left(){
        physics.setVelocityX(-velocity); // set velocity right
        if(!(texture.getAnimationChannel() == animRunSide)){
            texture.loopAnimationChannel(animRunSide);
        }
        texture.setScaleX(playerScale);
    }

    public void up(){
        physics.setVelocityY(-velocity); // set velocity right
        if(!(texture.getAnimationChannel() == animRunUp)){
            texture.loopAnimationChannel(animRunUp);
        }
    }

    public void down(){
        physics.setVelocityY(velocity); // set velocity right
        if(!(texture.getAnimationChannel() == animRunDown)){
            texture.loopAnimationChannel(animRunDown);
        }
    }

    public void ferma(){
        physics.setVelocityX(0);
        physics.setVelocityY(0);
        texture.loopAnimationChannel(animIdle);
    }
}
