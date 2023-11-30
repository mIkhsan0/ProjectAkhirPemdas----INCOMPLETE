package com.mygdx.test001.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.test001.utils.SettingsScreenOverlay;

public class GameMainMenuScreen implements Screen {

    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector3 touchPosition;
    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private TextureAtlas backgroundMainMenuScreen;
    private Array<Sprite> iconUI;
    private Array<Sprite> background;
    private Animation<Sprite> backgroundAnimation;
    private Sprite backgroundRenderedFrames;
    private Sprite playMainMenuScreen;
    private Sprite playMainMenuScreenClicked;
    private Sprite quitMainMenuScreen;
    private Sprite quitMainMenuScreenClicked;
    private Sprite menuMainMenuScreen;
    private Sprite menuMainMenuScreenClicked;
    private Sprite SettingMainMenuScreen;
    private Sprite SettingMainMenuScreenClicked;
    private boolean menuMainMenuScreenClickedState;
    private boolean menuMainMenuScreenMenuOpenState;
    private boolean settingsScreenClickedState;
    private float stateTime;
    private GameScreenManager gameScreenManager;
    private SettingsScreenOverlay settingsScreenOverlay;

    public GameMainMenuScreen(GameScreenManager gameScreenManager) {
        this.gameScreenManager = gameScreenManager;
        batch = gameScreenManager.batch;
    }

    //Tried to implement method for Button Clicked but i have to set position ahead of time so the code looks kind of ugly so maybe not
    /*private boolean isButtonClicked(Sprite button, float touchPositionX, float touchPositionY) {
        float buttonLeftSide = button.getX();
        float buttonRightSide = buttonLeftSide + button.getWidth();
        float buttonBottomSide = button.getY();
        float buttonTopSide = buttonBottomSide + button.getHeight();

        return touchPositionX >= buttonLeftSide && touchPositionX <= buttonRightSide && touchPositionY >= buttonBottomSide && touchPositionY <= buttonTopSide;
    }*/

    @Override
    public void show() {

        //Test
        //gameScreenManager.setCurrentScreen(new GameRunScreen(gameScreenManager), false);

        //Important
        //batch = new SpriteBatch();

        //Camera and Stuff
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(1000, 480,camera);
        camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
        touchPosition = new Vector3();

        //Stuff necessary to put into game UI!!!
        textureAtlas = new TextureAtlas("UI.txt");
        backgroundMainMenuScreen = new TextureAtlas("BackgroundAnimation/BackgroundAnimation.txt");

        background = backgroundMainMenuScreen.createSprites();
        backgroundAnimation = new Animation<>(0.3f, background, Animation.PlayMode.LOOP);
        iconUI = textureAtlas.createSprites("Sprite sheet for Basic Pack");

        //Initialize Buttons
        playMainMenuScreen = iconUI.get(274);
        playMainMenuScreenClicked = iconUI.get(275);
        menuMainMenuScreen = iconUI.get(150);
        menuMainMenuScreenClicked = iconUI.get(213);
        menuMainMenuScreenClickedState = false;
        menuMainMenuScreenMenuOpenState = false;
        SettingMainMenuScreen = iconUI.get(76);
        SettingMainMenuScreenClicked = iconUI.get(77);
        quitMainMenuScreen = iconUI.get(211);

        //Other Necessary Stuff
        settingsScreenOverlay = new SettingsScreenOverlay(gameScreenManager);

        //Others
        stateTime = 0.0f;

    }

    @Override
    public void render(float delta) {

        stateTime += delta;

        //Clear Screem
        ScreenUtils.clear(0,0,0,0);

        //camera.position.set();
        touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPosition);
        viewport.apply();
        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        backgroundRenderedFrames = backgroundAnimation.getKeyFrame(stateTime, true);

        batch.begin();

        batch.draw(backgroundRenderedFrames, 0, 0);

        //Play Button Render ### NOTE SCALE THE SIZE MAKE IT BIGGER
        if(Gdx.input.isTouched() && touchPosition.x >= 200 && touchPosition.x < 200 + playMainMenuScreen.getWidth()
                && touchPosition.y >= 45 && touchPosition.y < 45 + playMainMenuScreen.getHeight()) {
            batch.draw(playMainMenuScreenClicked, 200, 45);
            gameScreenManager.setCurrentScreen(new GameRunScreen(gameScreenManager, iconUI, touchPosition), false);
        }
        else
            batch.draw(playMainMenuScreen, 200, 45);

        //Menu Button Render
        if(Gdx.input.justTouched() && touchPosition.x >= 300 && touchPosition.x < 300 + menuMainMenuScreen.getWidth()
                && touchPosition.y >= 45 && touchPosition.y < 45 + menuMainMenuScreen.getHeight()) {
            batch.draw(menuMainMenuScreenClicked, 300, 45);
            if (!menuMainMenuScreenClickedState) {
                menuMainMenuScreenClickedState = true;
                menuMainMenuScreenMenuOpenState = true;
            }
            else {
                menuMainMenuScreenClickedState = false;
                menuMainMenuScreenMenuOpenState = false;
            }
        }
        else if (!menuMainMenuScreenClickedState)
            batch.draw(menuMainMenuScreen, 300, 45);
        else
            batch.draw(menuMainMenuScreenClicked, 300, 45);

        //Other Button Slowly Appear
        //NOTE IMPLEMENT LATER

        //Quit Button Render
        if(menuMainMenuScreenMenuOpenState && Gdx.input.isTouched() && touchPosition.x >= 360 && touchPosition.x < 360 + quitMainMenuScreen.getWidth()
                && touchPosition.y >= 45 && touchPosition.y < 45 + quitMainMenuScreen.getHeight()) {
            batch.draw(quitMainMenuScreen, 360, 45); //Jump To Are You Sure Window/Screen. IMPLEMENT LATER

            Gdx.app.exit();
        }
        else if (menuMainMenuScreenMenuOpenState)
            batch.draw(quitMainMenuScreen, 360, 45);

        //Settings Button Render
        if(menuMainMenuScreenMenuOpenState && Gdx.input.isTouched()
                && touchPosition.x >= 330 && touchPosition.x < 330 + SettingMainMenuScreen.getWidth()
                && touchPosition.y >= 45 && touchPosition.y < 45 + SettingMainMenuScreen.getHeight()) {
            batch.draw(SettingMainMenuScreenClicked, 330, 45); //Jump To GameSettingsScreen. IMPLEMENT LATER
            if (Gdx.input.justTouched())
                settingsScreenClickedState = !settingsScreenClickedState;
        }
        else if (menuMainMenuScreenMenuOpenState)
            batch.draw(SettingMainMenuScreen, 330, 45);
        if (settingsScreenClickedState)
            settingsScreenOverlay.renderSettingsScreen();

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
        //batch.dispose(); Stay Until Remove Game Screen Manager

        //Dispose TextureAtlas
        backgroundMainMenuScreen.dispose();
        textureAtlas.dispose();
    }
}
