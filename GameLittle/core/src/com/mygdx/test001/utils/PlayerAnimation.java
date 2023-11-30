package com.mygdx.test001.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayerAnimation {

    /*private TextureAtlas playerLooksSample;
    private Array<Sprite> playerLooks;
    private Sprite[][] playerAnimationTest*/
    private Animation<TextureRegion> playerAnimationDown;
    private Animation<TextureRegion> playerAnimationDownIdle;
    private Animation<TextureRegion> playerAnimationUp;
    private Animation<TextureRegion> playerAnimationUpIdle;
    private Animation<TextureRegion> playerAnimationLeft;
    private Animation<TextureRegion> playerAnimationLeftIdle;
    private Animation<TextureRegion> playerAnimationRight;
    private Animation<TextureRegion> playerAnimationRightIdle;
    private int column;
    private int row;
    private Texture playerAnimationList;
    private TextureRegion[][] playerAnimationListArray;

    public PlayerAnimation() {

        playerAnimationList = new Texture("Entities/PlayerAnimation/PlayerAnimationTEST.png");
        playerAnimationListArray = TextureRegion.split(playerAnimationList, 48, 48);

        playerAnimationDown = new Animation<>(0.2f, playerAnimationListArray[0]);
        playerAnimationUp = new Animation<>(0.2f, playerAnimationListArray[1]);
        playerAnimationLeft = new Animation<>(0.2f, playerAnimationListArray[2]);
        playerAnimationRight = new Animation<>(0.2f, playerAnimationListArray[3]);


        /*playerLooksSample = new TextureAtlas("Entities/PlayerAnimation/PlayerAnimation.txt");
        playerLooks = playerLooksSample.createSprites();
        playerAnimationTest = new Sprite[4][4];

        column = 0;
        row = 0;
        for (Sprite playerImage : playerLooks) {
            playerAnimationTest[row][column] = playerImage;
            ++column;
            if (column >= 4) {
                column = 0;
                ++row;
                if (row >= 4)
                    row = 0;
            }
        }
        playerAnimationDown = new Animation<>(0.2f, playerAnimationTest[0]);
        playerAnimationUp = new Animation<>(0.2f, playerAnimationTest[1]);
        playerAnimationLeft = new Animation<>(0.2f, playerAnimationTest[2]);
        playerAnimationRight = new Animation<>(0.2f, playerAnimationTest[3]);*/

        /*for (Sprite playerImage : playerLooks) {
            playerAnimationTest[row][column] = playerImage;
            ++column;
            if (column >= 2) {
                column = 0;
                ++row;
                if (row >= 8)
                    row = 0;
            }
        }
        playerAnimationDownIdle = new Animation<>(0.2f, playerAnimationTest[0]);
        playerAnimationDown = new Animation<>(0.2f, playerAnimationTest[1]);
        playerAnimationUpIdle = new Animation<>(0.2f, playerAnimationTest[2]);
        playerAnimationUp = new Animation<>(0.2f, playerAnimationTest[3]);
        playerAnimationLeftIdle = new Animation<>(0.2f, playerAnimationTest[4]);
        playerAnimationLeft = new Animation<>(0.2f, playerAnimationTest[5]);
        playerAnimationRightIdle = new Animation<>(0.2f, playerAnimationTest[6]);
        playerAnimationRight = new Animation<>(0.2f, playerAnimationTest[7]);*/
    }

    public TextureRegion playerAnimationListArray(int row, int column) {
        return playerAnimationListArray[row][column];
    }

    public Animation<TextureRegion> getPlayerAnimationDown() {
        return playerAnimationDown;
    }

    public Animation<TextureRegion> getPlayerAnimationUp() {
        return playerAnimationUp;
    }

    public Animation<TextureRegion> getPlayerAnimationLeft() {
        return playerAnimationLeft;
    }

    public Animation<TextureRegion> getPlayerAnimationRight() {
        return playerAnimationRight;
    }
}
