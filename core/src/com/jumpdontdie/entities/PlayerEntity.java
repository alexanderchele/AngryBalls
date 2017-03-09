package com.jumpdontdie.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Alexander Caballero on 9/3/2017.
 */

public class PlayerEntity extends Actor {
    private Texture texture;
    private World world;
    private Body body;
    private Fixture  fixture;
    private boolean alive=true, jumping=false;

    public PlayerEntity(World world, Texture texture, Vector2 position){
        this.world=world;
        this.texture=texture;
        
        BodyDef def= new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body=world.createBody(def);

        PolygonShape box= new PolygonShape();
        box.setAsBox(0.5f,0.5f);
        fixture=body.createFixture(box,1);
        box.dispose();
    }
}
