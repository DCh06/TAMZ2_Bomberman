package com.example.bombermangitversion;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Animation {
    private Bitmap spriteSheet;
    private int frameIndex;
    private int row = 0;
    private int height;
    private int width;

    private boolean isPlaying = false;
    public boolean isPlaying(){
        return this.isPlaying;
    }
    public void play(){
        isPlaying = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }
    public void stop(){
        isPlaying = false;
    }
    private float frameTime;
    private long lastFrame;

    public Animation(Bitmap spriteSheet, float animTime, int height, int width, int row){
        this.spriteSheet = spriteSheet;
        this.width = width;
        this.height = height;
        this.row = row;

        frameIndex =  0;

        frameTime = animTime/3;//number of frames
        lastFrame = System.currentTimeMillis();
    }


    public void draw(Canvas canvas, Rect destination){
        if(!isPlaying)
            return;
        int srcX = frameIndex * width;
        int srcY = height * row;

        Rect source = new Rect(srcX, srcY, srcX + width, srcY + height);
        canvas.drawBitmap(spriteSheet, source, destination, null);
    }


    public void update(){
        if(!isPlaying)
            return;

        if(System.currentTimeMillis() - lastFrame > frameTime*1000){
            frameIndex++;
            frameIndex = frameIndex < 3? frameIndex: 0;
            lastFrame = System.currentTimeMillis();
        }
    }
}
