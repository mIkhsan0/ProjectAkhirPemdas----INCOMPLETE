package com.mygdx.test001.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class PlayerPhysicsMovement {
    private Body playerPhysics;
    private PlayerAnimation playerAnimation;
    private SpriteBatch spriteBatch;
    private float playerWalkingSpeedX;
    private float playerWalkingSpeedY;
    private float playerRunningSpeed;
    private float playerState;
    private boolean keyPressedWS;
    private boolean keyPressedAD;
    private float faceState;
    private boolean faceDown;
    private boolean faceUp;
    private boolean faceLeft;
    private boolean faceRight;
    public PlayerPhysicsMovement(Body playerPhysics, PlayerAnimation playerAnimation, SpriteBatch spriteBatch) {
        this.playerPhysics = playerPhysics;
        this.playerAnimation = playerAnimation;
        this.spriteBatch = spriteBatch;
        playerRunningSpeed = 80;
        playerLastState();
        faceDown = true;
        faceState = 0.0f;
    }
    public void playerPhysicsMovementLogicRender(float delta) {
        //Movement
        if (!(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S))) {
            keyPressedWS = false;
        }
        if (!(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D))) {
            keyPressedAD = false;
        }

        if ((!keyPressedAD && !keyPressedWS) || (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) || (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D))) {
            if (faceDown) {
                spriteBatch.draw(playerAnimation.getPlayerAnimationDown().getKeyFrame(faceState, true), playerPhysics.getPosition().x - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionWidth() /2 - 2, playerPhysics.getPosition().y - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionHeight() /2);
                faceState += delta/4;
                if (faceState >= 0.2f) {
                    faceState = 0.0f;
                }
            }
            if (faceUp) {
                spriteBatch.draw(playerAnimation.getPlayerAnimationUp().getKeyFrame(faceState, true), playerPhysics.getPosition().x - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionWidth() / 2 - 2, playerPhysics.getPosition().y - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionHeight() / 2);
                faceState += delta/4;
                if (faceState >= 0.2f) {
                    faceState = 0.0f;
                }
            }
            if (faceLeft) {
                spriteBatch.draw(playerAnimation.getPlayerAnimationLeft().getKeyFrame(faceState, true), playerPhysics.getPosition().x - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionWidth() / 2, playerPhysics.getPosition().y - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionHeight() / 2);
                faceState += delta/4;
                if (faceState >= 0.2f) {
                    faceState = 0.0f;
                }
            }
            if (faceRight) {
                spriteBatch.draw(playerAnimation.getPlayerAnimationRight().getKeyFrame(faceState, true), playerPhysics.getPosition().x - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionWidth() / 2, playerPhysics.getPosition().y - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionHeight() / 2);
                faceState += delta/4;
                if (faceState >= 0.2f) {
                    faceState = 0.0f;
                }
            }
            playerPhysics.setLinearVelocity(0, 0);
        }

        //Animation Movement and Check Keys
        if (((Gdx.input.isKeyPressed(Input.Keys.S)) && !(Gdx.input.isKeyPressed(Input.Keys.W))) || (!(Gdx.input.isKeyPressed(Input.Keys.S)) && (Gdx.input.isKeyPressed(Input.Keys.W)))) {
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (!keyPressedAD) {
                    spriteBatch.draw(playerAnimation.getPlayerAnimationDown().getKeyFrame(playerState, true), playerPhysics.getPosition().x - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionWidth() /2 - 2, playerPhysics.getPosition().y - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionHeight() /2);
                    keyPressedWS = true;
                }
                playerWalk();
                playerLastState();
                faceDown = true;
                playerState += delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (!keyPressedAD) {
                    spriteBatch.draw(playerAnimation.getPlayerAnimationUp().getKeyFrame(playerState, true), playerPhysics.getPosition().x - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionWidth() /2 - 2, playerPhysics.getPosition().y - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionHeight() /2);
                    keyPressedWS = true;
                }
                playerWalk();
                playerLastState();
                faceUp = true;
                playerState += delta;
            }
        }
        if (((Gdx.input.isKeyPressed(Input.Keys.A)) && !(Gdx.input.isKeyPressed(Input.Keys.D))) || (!(Gdx.input.isKeyPressed(Input.Keys.A)) && (Gdx.input.isKeyPressed(Input.Keys.D)))) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (!keyPressedWS) {
                    spriteBatch.draw(playerAnimation.getPlayerAnimationLeft().getKeyFrame(playerState, true), playerPhysics.getPosition().x - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionWidth() /2, playerPhysics.getPosition().y - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionHeight() /2);
                    keyPressedAD = true;
                }
                playerWalk();
                playerLastState();
                faceLeft = true;
                playerState += delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (!keyPressedWS) {
                    spriteBatch.draw(playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true), playerPhysics.getPosition().x - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionWidth() /2, playerPhysics.getPosition().y - (float) playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true).getRegionHeight() /2);
                    keyPressedAD = true;
                }
                playerWalk();
                playerLastState();
                faceRight = true;
                playerState += delta;
            }
        }
    }
    public void playerWalk() {
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.A))
                playerWalkingSpeedX = -30;
            if (Gdx.input.isKeyPressed(Input.Keys.D))
                playerWalkingSpeedX = 30;
        } else playerWalkingSpeedX = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.W))
                playerWalkingSpeedY = 30;
            if (Gdx.input.isKeyPressed(Input.Keys.S))
                playerWalkingSpeedY = -30;
        } else playerWalkingSpeedY = 0;

        if (playerWalkingSpeedX > playerRunningSpeed)
            playerWalkingSpeedX = 80;
        if (playerWalkingSpeedY > playerRunningSpeed)
            playerWalkingSpeedY = 80;

        playerPhysics.setLinearVelocity(playerWalkingSpeedX, playerWalkingSpeedY);
    }

    public void playerLastState() {
        faceDown = false;
        faceUp = false;
        faceLeft = false;
        faceRight = false;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public boolean isFaceLeft() {
        return faceLeft;
    }

    public boolean isFaceRight() {
        return faceRight;
    }
}
