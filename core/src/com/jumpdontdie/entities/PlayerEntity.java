package com.jumpdontdie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jumpdontdie.Constants;

/**
 * Created by Alexander Caballero on 9/3/2017.
 */

public class PlayerEntity extends Actor {


    private Texture texture;


    private World world;


    private Body body;


    private Fixture fixture;


    private boolean alive = true;



    private boolean jumping = false;


    private boolean mustJump = false;

    public TextureRegion[] dudeFrames;
    public Animation dudeAnimation;

    public PlayerEntity(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;



        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);


        CircleShape box = new CircleShape();
        box.setRadius(0.5f);
        fixture = body.createFixture(box, 3);
        fixture.setUserData("player");
        box.dispose();


        setSize(Constants.PIXELS_IN_METER, Constants.PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        setPosition((body.getPosition().x - 0.5f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 0.5f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {

        if (Gdx.input.justTouched()) {
            jump();
        }


        if (mustJump) {
            mustJump = false;
            jump();
        }


        if (alive) {

            float speedY = body.getLinearVelocity().y;
            body.setLinearVelocity(Constants.PLAYER_SPEED, speedY);
        }


        if (jumping) {
            body.applyForceToCenter(0, -Constants.IMPULSE_JUMP * 1.15f, true);
        }
    }

    public void jump() {

        if (!jumping && alive) {
            jumping = true;


            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, Constants.IMPULSE_JUMP, position.x, position.y, true);
        }
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }



    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }

    public static void Animacion(float delta) {


    }
}
