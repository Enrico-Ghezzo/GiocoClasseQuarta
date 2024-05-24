package com.game.megaman2;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.AnimatedTexture;
import javafx.scene.Node;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;

public class FabbricaEntita implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        var physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().density(6.5f).friction(1.0f).restitution(0.05f));
        physics.setBodyType(BodyType.DYNAMIC);

        return new EntityBuilder()
                //.at(((AnimatedTexture)data.get("texture")).getWidth() * 2, getAppHeight()-((AnimatedTexture)data.get("texture")).getHeight()*2)
                .at(data.getX(), data.getY())
                .scale(data.get("playerScale"), data.get("playerScale"))
                .viewWithBBox((Node)data.get("texture"))//mettere view per togliere fisica
                .anchorFromCenter()
                .collidable()
                .with(physics) //eliminare per togliere fisica
                .build();
    }
}
