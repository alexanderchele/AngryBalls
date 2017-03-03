package com.jumpdontdie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends ApplicationAdapter {
	private Texture balon;
	private SpriteBatch batch;
	@Override
	public void create() {
		balon = new Texture("bola1.png");
		batch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		balon.dispose();
		batch.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  		batch.begin();
		batch.draw(balon,200,200);
		batch.end();
	}
}
