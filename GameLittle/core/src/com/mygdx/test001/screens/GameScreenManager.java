package com.mygdx.test001.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.test001.Test001;

import java.util.Stack;

public class GameScreenManager {
    private Test001 mainProcess;
    private Stack<Screen> screenStack;
    private Screen previousScreen;
    private Screen currentScreen;
    public SpriteBatch batch;

    public GameScreenManager(Test001 mainProcess) {
        this.mainProcess = mainProcess;
        screenStack = new Stack<>();
        batch = new SpriteBatch();
    }

    public void getPreviousScreen() {
        if (!screenStack.isEmpty()) {
            currentScreen = screenStack.pop();
            if (!screenStack.isEmpty()) {
                previousScreen = screenStack.peek();
                mainProcess.setScreen(previousScreen);
            }
            else
                screenStack.push(currentScreen);
        }
    }

    public Screen getCurrentScreen() {
        return screenStack.isEmpty() ? null : screenStack.peek();
    }

    public void setCurrentScreen(Screen newScreen, boolean disposePreviousScreen) {
        if (disposePreviousScreen && !screenStack.isEmpty()) {
            previousScreen = screenStack.pop();
            previousScreen.dispose();
        }

        screenStack.push(newScreen);
        mainProcess.setScreen(newScreen);
    }

    public void disposeAllScreen() {
        screenStack.clear();
        batch.dispose();
    }
}
