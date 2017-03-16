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
 * Created by Alexander Caballero on 12/3/2017.
 */

public class SelectLevel extends BaseScreen {
    private Stage stage;
    private Skin skin;
    private Image imageLevel;

    private TextButton nivel1, nivel2;
    public SelectLevel(final MainGame Game) {
        super(Game);


        stage = new Stage(new FitViewport(640, 360));


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));


        nivel1 = new TextButton("Nivel 1(Facil)", skin);
        nivel2 = new TextButton("Nivel 2 (Difícil)", skin);


        imageLevel = new Image(Game.getManager().get("logoElegirNivel.png", Texture.class));

        nivel1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
              NextLevel.levelControl=1;
                Game.setScreen(Game.gameScreen);

            }
        });

        nivel2.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                NextLevel.levelControl=2;
                Game.setScreen(Game.gameScreenL2);
            }
        });


        imageLevel.setPosition(320 - imageLevel.getWidth() / 2, 320 - imageLevel.getHeight());
        nivel1.setSize(200, 80);
        nivel2.setSize(200, 80);
        nivel1.setPosition(60, 50);
        nivel2.setPosition(380, 50);


        stage.addActor(nivel1);
        stage.addActor(imageLevel);
        stage.addActor(nivel2);
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
        // Dispose assets.
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        // Just render things.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

}

