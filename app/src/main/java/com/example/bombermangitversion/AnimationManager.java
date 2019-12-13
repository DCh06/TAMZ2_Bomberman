package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.util.Log;

public class AnimationManager {

    private Animation[] animations;
    private int animationIndex = 0;
    public AnimationManager(Animation[] animations){
        this.animations = animations;
    }

    public void playAnim(int index) {
        /*
        Log.d("PLAYANIM", String.valueOf(index));
        for (int i = 0; i < animations.length; i++) {
            if (i == index) {
                if (!animations[index].isPlaying()) {
                    animations[i].play();
                }
            }else
                animations[i].stop();
        }

         */
        animations[index].play();
        animationIndex = index;
    }

    public void draw(Canvas canvas, Rect rect){
        if(animations[animationIndex].isPlaying())
            animations[animationIndex].draw(canvas,rect);
    }

    public void update(){
        if(animations[animationIndex].isPlaying())
            animations[animationIndex].update();
    }

}
