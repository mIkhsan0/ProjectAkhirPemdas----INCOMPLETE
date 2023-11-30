package com.mygdx.test001.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.mygdx.test001.screens.GameScreenManager;

public class SettingsScreenOverlay {
    SpriteBatch batch;
    private TextureAtlas gameSettingsScreen;
    private Array<Sprite> settingsBox;
    int settingsPositionX;
    public SettingsScreenOverlay(GameScreenManager gameScreenManager) {
        batch = gameScreenManager.batch;

        //Settings
        gameSettingsScreen = new TextureAtlas("Settings Menu/Settings.txt");
        settingsBox = gameSettingsScreen.createSprites();
        settingsPositionX = 450;
    }

    public void renderSettingsScreen() {
        batch.draw(settingsBox.get(0), settingsPositionX, 0);
        batch.draw(settingsBox.get(1), settingsPositionX + settingsBox.get(0).getWidth(), 0);
    }
}
