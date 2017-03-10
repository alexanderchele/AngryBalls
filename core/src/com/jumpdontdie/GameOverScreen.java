package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Alexander Caballero on 10/3/2017.
 */

public class GameOverScreen extends BaseScreen{
    private Stage stage;
    private Skin skin;
    private Image gameover;
    private TextButton retry;
    public GameOverScreen(final MainGame Game) {
        super(Game);
        stage= new Stage(new FitViewport(640,360));
        skin=new Skin(Gdx.files.internal("skin/uiskin.json"));
        gameover=new Image(Game.getManager().get("gameover.png",Texture.class));
        retry=new TextButton("Jugar de nuevo",skin);

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setScreen(Game.gameScreen);
            }
        });


        gameover.setPosition(320-gameover.getWidth()/2,320-gameover.getHeight());
        retry.setSize(200,100);
        retry.setPosition(220,50);
        stage.addActor(retry);
        stage.addActor(gameover);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
