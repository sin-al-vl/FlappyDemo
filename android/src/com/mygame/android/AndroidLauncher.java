package com.mygame.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygame.FlappyDemo;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useCompass = false;
		config.useAccelerometer = false;

//		try { Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
//		} catch (Exception e) {}

		initialize(new FlappyDemo(), config);
	}
}
