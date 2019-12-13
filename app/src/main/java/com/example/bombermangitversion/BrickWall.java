package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class BrickWall implements GameObject {

    private Rect rectangle;
    private int color;


    public BrickWall(int rectHeight, int rectWidth, int startX, int startY){
        this.color = Color.RED;
        rectangle = new Rect(startX, startY, startX + rectWidth, startY + rectHeight);
    }


    public Rect getRectangle(){
        return rectangle;
    }

    public boolean playerCollide(ObjBomberMan bomberMan){
        Rect copy = new Rect(this.rectangle);
        return copy.intersect(bomberMan.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(this.rectangle, paint);
    }

    @Override
    public void update() {

    }
}

