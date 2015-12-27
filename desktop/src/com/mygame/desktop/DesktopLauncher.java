package com.mygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygame.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name","CorrectUserName");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = FlappyDemo.WIDTH;
		config.height = FlappyDemo.HEIGHT;
		config.title = FlappyDemo.TITLE;

		new LwjglApplication(new FlappyDemo(), config);
	}
}
