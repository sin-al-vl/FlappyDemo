package com.mygame.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.Saver;
import com.mygame.SoundManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Александр on 20.12.2015.
 */

public class GameStateManager {
    private Stack<State> states;

    private SoundManager soundManager;
    private Saver saver;
    protected int score;

    protected boolean dayOrNight;

    public GameStateManager(){
        states = new Stack<State>();

        soundManager = new SoundManager("sfx_wing.ogg", "sfx_point.ogg", "sfx_hit.ogg", "sfx_die.ogg",
                "Explosion.ogg", "music.mp3");
        saver = new Saver();

        Date now = new Date();
        dayOrNight = now.getHours() > 7 || now.getHours() < 18;
    }

    public boolean isDayOrNight() {
        return dayOrNight;
    }

    public SoundManager getSoundManager(){
        return soundManager;
    }

    public Saver getSaver() {
        return saver;
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
