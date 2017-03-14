package com.jumpdontdie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {
	private AssetManager manager;
	//registrar pantallas
	public BaseScreen loadingScreen, menuScreen, gameScreen, gameOverScreen, playerScreen, nextLevel, gameScreenL2,selectLevel,endGameScreen;


	@Override
	public void create() {
		manager = new AssetManager();
		manager.load("floor.png", Texture.class);
		manager.load("overfloor.png", Texture.class);
		manager.load("spike.png", Texture.class);
		manager.load("bola1.png", Texture.class);
		manager.load("bola2.png", Texture.class);
		manager.load("bola3.png", Texture.class);
		manager.load("bandera.png", Texture.class);
		manager.load("logo.png", Texture.class);
		manager.load("die.ogg", Sound.class);
		manager.load("jump.ogg", Sound.class);
		manager.load("song.ogg", Music.class);
		manager.load("gameover.png", Texture.class);
		//manager.finishLoading();

		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}

	public void finishLoading() {
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		gameOverScreen = new GameOverScreen(this);
		playerScreen=new PlayerScreen(this);
		nextLevel=new NextLevel(this);
		gameScreenL2=new GameScreenL2(this);
		selectLevel=new SelectLevel(this);
		endGameScreen=new EndGameScreen(this);
		setScreen(menuScreen);
	}

	public AssetManager getManager() {
		return manager;
	}

}

