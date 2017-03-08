package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Alexander Caballero on 7/3/2017.
 */

public class Box2DScreen extends BaseScreen{
    public Box2DScreen(MainGame Game) {
        super(Game);
    }
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body minijoeBody, sueloBody, pinchoBody;
    private Fixture minijoeFixture,sueloFixture, pinchoFixture;

    @Override
    public void show() {
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera =new OrthographicCamera(7.11f, 4);
        camera.translate(0,1);

        minijoeBody=world.createBody(createJoeBodyDef());
        sueloBody=world.createBody(createSueloBodyDef());
        pinchoBody=world.createBody(createPinchoBodyDef(0.5f));

        //crear jugador
        PolygonShape minijoeShape= new PolygonShape();
        minijoeShape.setAsBox(0.5f,0.5f);
        minijoeFixture=minijoeBody.createFixture(minijoeShape,1);
        minijoeShape.dispose();

        //crear suelo
        PolygonShape sueloShape=new PolygonShape();
        sueloShape.setAsBox(500,1);
        sueloFixture = sueloBody.createFixture(sueloShape,1);
        sueloShape.dispose();

        //crear pincho
        pinchoFixture=createPinchoFixture(pinchoBody);

    }

    private BodyDef createPinchoBodyDef(float x) {
        BodyDef def= new BodyDef();
        def.position.set(x,0.5f);
        return def;
    }

    private BodyDef createSueloBodyDef() {
        BodyDef def= new BodyDef();
        def.position.set(0,-1);
        return def;
    }

    private BodyDef createJoeBodyDef() {
        BodyDef def= new BodyDef();
        def.position.set(0,10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private Fixture createPinchoFixture(Body pinchoBody){
        Vector2[] vertices=new Vector2[3];
        vertices[0]=new Vector2(-0.5f,-0.5f);
        vertices[1]=new Vector2(0.5f,-0.5f);
        vertices[2]=new Vector2(0,0.5f);

        PolygonShape shape=new PolygonShape();
        shape.set(vertices);
        Fixture fix=pinchoBody.createFixture(shape,1);
        shape.dispose();
        return fix;
    }

    @Override
    public void dispose() {
        sueloBody.destroyFixture(sueloFixture);
        minijoeBody.destroyFixture(minijoeFixture);
        pinchoBody.destroyFixture(pinchoFixture);
        world.destroyBody(sueloBody);
        world.destroyBody(minijoeBody);
        world.destroyBody(pinchoBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(delta, 6,2);
        camera.update();
        renderer.render(world, camera.combined);
    }
}
