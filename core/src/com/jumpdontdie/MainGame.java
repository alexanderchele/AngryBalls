package com.jumpdontdie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {
	private AssetManager manager;
//registrar pantallas
	public GameScreen gameScreen;
	public GameOverScreen gameOverScreen;

	public AssetManager getManager(){
		return manager;
	}
	@Override
	public void create() {
		manager=new AssetManager();
		manager.load("floor.png", Texture.class);
		manager.load("overfloor.png", Texture.class);
		manager.load("spike.png", Texture.class);
		manager.load("bola1.png", Texture.class);
		manager.load("die.ogg", Sound.class);
		manager.load("jump.ogg", Sound.class);
		manager.load("song.ogg", Music.class);
		manager.load("gameover.png", Texture.class);
		manager.finishLoading();

		gameScreen=new GameScreen(this);
		gameOverScreen=new GameOverScreen(this);
		setScreen(gameOverScreen);
	}
}
