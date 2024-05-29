package com.game.pokemon;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

public class FabbricaEntita implements EntityFactory {

    //DEFINISCE L'ENTITA' DEL GIOCATORE
    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        var physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().density(0).friction(1.0f).restitution(0.05f));
        physics.setBodyType(BodyType.DYNAMIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.PLAYER)
                .bbox(new HitBox(BoundingShape.box(14*1.5,21*1.5)))
                .anchorFromCenter()
                .with(physics) //eliminare per togliere fisica
                .with(new CollidableComponent(true))
                .with(new PlayerControl())
                .with(new BarraVitaComponent())
                .with(new VitaComponent(100))
                .build();
    }

    @Spawns("groudon")
    public Entity newGroudon(SpawnData data) {
        var physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().density(0).friction(1.0f).restitution(0.05f));
        physics.setBodyType(BodyType.KINEMATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.GROUDON)
                .bbox(new HitBox(BoundingShape.box(40*1.5,53*1.5)))
                .anchorFromCenter()
                .with(physics) //eliminare per togliere fisica
                .with(new CollidableComponent(true))
                .with(new GroudonControl())
                .with(new BarraVitaComponent())
                .with(new VitaComponent(100))
                .build();
    }

    @Spawns("saffi")
    public Entity newSaffi(SpawnData data) {
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SAFFI)
                .bbox(new HitBox(BoundingShape.box(14*1.5, 21*1.5)))
                .view(FXGL.texture("safficorsagiu1.png", 14*1.5, 21*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new VitaComponent(100))
                .with(new BarraVitaComponent())
                .build();
    }

    //DEFINISCE LE COLLISIONI DELLA MAPPA
    @Spawns("oggettoSolido")
    public Entity newOggettoSolido(SpawnData data) {    //per il collisionHandler guarda: https://www.youtube.com/watch?v=37wfF9GW1vQ&t=1457s
        return new EntityBuilder()
                .from(data)
                .type(PokemonTypes.OGGETTOSOLIDO)
                .bbox(new HitBox(BoundingShape.box(((int)data.get("width")), ((int)data.get("height")))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("ring")
    public Entity newRing(SpawnData data) {
        return new EntityBuilder()
                .bbox(BoundingShape.circle((int)data.get("width")/2))
                .type(PokemonTypes.RINGGROUDON)
                .build();
    }

    @Spawns("spawnPoint")
    public Entity newSpawn(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNPOINT)
                .build();
    }

    @Spawns("spawnSaffi")
    public Entity newSpawnSaffi(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNSAFFI)
                .build();
    }

    @Spawns("spawnGroudon")
    public Entity newSpawnGroudon(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNGROUDON)
                .build();
    }
}
