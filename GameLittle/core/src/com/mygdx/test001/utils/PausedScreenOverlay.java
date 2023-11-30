package com.mygdx.test001.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.test001.screens.GameScreenManager;

public class PausedScreenOverlay {
    private GameScreenManager gameScreenManager;
    private SpriteBatch batch;
    private Texture pausedScreen;
    private Texture homeButton;
    private Texture homeButtonPressed;
    private OrthographicCamera camera;
    private Texture blackBackground;
    private Vector3 touchPosition;
    public PausedScreenOverlay(GameScreenManager gameScreenManager, OrthographicCamera camera, Vector3 touchPosition) {
        this.gameScreenManager = gameScreenManager;
        batch = gameScreenManager.batch;
        this.camera = camera;
        this.touchPosition = touchPosition;

        //Texture Initialize
        pausedScreen = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_229.png");;
        homeButton = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_80.png");;
        homeButtonPressed = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_81.png");;
        blackBackground = new Texture("BackgroundPause.png");

    }

    public void renderPausedScreen() {
        batch.setColor(0, 0, 0, 0.5f);
        batch.draw(blackBackground, camera.position.x - camera.viewportWidth, camera.position.y - camera.viewportHeight);
        batch.setColor(Color.WHITE);
        batch.draw(pausedScreen, camera.position.x - pausedScreen.getWidth(), camera.position.y - pausedScreen.getHeight(), 200, 100);
        if (Gdx.input.isTouched() && touchPosition.x > camera.position.x && touchPosition.x < camera.position.x + homeButton.getWidth()
                && touchPosition.y > camera.position.y && touchPosition.y < camera.position.y + homeButton.getHeight()) {
            batch.draw(homeButtonPressed, camera.position.x, camera.position.y);
            gameScreenManager.getPreviousScreen();
        }
        else
            batch.draw(homeButton, camera.position.x, camera.position.y);
    }
}
