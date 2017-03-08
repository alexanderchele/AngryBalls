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
    private Body minijoeBody;
    private Fixture minijoeFixture;

    @Override
    public void show() {
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera =new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        BodyDef minijoeDef= createJoeBodyDef();
        minijoeBody=world.createBody(minijoeDef);

        PolygonShape minijoeShape= new PolygonShape();
        minijoeShape.setAsBox(1,1);
        minijoeFixture=minijoeBody.createFixture(minijoeShape,1);
        minijoeShape.dispose();

    }

    private BodyDef createJoeBodyDef() {
        BodyDef def= new BodyDef();
        def.position.set(0,10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    public void dispose() {
        world.destroyBody(minijoeBody);
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
