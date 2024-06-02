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

/**
 * Classe per creare le entità
 */

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
                .with(new VitaComponent(100))
                .with(new BarraVitaComponent())
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
                .with(new VitaComponent(200))
                .with(new BarraVitaComponent())
                .build();
    }

    @Spawns("lapras")
    public Entity newLapras(SpawnData data) {
        var physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().density(0).friction(1.0f).restitution(0.05f));
        physics.setBodyType(BodyType.KINEMATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.LAPRAS)
                .bbox(new HitBox(BoundingShape.box(43*1.5,34*1.5)))
                .anchorFromCenter()
                .with(physics) //eliminare per togliere fisica
                .with(new CollidableComponent(true))
                .with(new LaprasControl())
                .with(new VitaComponent(200))
                .with(new BarraVitaComponent())
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
                .build();
    }

    @Spawns("mancino")
    public Entity newMancino(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.MANCINO)
                .bbox(new HitBox(BoundingShape.box(16*1.5, 19*1.5)))
                .view(FXGL.texture("mancino.png", 16*1.5, 19*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("biral")
    public Entity newBiral(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.BIRAL)
                .bbox(new HitBox(BoundingShape.box(16*1.5, 20*1.5)))
                .view(FXGL.texture("biral.png", 16*1.5, 20*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("distefano")
    public Entity newDistefano(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.DISTEFANO)
                .bbox(new HitBox(BoundingShape.box(16*1.5, 20*1.5)))
                .view(FXGL.texture("distefano.png", 16*1.5, 20*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("funes")
    public Entity newFunes(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.FUNES)
                .bbox(new HitBox(BoundingShape.box(16*1.5, 19*1.5)))
                .view(FXGL.texture("funes.png", 16*1.5, 19*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("pagan")
    public Entity newPagan(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.PAGAN)
                .bbox(new HitBox(BoundingShape.box(14*1.5, 20*1.5)))
                .view(FXGL.texture("pagan.png", 14*1.5, 20*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("penzo")
    public Entity newPenzo(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.PENZO)
                .bbox(new HitBox(BoundingShape.box(16*1.5, 20*1.5)))
                .view(FXGL.texture("penzo.png", 16*1.5, 20*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("pesenti")
    public Entity newPesenti(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.PESENTI)
                .bbox(new HitBox(BoundingShape.box(14*1.5, 20*1.5)))
                .view(FXGL.texture("pesenti.png", 14*1.5, 20*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("sandi")
    public Entity newSandi(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SANDI)
                .bbox(new HitBox(BoundingShape.box(16*1.5, 20*1.5)))
                .view(FXGL.texture("sandi.png", 16*1.5, 20*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("steve")
    public Entity newSteve(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.STEVE)
                .bbox(new HitBox(BoundingShape.box(16*1.5, 20*1.5)))
                .view(FXGL.texture("steve.png", 16*1.5, 20*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("zuccon")
    public Entity newZuccon(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.ZUCCON)
                .bbox(new HitBox(BoundingShape.box(16*1.5, 20*1.5)))
                .view(FXGL.texture("zuccon.png", 16*1.5, 20*1.5))
                .with(physics)
                .with(new CollidableComponent(true))
                .build();
    }

    //####################################################################################

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

    //###################################################################################
    @Spawns("ring")
    public Entity newRing(SpawnData data) {
        return new EntityBuilder()
                .bbox(BoundingShape.circle((int)data.get("width")/2))
                .type(PokemonTypes.RINGGROUDON)
                .build();
    }

    @Spawns("ringLapras")
    public Entity newRingLapras(SpawnData data) {
        return new EntityBuilder()
                .bbox(BoundingShape.circle((int)data.get("width")/2))
                .type(PokemonTypes.RINGLAPRAS)
                .build();
    }

    //###################################################################################
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

    @Spawns("spawnMancino")
    public Entity newSpawnMancino(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNMANCINO)
                .build();
    }

    @Spawns("spawnBiral")
    public Entity newSpawnBiral(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNBIRAL)
                .build();
    }

    @Spawns("spawnDistefano")
    public Entity newSpawnDistefano(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNDISTEFANO)
                .build();
    }

    @Spawns("spawnFunes")
    public Entity newSpawnFunes(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNFUNES)
                .build();
    }

    @Spawns("spawnPagan")
    public Entity newSpawnPagan(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNPAGAN)
                .build();
    }

    @Spawns("spawnPenzo")
    public Entity newSpawnPenzo(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNPENZO)
                .build();
    }

    @Spawns("spawnPesenti")
    public Entity newSpawnPesenti(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNPESENTI)
                .build();
    }

    @Spawns("spawnSandi")
    public Entity newSpawnSandi(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNSANDI)
                .build();
    }

    @Spawns("spawnSteve")
    public Entity newSpawnSteve(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNSTEVE)
                .build();
    }

    @Spawns("spawnZuccon")
    public Entity newSpawnZuccon(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNZUCCON)
                .build();
    }

    @Spawns("spawnGroudon")
    public Entity newSpawnGroudon(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNGROUDON)
                .build();
    }

    @Spawns("spawnLapras")
    public Entity newSpawnLapras(SpawnData data) {
        return new EntityBuilder()
                .at(data.getX(), data.getY())
                .type(PokemonTypes.SPAWNLAPRAS)
                .build();
    }
}
