package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class IronWall implements GameObject {

    private Rect rectangle;
    private int color;


    public IronWall(int rectHeight, int rectWidth, int startX, int startY){
        this.color = Color.BLACK;
        rectangle = new Rect(startX, startY, startX + rectWidth, startY + rectHeight);
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
        canvas.drawRect(this.rectangle, paint);
    }

    @Override
    public void update() {

    }
}

