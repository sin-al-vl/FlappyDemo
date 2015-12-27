package com.mygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.FlappyDemo;

/**
 * Created by Александр on 20.12.2015.
 */

public class MenuState extends State implements InputProcessor {

    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private boolean play, exit;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);

        if(gameStateManager.isDayOrNight())
            background = new Texture("bg.png");
        else
            background = new Texture("bg1.png");

        playBtn = new Texture("playbtn.png");
        exitBtn = new Texture("exitbtn.png");
//        exitBounds = new Rectangle(camera.position.x + FlappyDemo.WIDTH/4 - exitBtn.getWidth(),
//                camera.position.y + FlappyDemo.HEIGHT/4 - exitBtn.getHeight(),
//                exitBtn.getWidth(), exitBtn.getHeight());

        Gdx.input.setInputProcessor(this);

        gameStateManager.getSoundManager().playBackgroundMusic();
    }

    @Override
    public void handleInput() {
        if (play) {
            gameStateManager.set(new PlayState(gameStateManager));
        } else if (exit) {
            gameStateManager.getSoundManager().dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        sb.draw(background, 0, 0);
        sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        sb.draw(exitBtn, camera.position.x + FlappyDemo.WIDTH/4 - exitBtn.getWidth(),
                camera.position.y + FlappyDemo.HEIGHT/4 - exitBtn.getHeight());

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        exitBtn.dispose();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        System.out.println(FlappyDemo.WIDTH - exitBtn.getWidth());
//        System.out.println(FlappyDemo.WIDTH);
//        System.out.println(0);
//        System.out.println(exitBtn.getHeight());
        if(screenX > FlappyDemo.WIDTH/2 - playBtn.getWidth() &&
                screenX < FlappyDemo.WIDTH/2 + playBtn.getWidth() &&
                screenY < FlappyDemo.HEIGHT/2 - 30 &&
                screenY > FlappyDemo.HEIGHT/2 - playBtn.getHeight()*2 - 14)
            return play = true;
        else if(screenX > FlappyDemo.WIDTH - exitBtn.getWidth()*2 &&
                screenX < FlappyDemo.WIDTH &&
                screenY > 0 &&
                screenY < exitBtn.getHeight()*2)
            return exit = true;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
