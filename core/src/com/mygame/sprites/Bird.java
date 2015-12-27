package com.mygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygame.FlappyDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Александр on 20.12.2015.
 */

public class Bird {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;

    Texture birdAnimation;
    private TextureRegion bird;
    private Map<String, TextureRegion> birdRegions = new HashMap<String, TextureRegion>();

    private int oneWagCycle;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        birdAnimation = new Texture("birdanimation.png");

        TextureRegion tmp[][] = TextureRegion.split(birdAnimation, birdAnimation.getWidth() / 3, birdAnimation.getHeight());
        birdRegions.put("up", tmp[0][0]);
        birdRegions.put("middle", tmp[0][1]);
        birdRegions.put("down", tmp[0][2]);

        oneWagCycle = 0;
        bird = birdRegions.get("up");

        bounds = new Rectangle(x, y, bird.getRegionWidth(), bird.getRegionHeight());

    }

    public void update(float dt){
        animate();

        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);

        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y > FlappyDemo.HEIGHT/2 - 25)
            position.y = FlappyDemo.HEIGHT/2 - 25;


        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    private void animate() {
        if (oneWagCycle != 0) {
            if(oneWagCycle > 13)
                oneWagCycle = 0;
            else {

                if (bird.equals(birdRegions.get("up")) && oneWagCycle == 1)
                    bird = birdRegions.get("middle");
                else if (bird.equals(birdRegions.get("middle")) && oneWagCycle == 4)
                    bird = birdRegions.get("down");
                else if (bird.equals(birdRegions.get("down")) && oneWagCycle == 10)
                    bird = birdRegions.get("middle");
                else if (bird.equals(birdRegions.get("middle")) && oneWagCycle == 13)
                    bird = birdRegions.get("up");

                oneWagCycle++;
            }
        }
    }

    public void jump(){
        velocity.y = 250;
        oneWagCycle = 1;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return bird;
    }

    public Rectangle getBounds(){
        return bounds;
    }


    public void dispose() {
        birdAnimation.dispose();
    }
}
