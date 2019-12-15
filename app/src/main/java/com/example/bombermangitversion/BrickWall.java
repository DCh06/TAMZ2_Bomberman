package com.example.bombermangitversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class BrickWall implements GameObject {

    private Rect rectangle;
    private int color;
    public boolean visible;
    BitmapFactory bf;
    Bitmap spriteSheet;


    public BrickWall(int rectHeight, int rectWidth, int startX, int startY){
        this.color = Color.RED;
        rectangle = new Rect(startX, startY, startX + rectWidth, startY + rectHeight);
        visible = true;
        bf = new BitmapFactory();
        spriteSheet = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.brick_wall);
    }


    public Rect getRectangle(){
        return rectangle;
    }

    public boolean playerCollide(ObjBomberMan bomberMan){
        Rect copy = new Rect(this.rectangle);
        return copy.intersect(bomberMan.getRectangle());
    }

    public boolean enemyCollide(Nepritel nepritel){
        Rect copy = new Rect(this.rectangle);
        return copy.intersect(nepritel.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawBitmap(spriteSheet, null, rectangle, null);
    }

    @Override
    public void update() {

    }
}

