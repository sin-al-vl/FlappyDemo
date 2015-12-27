package com.mygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygame.FlappyDemo;
import com.mygame.sprites.Bird;
import com.mygame.sprites.Enemy;
import com.mygame.sprites.Tube;

/**
 * Created by Александр on 20.12.2015.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 250;
    private static final int TUBE_COUNT = 2;
    private static final int GROUND_Y_OFFSET = -30;

    private BitmapFont font;

    private Bird bird;
    private Enemy enemy;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);

        bird = new Bird(50, 300);
        enemy = new Enemy();

        if(gameStateManager.isDayOrNight())
            bg = new Texture("bg.png");
        else
            bg = new Texture("bg1.png");

        ground = new Texture("ground.png");

        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();
        for (int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH) + FlappyDemo.WIDTH*(float)(3/2)));
        }

        font = new BitmapFont();
        gameStateManager.score = 0;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.getSoundManager().playJumpSound();
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        if(enemy.isValid()) {
            enemy.updateTarget(bird.getPosition());
            enemy.update(dt);
            if(enemy.getPosition().x < camera.position.x - (camera.viewportWidth / 2) - enemy.getEnemy().getWidth())
                enemy.setNotValid();
        } else if (gameStateManager.score > 0 && gameStateManager.score%3 == 0) {
            enemy.updateTarget(bird.getPosition());
            enemy.attack();
        }

        for (int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                tube.setNonChecked();
            }

            if(tube.isPassed(bird.getPosition())) {
                gameStateManager.score++;
                gameStateManager.getSoundManager().playPointSound();
            }

            if (tube.collides(bird.getBounds()) || enemy.collides(bird.getBounds()) || bird.getPosition().y < 75) {
                if(tube.collides(bird.getBounds()))
                    gameStateManager.getSoundManager().playHitSound();
                else if(enemy.collides(bird.getBounds()))
                    gameStateManager.getSoundManager().playExplosionSound();
                gameStateManager.set(new GameOverState(gameStateManager));
                break;
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);

        if(enemy.isValid())
            if (camera.position.x + camera.viewportWidth / 2 < enemy.getPosition().x)
                sb.draw(enemy.getWarning(),
                        camera.position.x + camera.viewportWidth / 2 - enemy.getWarning().getWidth(),
                        enemy.getPosition().y);
            else
                sb.draw(enemy.getEnemy(), enemy.getPosition().x, enemy.getPosition().y);


        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        font.draw(sb, "Score: " + gameStateManager.score, camera.position.x - (camera.viewportWidth / 2) + 10,
                400);

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        enemy.dispose();
        ground.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        font.dispose();
    }

    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth()*2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth()*2, 0);
    }
}