package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Nepritel implements GameObject {

    private Rect rectangle;
    private int color;
    public boolean direction;
    public boolean visible;
    private float speed;



    public Nepritel(int rectHeight, int rectWidth, int startX, int startY, boolean direction) {
        this.color = Color.MAGENTA;
        rectangle = new Rect(startX, startY, startX + rectWidth, startY + rectHeight);
        visible = true;
        speed = 0.2f;
    }

    public void walkUp(float y) {
        rectangle.top += y*speed;
        rectangle.bottom += y*speed;
        Log.d("enemy speed", ""+y*speed);
    }

    public void walkDown(float y) {
        rectangle.top -= y*speed;
        rectangle.bottom -= y*speed;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public boolean playerCollide(ObjBomberMan bomberMan) {
        Rect copy = new Rect(this.rectangle);
        return copy.intersect(bomberMan.getRectangle());
    }

    public boolean bombCollide(){
        return true;
    }

    public void changeDirection() {
        direction = !direction;
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
