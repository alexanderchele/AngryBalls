package com.jumpdontdie;

import com.badlogic.gdx.Screen;

/**
 * Created by Alexander Caballero on 4/3/2017.
 */

public abstract class BaseScreen implements Screen {
protected MainGame Game;

    public BaseScreen(MainGame Game){
        this.Game=Game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
