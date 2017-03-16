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
 * Created by Alexander Caballero on 11/3/2017.
 */

public class PlayerScreen extends BaseScreen {
    private Stage stage;
    private Skin skin;
    private Image bolaFubol,bolaBaseball,bolaBillar, imageJugador;
    public static int controlPlayer=1;
    private TextButton botonBola1,botonBola2,botonBola3;
    public PlayerScreen(final MainGame Game) {
        super(Game);


        stage = new Stage(new FitViewport(640, 360));


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));


        botonBola1 = new TextButton("Bola de fúbol", skin);
        botonBola2 = new TextButton("Bola de baseball", skin);
        botonBola3 = new TextButton("Bola de billar", skin);

        bolaFubol = new Image(Game.getManager().get("bola1.png", Texture.class));
        bolaBaseball = new Image(Game.getManager().get("bola2.png", Texture.class));
        bolaBillar = new Image(Game.getManager().get("bola3.png", Texture.class));
        imageJugador = new Image(Game.getManager().get("logoSelecionarJugador.png", Texture.class));

        botonBola1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                controlPlayer=1;
                Game.setScreen(Game.menuScreen);
            }
        });

        botonBola2.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controlPlayer=2;
                Game.setScreen(Game.menuScreen);
            }
        });

        botonBola3.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                controlPlayer=3;
                Game.setScreen(Game.menuScreen);
            }
        });


        bolaFubol.setPosition(70, 120);
        bolaBaseball.setPosition(270, 120);
        bolaBillar.setPosition(470, 120);
        bolaBillar.setSize(90,90);
        bolaBaseball.setSize(90,90);
        bolaFubol.setSize(90,90);

        botonBola1.setSize(150, 50);
        botonBola2.setSize(150, 50);
        botonBola3.setSize(150, 50);
        botonBola1.setPosition(50, 50);
        botonBola2.setPosition(250, 50);
        botonBola3.setPosition(450, 50);
        imageJugador.setPosition(320 - imageJugador.getWidth() / 2, 240);

        stage.addActor(bolaFubol);
        stage.addActor(bolaBaseball);
        stage.addActor(bolaBillar);
        stage.addActor(imageJugador);

        stage.addActor(botonBola1);
        stage.addActor(botonBola2);
        stage.addActor(botonBola3);
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

