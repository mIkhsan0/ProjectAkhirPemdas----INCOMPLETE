package com.mygdx.test001.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

public class PlayerTools {
    private OverlayGameRunScreen overlayGameRunScreen;
    private Texture playerAction;
    private TextureRegion[][] playerActionSampleSource;
    private Animation<TextureRegion> playerActionAnimationHoeDown;
    private Animation<TextureRegion> playerActionAnimationHoeUp;
    private Animation<TextureRegion> playerActionAnimationHoeLeft;
    private Animation<TextureRegion> playerActionAnimationHoeRight;
    private Animation<TextureRegion> playerActionAnimationAxeDown;
    private Animation<TextureRegion> playerActionAnimationAxeUp;
    private Animation<TextureRegion> playerActionAnimationAxeLeft;
    private Animation<TextureRegion> playerActionAnimationAxeRight;
    private Animation<TextureRegion> playerActionAnimationWateringCanDown;
    private Animation<TextureRegion> playerActionAnimationWateringCanUp;
    private Animation<TextureRegion> playerActionAnimationWateringCanLeft;
    private Animation<TextureRegion> playerActionAnimationWateringCanRight;
    private SpriteBatch batch;
    private Body player;
    private float playerState;
    private PlayerPhysicsMovement playerFace;
    private PlayerAnimation playerAnimation;
    public PlayerTools(OverlayGameRunScreen overlayGameRunScreen, SpriteBatch batch, Body player, PlayerPhysicsMovement playerFace, PlayerAnimation playerAnimation) {
        this.overlayGameRunScreen = overlayGameRunScreen;
        this.batch = batch;
        this.player = player;
        this.playerFace = playerFace;
        this.playerAnimation = playerAnimation;
        playerAction = new Texture("Entities/PlayerActionAnimation/PlayerAction.png");
        playerActionSampleSource = TextureRegion.split(playerAction, 48, 48);

        playerActionAnimationHoeDown = new Animation<>(0.2f, playerActionSampleSource[0]);
        playerActionAnimationHoeUp = new Animation<>(0.2f, playerActionSampleSource[1]);
        playerActionAnimationHoeLeft = new Animation<>(0.2f, playerActionSampleSource[2]);
        playerActionAnimationHoeRight = new Animation<>(0.2f, playerActionSampleSource[3]);
        playerActionAnimationAxeDown = new Animation<>(0.2f, playerActionSampleSource[4]);
        playerActionAnimationAxeUp = new Animation<>(0.2f, playerActionSampleSource[5]);
        playerActionAnimationAxeLeft = new Animation<>(0.2f, playerActionSampleSource[6]);
        playerActionAnimationAxeRight = new Animation<>(0.2f, playerActionSampleSource[7]);
        playerActionAnimationWateringCanDown = new Animation<>(0.2f, playerActionSampleSource[8]);
        playerActionAnimationWateringCanUp = new Animation<>(0.2f, playerActionSampleSource[9]);
        playerActionAnimationWateringCanLeft = new Animation<>(0.2f, playerActionSampleSource[10]);
        playerActionAnimationWateringCanRight = new Animation<>(0.2f, playerActionSampleSource[11]);
    }

    public void playerToolsActionRender(float delta) {

        //Implemented
        if (overlayGameRunScreen.isCurrentlyHoldingHoe()) {
            checkFace(delta, playerActionAnimationHoeDown, playerActionAnimationHoeUp, playerActionAnimationHoeLeft, playerActionAnimationHoeRight);
        }
        if (overlayGameRunScreen.isCurrentlyHoldingAxe()) {
            checkFace(delta, playerActionAnimationAxeDown, playerActionAnimationAxeUp, playerActionAnimationAxeLeft, playerActionAnimationAxeRight);
        }
        if (overlayGameRunScreen.isCurrentlyHoldingWateringCan()) {
            checkFace(delta, playerActionAnimationWateringCanDown, playerActionAnimationWateringCanUp, playerActionAnimationWateringCanLeft, playerActionAnimationWateringCanRight);
        }

        //Not Implemented
        if (overlayGameRunScreen.isCurrentlyHoldingEmpty()) {
            checkFace(0, playerAnimation.getPlayerAnimationDown(), playerAnimation.getPlayerAnimationUp(), playerAnimation.getPlayerAnimationLeft(), playerAnimation.getPlayerAnimationRight());
        }
        if (overlayGameRunScreen.isCurrentlyHoldingPickaxe()) {
            checkFace(0, playerAnimation.getPlayerAnimationDown(), playerAnimation.getPlayerAnimationUp(), playerAnimation.getPlayerAnimationLeft(), playerAnimation.getPlayerAnimationRight());
        }
        if (overlayGameRunScreen.isCurrentlyHoldingFishingRod()) {
            checkFace(0, playerAnimation.getPlayerAnimationDown(), playerAnimation.getPlayerAnimationUp(), playerAnimation.getPlayerAnimationLeft(), playerAnimation.getPlayerAnimationRight());
        }

    }

    private void checkFace(float delta, Animation<TextureRegion> playerActionAnimationDown, Animation<TextureRegion> playerActionAnimationUp, Animation<TextureRegion> playerActionAnimationLeft, Animation<TextureRegion> playerActionAnimationRight) {
        if (playerFace.isFaceDown()) {
            batch.draw(playerActionAnimationDown.getKeyFrame(playerState, true), player.getPosition().x - (float) playerActionAnimationDown.getKeyFrame(playerState, true).getRegionWidth() / 2 - 2, player.getPosition().y - (float) playerActionAnimationDown.getKeyFrame(playerState, true).getRegionHeight() / 2);
            playerState += delta;
            if (playerState >= 0.4f) {
                playerState = 0.0f;
            }
        }
        else if (playerFace.isFaceUp()) {
            batch.draw(playerActionAnimationUp.getKeyFrame(playerState, true), player.getPosition().x - (float) playerActionAnimationDown.getKeyFrame(playerState, true).getRegionWidth() / 2 - 2, player.getPosition().y - (float) playerActionAnimationDown.getKeyFrame(playerState, true).getRegionHeight() / 2);
            playerState += delta;
            if (playerState >= 0.4f) {
                playerState = 0.0f;
            }
        }
        else if (playerFace.isFaceLeft()) {
            batch.draw(playerActionAnimationLeft.getKeyFrame(playerState, true), player.getPosition().x - (float) playerActionAnimationDown.getKeyFrame(playerState, true).getRegionWidth() / 2, player.getPosition().y - (float) playerActionAnimationDown.getKeyFrame(playerState, true).getRegionHeight() / 2);
            playerState += delta;
            if (playerState >= 0.4f) {
                playerState = 0.0f;
            }
        }
        else if (playerFace.isFaceRight()) {
            batch.draw(playerActionAnimationRight.getKeyFrame(playerState, true), player.getPosition().x - (float) playerActionAnimationDown.getKeyFrame(playerState, true).getRegionWidth() / 2, player.getPosition().y - (float) playerActionAnimationDown.getKeyFrame(playerState, true).getRegionHeight() / 2);
            playerState += delta;
            if (playerState >= 0.4f) {
                playerState = 0.0f;
            }
        }
    }
}
