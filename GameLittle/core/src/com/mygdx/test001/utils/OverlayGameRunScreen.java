package com.mygdx.test001.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.test001.screens.GameScreenManager;

public class OverlayGameRunScreen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture toolSelectBottomLeft;
    private Texture toolSelectBottomRight;
    private Texture toolSelectTopRight;
    private Texture toolSelectTopLeft;
    private Texture toolEmpty;
    private Texture toolEmptyPressed;
    private Texture toolHoe;
    private Texture toolHoePressed;
    private Texture toolPickaxe;
    private Texture toolPickaxePressed;
    private Texture toolAxe;
    private Texture toolAxePressed;
    private Texture toolWateringCan;
    private Texture toolWateringCanPressed;
    private Texture toolFishingRod;
    private Texture toolFishingRodPressed;
    private Vector3 touchPosition;
    private boolean buttonToolIsClicked;
    private boolean currentlyHoldingEmpty;
    private boolean currentlyHoldingHoe;
    private boolean currentlyHoldingPickaxe;
    private boolean currentlyHoldingAxe;
    private boolean currentlyHoldingWateringCan;
    private boolean currentlyHoldingFishingRod;
    private Texture healthAndGold;
    private Texture healthFull;
    private Texture healthHalfFull;
    private Texture healthEmpty;

    public OverlayGameRunScreen(GameScreenManager gameScreenManager, Array<Sprite> iconUI, OrthographicCamera camera, Vector3 touchPosition) {
        batch = gameScreenManager.batch;
        this.camera = camera;

        /*toolSelectBottomLeft = iconUI.get(158);
        toolSelectBottomRight = iconUI.get(159);
        toolSelectTopRight = iconUI.get(193);
        toolSelectTopLeft = iconUI.get(192);
        toolEmpty = iconUI.get(15);
        toolEmptyPressed = iconUI.get(18);
        toolHoe = iconUI.get(16);
        toolHoePressed = iconUI.get(19);
        toolWateringCan = iconUI.get(17);
        toolWateringCanPressed = iconUI.get(20);
        toolAxe = iconUI.get(41);
        toolAxePressed = iconUI.get(44);
        toolPickaxe = iconUI.get(42);
        toolPickaxePressed = iconUI.get(45);
        toolFishingRod = iconUI.get(43);
        toolFishingRodPressed = iconUI.get(46);
        this.touchPosition = touchPosition;
        healthAndGold = iconUI.get(206);
        healthFull = iconUI.get(140);
        healthHalfFull = iconUI.get(141);
        healthEmpty = iconUI.get(142);*/

        toolSelectBottomLeft = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_158.png");
        toolSelectBottomRight = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_159.png");
        toolSelectTopRight = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_193.png");
        toolSelectTopLeft = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_192.png");
        toolEmpty = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_15.png");
        toolEmptyPressed = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_18.png");
        toolHoe = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_16.png");
        toolHoePressed = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_19.png");
        toolWateringCan = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_17.png");
        toolWateringCanPressed = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_20.png");
        toolAxe = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_41.png");
        toolAxePressed = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_44.png");
        toolPickaxe = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_42.png");
        toolPickaxePressed = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_45.png");
        toolFishingRod = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_43.png");
        toolFishingRodPressed = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_46.png");
        this.touchPosition = touchPosition;
        healthAndGold = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_206.png");
        healthFull = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_140.png");
        healthHalfFull = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_141.png");
        healthEmpty = new Texture("NecessaryBetterImage/SproutLands/Sprite sheet for Basic Pack_142.png");

        buttonToolIsClicked = false;
        setToolFalse();
        currentlyHoldingEmpty = true;
    }

    public void toolsAndButtonRender() {
        /*touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPosition);*/

        batch.draw(toolEmpty, camera.position.x - 240, camera.position.y - 110);

        if (buttonToolIsClicked) {
            //Empty
            if (currentlyHoldingEmpty)
                batch.draw(toolEmptyPressed, camera.position.x - 240, camera.position.y - 90, 19, 18);
            else
                batch.draw(toolEmpty, camera.position.x - 240, camera.position.y - 90);
            //Hoe
            if (currentlyHoldingHoe)
                batch.draw(toolHoePressed, camera.position.x - 240, camera.position.y - 70, 19, 18);
            else
                batch.draw(toolHoe, camera.position.x - 240, camera.position.y - 70);
            //Watering Can
            if (currentlyHoldingWateringCan)
                batch.draw(toolWateringCanPressed, camera.position.x - 240, camera.position.y - 50, 19, 18);
            else
                batch.draw(toolWateringCan, camera.position.x - 240, camera.position.y - 50);
            //Axe
            if (currentlyHoldingAxe)
                batch.draw(toolAxePressed, camera.position.x - 240, camera.position.y - 30, 19, 18);
            else
                batch.draw(toolAxe, camera.position.x - 240, camera.position.y - 30);
            //Pickaxe
            if (currentlyHoldingPickaxe)
                batch.draw(toolPickaxePressed, camera.position.x - 240, camera.position.y - 10, 19, 18);
            else
                batch.draw(toolPickaxe, camera.position.x - 240, camera.position.y - 10);
            //Fishing Rod
            if (currentlyHoldingFishingRod)
                batch.draw(toolFishingRodPressed, camera.position.x - 240, camera.position.y + 10, 19, 18);
            else
                batch.draw(toolFishingRod, camera.position.x - 240, camera.position.y + 10);
        }

        if (Gdx.input.justTouched() && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 110 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 110) {
            buttonToolIsClicked = !buttonToolIsClicked;
        }
        if (buttonToolIsClicked && Gdx.input.justTouched() && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 90 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 90) {
            buttonToolIsClicked = !buttonToolIsClicked;
            setToolFalse();
            currentlyHoldingEmpty = true;
        }
        if (buttonToolIsClicked && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 90 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 90) {
           batch.draw(toolSelectBottomLeft, camera.position.x - 243, camera.position.y - 93, 7, 7);
           batch.draw(toolSelectBottomRight, camera.position.x - 225, camera.position.y - 93, 7, 7);
           batch.draw(toolSelectTopRight, camera.position.x - 225, camera.position.y - 75, 7, 7);
           batch.draw(toolSelectTopLeft, camera.position.x - 243, camera.position.y - 75, 7, 7);
        }
        if (buttonToolIsClicked && Gdx.input.justTouched() && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 70 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 70) {
            buttonToolIsClicked = !buttonToolIsClicked;
            setToolFalse();
            currentlyHoldingHoe = true;
        }
        if (buttonToolIsClicked && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 70 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 70) {
            batch.draw(toolSelectBottomLeft, camera.position.x - 243, camera.position.y - 73, 7, 7);
            batch.draw(toolSelectBottomRight, camera.position.x - 225, camera.position.y - 73, 7, 7);
            batch.draw(toolSelectTopRight, camera.position.x - 225, camera.position.y - 55, 7, 7);
            batch.draw(toolSelectTopLeft, camera.position.x - 243, camera.position.y - 55, 7, 7);
        }
        if (buttonToolIsClicked && Gdx.input.justTouched() && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 50 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 50) {
            buttonToolIsClicked = !buttonToolIsClicked;
            setToolFalse();
            currentlyHoldingWateringCan = true;
        }
        if (buttonToolIsClicked && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 50 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 50) {
            batch.draw(toolSelectBottomLeft, camera.position.x - 243, camera.position.y - 53, 7, 7);
            batch.draw(toolSelectBottomRight, camera.position.x - 225, camera.position.y - 53, 7, 7);
            batch.draw(toolSelectTopRight, camera.position.x - 225, camera.position.y - 35, 7, 7);
            batch.draw(toolSelectTopLeft, camera.position.x - 243, camera.position.y - 35, 7, 7);
        }
        if (buttonToolIsClicked && Gdx.input.justTouched() && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 30 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 30) {
            buttonToolIsClicked = !buttonToolIsClicked;
            setToolFalse();
            currentlyHoldingAxe = true;
        }
        if (buttonToolIsClicked && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 30 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 30) {
            batch.draw(toolSelectBottomLeft, camera.position.x - 243, camera.position.y - 33, 7, 7);
            batch.draw(toolSelectBottomRight, camera.position.x - 225, camera.position.y - 33, 7, 7);
            batch.draw(toolSelectTopRight, camera.position.x - 225, camera.position.y - 15, 7, 7);
            batch.draw(toolSelectTopLeft, camera.position.x - 243, camera.position.y - 15, 7, 7);
        }
        if (buttonToolIsClicked && Gdx.input.justTouched() && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 10 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 10) {
            buttonToolIsClicked = !buttonToolIsClicked;
            setToolFalse();
            currentlyHoldingPickaxe = true;
        }
        if (buttonToolIsClicked && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y - 10 && touchPosition.y < camera.position.y + toolEmpty.getHeight() - 10) {
            batch.draw(toolSelectBottomLeft, camera.position.x - 243, camera.position.y - 13, 7, 7);
            batch.draw(toolSelectBottomRight, camera.position.x - 225, camera.position.y - 13, 7, 7);
            batch.draw(toolSelectTopRight, camera.position.x - 225, camera.position.y + 5, 7, 7);
            batch.draw(toolSelectTopLeft, camera.position.x - 243, camera.position.y + 5, 7, 7);
        }
        if (buttonToolIsClicked && Gdx.input.justTouched() && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y + 10 && touchPosition.y < camera.position.y + toolEmpty.getHeight() + 10) {
            buttonToolIsClicked = !buttonToolIsClicked;
            setToolFalse();
            currentlyHoldingFishingRod = true;
        }
        if (buttonToolIsClicked && touchPosition.x > camera.position.x - 240 && touchPosition.x < camera.position.x + toolEmpty.getWidth() - 240
                && touchPosition.y > camera.position.y + 10 && touchPosition.y < camera.position.y + toolEmpty.getHeight() + 10) {
            batch.draw(toolSelectBottomLeft, camera.position.x - 243, camera.position.y + 7, 7, 7);
            batch.draw(toolSelectBottomRight, camera.position.x - 225, camera.position.y + 7, 7, 7);
            batch.draw(toolSelectTopRight, camera.position.x - 225, camera.position.y + 25, 7, 7);
            batch.draw(toolSelectTopLeft, camera.position.x - 243, camera.position.y + 25, 7, 7);
        }

    }

    public void healthAndGoldOverlay() {
        batch.draw(healthAndGold, camera.position.x + camera.viewportWidth - healthAndGold.getWidth()*8 - 7, camera.position.y + camera.viewportHeight - healthAndGold.getHeight()*4);
    }
    public void setToolFalse() {
        currentlyHoldingEmpty = false;
        currentlyHoldingHoe = false;
        currentlyHoldingPickaxe = false;
        currentlyHoldingAxe = false;
        currentlyHoldingWateringCan = false;
        currentlyHoldingFishingRod = false;
    }

    public boolean isCurrentlyHoldingEmpty() {
        return currentlyHoldingEmpty;
    }

    public boolean isCurrentlyHoldingHoe() {
        return currentlyHoldingHoe;
    }

    public boolean isCurrentlyHoldingPickaxe() {
        return currentlyHoldingPickaxe;
    }

    public boolean isCurrentlyHoldingAxe() {
        return currentlyHoldingAxe;
    }

    public boolean isCurrentlyHoldingWateringCan() {
        return currentlyHoldingWateringCan;
    }

    public boolean isCurrentlyHoldingFishingRod() {
        return currentlyHoldingFishingRod;
    }
}
