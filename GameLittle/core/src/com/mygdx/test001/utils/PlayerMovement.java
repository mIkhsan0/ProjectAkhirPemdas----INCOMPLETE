package com.mygdx.test001.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerMovement {
    private SpriteBatch spriteBatch;
    private Sprite player;
    private PlayerAnimation playerAnimation;
    private float playerWalkingSpeed;
    private float playerRunningSpeed;
    private float playerState;
    private boolean keyPressedWS;
    private boolean keyPressedAD;
    public PlayerMovement(Sprite player, PlayerAnimation playerAnimation, SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        this.player = player;
        this.playerAnimation = playerAnimation;
        player.setPosition(600, 780);
        playerWalkingSpeed = 30;
        playerRunningSpeed = 80;
        playerState = 0;
    }

    public void playerMovementLogicRender(float delta) {
        //Movement
        if (!(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)))
            keyPressedWS = false;
        if (!(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)))
            keyPressedAD = false;

        if ((!keyPressedAD && !keyPressedWS) || (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) || (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D)))
            spriteBatch.draw(player, player.getX(), player.getY());

        //Animation Movement and Check Keys
        if (((Gdx.input.isKeyPressed(Input.Keys.S)) && !(Gdx.input.isKeyPressed(Input.Keys.W))) || (!(Gdx.input.isKeyPressed(Input.Keys.S)) && (Gdx.input.isKeyPressed(Input.Keys.W)))) {
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (!keyPressedAD) {
                    spriteBatch.draw(playerAnimation.getPlayerAnimationDown().getKeyFrame(playerState, true), player.getX(), player.getY());
                    keyPressedWS = true;
                }
                player.setY(player.getY() - (playerWalkingSpeed * delta));
                /*if (!collisionDetectionPolygon())
                    player.setY(player.getY() + (playerWalkingSpeed * delta));*/
                playerState += delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (!keyPressedAD) {
                    spriteBatch.draw(playerAnimation.getPlayerAnimationUp().getKeyFrame(playerState, true), player.getX(), player.getY());
                    keyPressedWS = true;
                }
                player.setY(player.getY() + (playerWalkingSpeed * delta));
                /*if (!collisionDetectionPolygon())
                    player.setY(player.getY() - (playerWalkingSpeed * delta));*/
                playerState += delta;
            }
        }
        if (((Gdx.input.isKeyPressed(Input.Keys.A)) && !(Gdx.input.isKeyPressed(Input.Keys.D))) || (!(Gdx.input.isKeyPressed(Input.Keys.A)) && (Gdx.input.isKeyPressed(Input.Keys.D)))) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (!keyPressedWS) {
                    spriteBatch.draw(playerAnimation.getPlayerAnimationLeft().getKeyFrame(playerState, true), player.getX(), player.getY());
                    keyPressedAD = true;
                }
                player.setX(player.getX() - (playerWalkingSpeed * delta));
                /*if (!collisionDetectionPolygon())
                    player.setX(player.getX() + (playerWalkingSpeed * delta));*/
                playerState += delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (!keyPressedWS) {
                    spriteBatch.draw(playerAnimation.getPlayerAnimationRight().getKeyFrame(playerState, true), player.getX(), player.getY());
                    keyPressedAD = true;
                }
                player.setX(player.getX() + (playerWalkingSpeed * delta));
                /*if ((collisionDetectionPolygon()))
                    player.setX(player.getX() - (playerWalkingSpeed * delta));*/
                playerState += delta;
            }
        }
    }
}
