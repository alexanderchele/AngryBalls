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

public class NextLevel extends BaseScreen {
    private Stage stage;
    private Skin skin;
    private Image image;
    public static int levelControl =1;
    private TextButton next,retry,menu;
    public NextLevel(final MainGame Game) {
        super(Game);


        stage = new Stage(new FitViewport(640, 360));


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        next = new TextButton("Siguiente Nivel", skin);
        retry = new TextButton("Jugar de nuevo", skin);
        menu = new TextButton("Menu", skin);


        image = new Image(Game.getManager().get("gameover.png", Texture.class));

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                levelControl=1;
                Game.setScreen(Game.gameScreen);
            }
        });

        menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Game.setScreen(Game.menuScreen);
            }
        });

        next.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                levelControl=2;
                Game.setScreen(Game.gameScreenL2);

            }
        });


        image.setPosition(320 - image.getWidth() / 2, 320 - image.getHeight());
        retry.setSize(150, 50);
        menu.setSize(150, 50);
        next.setSize(150, 50);
        next.setPosition(50, 50);
        retry.setPosition(250, 50);
        menu.setPosition(450, 50);


        stage.addActor(retry);
        stage.addActor(image);
        stage.addActor(menu);
        stage.addActor(next);
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
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }


}
