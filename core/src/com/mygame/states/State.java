package com.mygame.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Александр on 20.12.2015.
 */

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;

    private int width, height;
    public float ppuX;	// pixels per unit on the X axis
    public float ppuY;	// pixels per unit on the Y axis
    float CAMERA_WIDTH = 800F;
    float CAMERA_HEIGHT = 480F;

    public State(GameStateManager gsm){
        this.gameStateManager = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width/CAMERA_WIDTH;
        ppuY = (float)height/CAMERA_HEIGHT;
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
