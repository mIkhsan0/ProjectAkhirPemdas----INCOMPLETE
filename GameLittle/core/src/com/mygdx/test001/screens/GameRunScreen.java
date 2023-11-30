package com.mygdx.test001.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.test001.utils.*;

public class GameRunScreen implements Screen {
    private GameScreenManager gameScreenManager;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector3 touchPosition;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private ShapeRenderer shapeRenderer;
    private TiledMap tiledMap;
    private MapObjects objectOceanBorderMap;
    private MapObjects objectNaturesMap;
    private MapObjects objectDecorationMap;
    private MapObjects borderWall;
    private MapObjects borderMultiple;
    private MapObjects borderFence;
    private MapObjects borderHill1;
    private MapObjects borderHill2;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Sprite player;
    private PlayerAnimation playerAnimation;
    private PlayerMovement playerMovement;
    private PlayerPhysicsMovement playerPhysicsMovement;
    private Body playerWithPhysics;
    private ObjectsMapRenderer objectsMapRenderer;
    private Array<Sprite> iconUI;
    private OverlayGameRunScreen overlayGameRunScreen;
    private PausedScreenOverlay pausedScreenOverlay;
    private PlayerTools playerAction;
    private TileMapChangeLogic changeTile;
    private boolean isPaused;
    private boolean inAnimation;
    private boolean movementStop;
    private boolean inAction;
    private float inActionState;

    public GameRunScreen(GameScreenManager gameScreenManager, Array<Sprite> iconUI, Vector3 touchPosition) {
        this.gameScreenManager = gameScreenManager;
        batch = gameScreenManager.batch;
        this.iconUI = iconUI;
        this.touchPosition = touchPosition;
        inAnimation = true;
        movementStop = false;
        inAction = false;
        inActionState = 0.0f;
    }

    @Override
    public void show() {

        //Map
        tiledMap = new TmxMapLoader().load("Map/GameMap001.tmx");
        objectOceanBorderMap = tiledMap.getLayers().get("Ocean Border").getObjects();
        objectNaturesMap = tiledMap.getLayers().get("Natures").getObjects();
        objectDecorationMap = tiledMap.getLayers().get("Decoration").getObjects();
        borderWall = tiledMap.getLayers().get("Wall1").getObjects();
        borderMultiple = tiledMap.getLayers().get("MultipleBorder").getObjects();
        borderFence = tiledMap.getLayers().get("Fence1").getObjects();
        borderHill1 = tiledMap.getLayers().get("Hill1").getObjects();
        borderHill2 = tiledMap.getLayers().get("Hill2").getObjects();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //World Initialize && Physics
        world = new World(new Vector2(0.0f, 0.0f),false);
        box2DDebugRenderer = new Box2DDebugRenderer();
        WorldPhysics worldBorderPhysics = new WorldPhysics(world);
        worldBorderPhysics.bodyAndFixture(objectOceanBorderMap);
        worldBorderPhysics.bodyAndFixture(borderWall);
        worldBorderPhysics.bodyAndFixture(borderFence);
        worldBorderPhysics.bodyAndFixture(borderHill1);
        worldBorderPhysics.bodyAndFixture(borderHill2);
        worldBorderPhysics.bodyAndFixture(borderMultiple);
        shapeRenderer = new ShapeRenderer();
        objectsMapRenderer = new ObjectsMapRenderer(world, batch);

        //Camera
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800 * 0.5f, 480 * 0.5f, camera);
        camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());

        //Tools and Buttons
        overlayGameRunScreen = new OverlayGameRunScreen(gameScreenManager, iconUI, camera, touchPosition);

        //Overlays
        pausedScreenOverlay = new PausedScreenOverlay(gameScreenManager, camera, touchPosition);
        isPaused = false;

        //Player
        playerAnimation = new  PlayerAnimation();
        //player = playerAnimation.getPlayerAnimationTest(0, 0);
        //playerMovement = new  PlayerMovement(player, playerAnimation, batch);
        playerWithPhysics = new PlayerPhysics(world).getBody();
        playerPhysicsMovement = new PlayerPhysicsMovement(playerWithPhysics, playerAnimation, batch);
        playerAction = new PlayerTools(overlayGameRunScreen, gameScreenManager.batch, playerWithPhysics, playerPhysicsMovement, playerAnimation);

        //ChangeLogic
        changeTile = new TileMapChangeLogic(tiledMap, camera);

    }

    @Override
    public void render(float delta) {

        //Clear Screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //Important
        viewport.apply();
        //camera.position.set(player.getX(),player.getY(),0);
        camera.position.set(playerWithPhysics.getPosition().x, playerWithPhysics.getPosition().y, 0);
        camera.update();
        touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPosition);
        mapRenderer.setView(camera);
        batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            isPaused = !isPaused;
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && touchPosition.x > camera.position.x - 20 && touchPosition.x < camera.position.x + 20
                && touchPosition.y > camera.position.y - 20 && touchPosition.y < camera.position.y + 20) {
            inAction = true;
            movementStop = true;
            if (overlayGameRunScreen.isCurrentlyHoldingHoe())
                changeTile.changeCurrentTile();
        }
        if (overlayGameRunScreen.isCurrentlyHoldingEmpty() || overlayGameRunScreen.isCurrentlyHoldingPickaxe() || overlayGameRunScreen.isCurrentlyHoldingFishingRod())
            movementStop = false;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.end();

        //Render Map
        mapRenderer.render();

        batch.begin();

        objectsMapRenderer.renderObjects(objectNaturesMap);
        objectsMapRenderer.renderObjects(objectDecorationMap);

        //PlayerMovement Logic Begin
        //playerMovement.playerMovementLogicRender(delta);
        if (!inAction || !movementStop)
            playerPhysicsMovement.playerPhysicsMovementLogicRender(delta);
        else {
            playerWithPhysics.setLinearVelocity(0, 0);
            playerAction.playerToolsActionRender(delta);
            inActionState += delta;
            if (inActionState >= 0.4f) {
                inActionState = 0.0f;
                inAction = false;
                movementStop = false;
            }
        }
        // PlayerMovement Logic END


        overlayGameRunScreen.toolsAndButtonRender();
        overlayGameRunScreen.healthAndGoldOverlay();

        if (isPaused)
            pausedScreenOverlay.renderPausedScreen();

        batch.end();

        //World Render
        world.step(1/60f,6,2);
        //box2DDebugRenderer.render(world, camera.combined);
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
        world.dispose();
        box2DDebugRenderer.dispose();
        shapeRenderer.dispose();
        tiledMap.dispose();
        mapRenderer.dispose();
    }
}
