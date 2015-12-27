package com.mygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Александр on 26.12.2015.
 */
public class Enemy {

    public static final int APPEARANCE_DISTANCE = 600;

    private static final int MOVEMENT = -50;
    private static final int APPROXIMATION = 50;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle boundsEnemy;

    private Texture warning;
    private Texture enemy;

    private Vector3 target;

    private boolean isValid;

    public boolean isValid() {
        return isValid;
    }

    public Enemy() {
        position = new Vector3(0, 0, 0);
        velocity = new Vector3(0, 0, 0);

        enemy = new Texture("komar.png");
        warning = new Texture("Warning.png");

        boundsEnemy = new Rectangle(10, 10, enemy.getWidth() - 20, enemy.getHeight() - 20);
        isValid = false;
    }

    public void updateTarget(Vector3 target){
        this.target = target;
    }

    public void attack(){
        position.y = target.y;
        position.x = target.x + APPEARANCE_DISTANCE;
        isValid = true;
    }

    public void update(float dt){

        velocity.scl(dt);

        if (target.y > position.y )
            position.add(MOVEMENT * dt, APPROXIMATION*dt, 0);
        else if (target.y < position.y)
            position.add(MOVEMENT * dt, -APPROXIMATION*dt, 0);

        velocity.scl(1 / dt);
        boundsEnemy.setPosition(position.x + 10, position.y + 10);
    }

    public Vector3 getPosition() {
        return position;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsEnemy);
    }

    public Texture getWarning() {
        return warning;
    }

    public Texture getEnemy() {
        return enemy;
    }

    public void dispose() {
        enemy.dispose();
        warning.dispose();
    }

    public void setNotValid() {
        isValid = false;
    }
}
