package com.example.bombermangitversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class IronWall implements GameObject {

    private Rect rectangle;
    private int color;
    BitmapFactory bf;
    Bitmap spriteSheet;


    public IronWall(int rectHeight, int rectWidth, int startX, int startY){
        this.color = Color.BLACK;
        rectangle = new Rect(startX, startY, startX + rectWidth, startY + rectHeight);
        bf = new BitmapFactory();
        spriteSheet = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.brick_wall_sunny);
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

