package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jumpdontdie.entities.EntityFactory;
import com.jumpdontdie.entities.FloorEntity;
import com.jumpdontdie.entities.PlayerEntity;
import com.jumpdontdie.entities.SpikeEntity;

import java.util.ArrayList;
import java.util.List;

import static com.jumpdontdie.LoadingScreen.e2;
import static com.jumpdontdie.LoadingScreen.r2;

/**
 * Created by Alexander Caballero on 12/3/2017.
 */

public class GameScreenL2 extends BaseScreen {


    private Stage stage;


    private World world;


    private PlayerEntity player;


    private List<FloorEntity> floorList = new ArrayList<FloorEntity>();


    private List<SpikeEntity> spikeList = new ArrayList<SpikeEntity>();


    private Sound jumpSound;

    private Image bandera;


    private Sound dieSound;


    private Music backgroundMusic;

    private Vector3 position;
    private float duracion;
    private Skin skin;
    public Label score,record;


    public static float posicionX;

    public GameScreenL2(MainGame Game) {
        super(Game);


        stage = new Stage(new FitViewport(640, 360));
        position = new Vector3(stage.getCamera().position);


        world = new World(new Vector2(0, -10), true);
        world.setContactListener(new GameScreenL2.GameContactListener());


        jumpSound = Game.getManager().get("jump.ogg");
        dieSound = Game.getManager().get("die.ogg");
        backgroundMusic = Game.getManager().get("song.ogg");
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        bandera = new Image(Game.getManager().get("bandera.png", Texture.class));
        bandera.setPosition(4300,10);
        bandera.setSize(150,150);
        stage.addActor(bandera);

        score = new Label("Score: ", skin);
        score.setPosition(0, 320);

        record = new Label("Record: ", skin);
        record.setPosition(0, 301);
        stage.addActor(record);
        stage.addActor(score);

    }


    @Override
    public void show() {


        EntityFactory factory = new EntityFactory(Game.getManager());





        floorList.add(factory.createFloor(world, 0, 1000, 1));

        floorList.add(factory.createFloor(world, 0, 12, 2));
        floorList.add(factory.createFloor(world, 0, 8, 3));

        spikeList.add(factory.createSpikes(world, 9, 2));
        spikeList.add(factory.createSpikes(world, 10, 2));
        spikeList.add(factory.createSpikes(world, 13, 1));
        spikeList.add(factory.createSpikes(world, 14, 1));

        floorList.add(factory.createFloor(world, 15, 8, 2));

        spikeList.add(factory.createSpikes(world, 24, 1));
        spikeList.add(factory.createSpikes(world, 27, 1));
        spikeList.add(factory.createSpikes(world, 30, 1));
        spikeList.add(factory.createSpikes(world, 33, 1));
        spikeList.add(factory.createSpikes(world, 38, 1));

        spikeList.add(factory.createSpikes(world, 45, 1));
        spikeList.add(factory.createSpikes(world, 46, 1));
        spikeList.add(factory.createSpikes(world, 53, 1));
        spikeList.add(factory.createSpikes(world, 54, 1));
        spikeList.add(factory.createSpikes(world, 61, 1));
        spikeList.add(factory.createSpikes(world, 62, 1));
        spikeList.add(factory.createSpikes(world, 69, 1));
        spikeList.add(factory.createSpikes(world, 70, 1));

        floorList.add(factory.createFloor(world, 73, 20, 2));
        spikeList.add(factory.createSpikes(world, 76, 2));
        floorList.add(factory.createFloor(world, 77, 4, 3));
        spikeList.add(factory.createSpikes(world, 82, 2));
       spikeList.add(factory.createSpikes(world, 86, 2));
        spikeList.add(factory.createSpikes(world, 92, 2));

        spikeList.add(factory.createSpikes(world, 120, 2));
        spikeList.add(factory.createSpikes(world, 125, 2));
        spikeList.add(factory.createSpikes(world, 130, 2));

        floorList.add(factory.createFloor(world, 100, 10, 2));
        floorList.add(factory.createFloor(world, 100, 10, 3));
        floorList.add(factory.createFloor(world, 100, 10, 4));
        floorList.add(factory.createFloor(world, 100, 10, 5));

        for (FloorEntity floor : floorList)
            stage.addActor(floor);
        for (SpikeEntity spike : spikeList)
            stage.addActor(spike);


        player = factory.createPlayer(world, new Vector2(3.5f,3.5f));
        stage.addActor(player);



        stage.getCamera().position.set(position);
        stage.getCamera().update();



        backgroundMusic.setVolume(0.75f);
        backgroundMusic.play();
    }


    @Override
    public void hide() {


        player.detach();
        for (FloorEntity floor : floorList)
            floor.detach();
        for (SpikeEntity spike : spikeList)
            spike.detach();



        floorList.clear();
        spikeList.clear();


    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0.5f , 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();


        world.step(delta, 6, 2);


        posicionX=player.getX();
        if (player.getX() > 150 && player.isAlive() && player.getX()<4500) {
            float speed = Constants.PLAYER_SPEED * delta * Constants.PIXELS_IN_METER;
            stage.getCamera().translate(speed, 0, 0);
        }

        if(player.isAlive()) {
            r2 = (int) player.getX() / 2;
            if(r2 >= e2){
                e2=r2;
            }
            score.setText("Score: " + r2 + " m de "+2222+" m");
            score.setX(player.getX() - 100);

            record.setText("Record: " + e2);
            record.setX(player.getX() - 100);


        }


        PlayerEntity.Animacion(delta);




        stage.draw();
    }


    @Override
    public void dispose() {

        stage.dispose();


        world.dispose();
    }


    private class GameContactListener implements ContactListener {

        private boolean areCollided(Contact contact, Object userA, Object userB) {
            Object userDataA = contact.getFixtureA().getUserData();
            Object userDataB = contact.getFixtureB().getUserData();

            if (userDataA == null || userDataB == null) {
                return false;
            }


            return (userDataA.equals(userA) && userDataB.equals(userB)) ||
                    (userDataA.equals(userB) && userDataB.equals(userA));
        }


        @Override
        public void beginContact(Contact contact) {

            if (areCollided(contact, "player", "floor")) {
                player.setJumping(false);


                if (Gdx.input.isTouched()) {
                    jumpSound.play();


                    player.setMustJump(true);
                }
            }


            if (areCollided(contact, "player", "spike")) {


                if (player.isAlive()) {
                    player.setAlive(false);


                    backgroundMusic.stop();
                    dieSound.play();


                    stage.addAction(
                            Actions.sequence(
                                    Actions.delay(1.5f),
                                    Actions.run(new Runnable() {

                                        @Override
                                        public void run() {
                                            if(player.getX()<4300) {

                                                Game.setScreen(Game.gameOverScreen);
                                            }
                                            else
                                                Game.setScreen(Game.endGameScreen);
                                        }
                                    })
                            )
                    );

                }
            }
        }


        @Override
        public void endContact(Contact contact) {

            if (areCollided(contact, "player", "floor")) {
                if (player.isAlive()) {
                    jumpSound.play();
                }
            }
        }


        @Override public void preSolve(Contact contact, Manifold oldManifold) { }
        @Override public void postSolve(Contact contact, ContactImpulse impulse) { }
    }
}
