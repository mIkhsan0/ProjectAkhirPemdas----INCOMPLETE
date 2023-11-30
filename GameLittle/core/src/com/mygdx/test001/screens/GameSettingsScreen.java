package com.mygdx.test001.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameSettingsScreen implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private TextureAtlas gameSettingsScreen;
    private Array<Sprite> settingsBox;
    private Sprite settingBox;

    public GameSettingsScreen(GameScreenManager gameScreenManager, OrthographicCamera camera) {
        this.camera = camera;
        batch = gameScreenManager.batch;
    }

    @Override
    public void show() {
        //Camera and Stuff
        viewport = new ExtendViewport(1000, 480,camera);
        camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());

        //Settings
        gameSettingsScreen = new TextureAtlas("Settings Menu/Settings.txt");
        settingsBox = gameSettingsScreen.createSprites();

        //Initialize Settings
        settingBox = settingsBox.get(0);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);

        //Camera Set
        viewport.apply();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //Test
        batch.begin();
        batch.draw(settingBox, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
