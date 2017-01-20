package com.mygame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.states.GameStateManager;
import com.mygame.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	public static int WIDTH = 480;
	public static int HEIGHT = 800;

	public static final String TITLE = "Flappy Demo";


	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));

		System.out.println(Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());

		if(Gdx.app.getType().equals(Application.ApplicationType.Android)) {

			WIDTH = Gdx.graphics.getWidth();
			HEIGHT = Gdx.graphics.getHeight();
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.resume();
	}
}