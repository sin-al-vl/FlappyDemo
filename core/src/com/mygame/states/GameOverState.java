package com.mygame.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.FlappyDemo;

/**
 * Created by Александр on 22.12.2015.
 */

public class GameOverState extends State implements InputProcessor {
    private Texture gameOver;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture background;
    private boolean restart, exit;

    private BitmapFont font;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);

        if(gameStateManager.isDayOrNight())
            background = new Texture("bg.png");
        else
            background = new Texture("bg1.png");
        gameOver = new Texture("gameover.png");
        exitBtn = new Texture("exitbtn.png");
        playBtn = new Texture("playbtn.png");

        Gdx.input.setInputProcessor(this);

        gameStateManager.getSoundManager().playDieSound();

        if(gameStateManager.getSaver().getRecPoint() < gameStateManager.score)
            gameStateManager.getSaver().setRecPoint(gameStateManager.score);

        font = new BitmapFont();
    }

    @Override
    protected void handleInput() {
        if(restart) {
            gameStateManager.set(new PlayState(gameStateManager));
        } else if(exit){
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
        sb.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y);
        sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y - playBtn.getHeight());
        sb.draw(exitBtn, camera.position.x + FlappyDemo.WIDTH/4 - exitBtn.getWidth(),
                camera.position.y + FlappyDemo.HEIGHT/4 - exitBtn.getHeight());
        font.draw(sb, "Score: " + gameStateManager.score, camera.position.x - playBtn.getWidth() / 2 + 25,
                camera.position.y - playBtn.getHeight() - font.getCapHeight());
        font.draw(sb, "Best: " + gameStateManager.getSaver().getRecPoint(),camera.position.x - playBtn.getWidth() / 2 + 25,
                camera.position.y - playBtn.getHeight() - font.getCapHeight()*2 - 15);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        gameOver.dispose();
        exitBtn.dispose();
        font.dispose();
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
//        System.out.println(FlappyDemo.WIDTH/2 - playBtn.getWidth());
//        System.out.println(FlappyDemo.WIDTH/2 + playBtn.getWidth());
//        System.out.println(FlappyDemo.HEIGHT/2 -20 );
//        System.out.println(FlappyDemo.HEIGHT/2 + playBtn.getHeight()*2 - 36 );
        if(screenX > FlappyDemo.WIDTH/2 - playBtn.getWidth() &&
                screenX < FlappyDemo.WIDTH/2 + playBtn.getWidth() &&
                screenY > FlappyDemo.HEIGHT/2 - 20 &&
                screenY < FlappyDemo.HEIGHT/2 + playBtn.getHeight()*2 - 36)
            return restart = true;
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
