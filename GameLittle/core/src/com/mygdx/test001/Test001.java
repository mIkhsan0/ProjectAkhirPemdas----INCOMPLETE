package com.mygdx.test001;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.test001.screens.GameMainMenuScreen;
import com.mygdx.test001.screens.GameRunScreen;
import com.mygdx.test001.screens.GameScreenManager;

public class Test001 extends Game {

	@Override
	public void create () {
		GameScreenManager gameScreenManager = new  GameScreenManager(this);
		gameScreenManager.setCurrentScreen(new GameMainMenuScreen(gameScreenManager), false);
	}

	@Override
	public void render () {
		super.render(); //Render Current Screen
	}
	
	@Override
	public void dispose () {

	}
}
