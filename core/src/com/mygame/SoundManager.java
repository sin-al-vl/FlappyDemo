package com.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Александр on 24.12.2015.
 */
public class SoundManager {
    private Sound jumpSound;
    private Sound pointSound;
    private Sound hitSound;
    private Sound dieSound;
    private Sound explosionSound;
    private Music backgroundMusic;

    public SoundManager(String jumpSound, String pointSound, String hitSound, String dieSound,
                        String explosionSound, String backgroundMusic) {
        this.jumpSound = Gdx.audio.newSound(Gdx.files.internal(jumpSound));
        this.pointSound = Gdx.audio.newSound(Gdx.files.internal(pointSound));
        this.hitSound = Gdx.audio.newSound(Gdx.files.internal(hitSound));
        this.dieSound = Gdx.audio.newSound(Gdx.files.internal(dieSound));
        //this.explosionSound = Gdx.audio.newSound(Gdx.files.internal(explosionSound));
        this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(backgroundMusic));
    }

    public void setJumpSound(Sound jumpSound) {
        this.jumpSound = jumpSound;
    }

    public void setDieSound(Sound dieSound) {
        this.dieSound = dieSound;
    }

    public void setHitSound(Sound hitSound) {
        this.hitSound = hitSound;
    }

    public void setPointSound(Sound pointSound) {
        this.pointSound = pointSound;
    }

    public void setBackgroundMusic(Music backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public void playJumpSound() {
        if(jumpSound != null)
            jumpSound.play();
    }

    public void playDieSound() {
        if(dieSound != null)
            dieSound.play();
    }

    public void playHitSound() {
        if(hitSound != null)
        hitSound.play();
    }

    public void playPointSound() {
        if(pointSound != null)
        pointSound.play();
    }

    public void playExplosionSound(){
        if(explosionSound != null)
            explosionSound.play();
    }
    public void playBackgroundMusic() {
        if(backgroundMusic != null) {
            backgroundMusic.setLooping(true);
            backgroundMusic.play();
            backgroundMusic.setVolume(Float.valueOf("0.4"));
        }
    }

    public void stopBackgroundMusic(){
        if(backgroundMusic != null && backgroundMusic.isPlaying())
            backgroundMusic.stop();
    }

    public void pauseBackgroundMusic(){
        if(backgroundMusic != null && backgroundMusic.isPlaying())
            backgroundMusic.pause();
    }

    public void dispose(){
        jumpSound.dispose();
        pointSound.dispose();
        hitSound.dispose();
        dieSound.dispose();
        backgroundMusic.dispose();
    }
}
