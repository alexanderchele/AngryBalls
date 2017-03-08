package com.jumpdontdie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jumpdontdie.actors.ActorJugador;
import com.jumpdontdie.actors.ActorPinchos;

/**
 * Created by Alexander Caballero on 5/3/2017.
 */

public class MainGameScreen extends BaseScreen {
    public MainGameScreen(MainGame Game) {
        super(Game);
        texturaJugador=new Texture("bola1.png");
        texturaPinchos=new Texture("spike.png");
        regionPinchos=new TextureRegion(texturaPinchos,0,64,128,64);
    }

    private Stage stage;
    private ActorJugador jugador;
    private ActorPinchos pinchos;
    private Texture texturaJugador, texturaPinchos;
    private TextureRegion regionPinchos;
    @Override
    public void show() {


    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        texturaJugador.dispose();
    }

    @Override
    public void render(float delta) {

    }


    }

