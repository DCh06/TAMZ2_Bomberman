package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class ObjEnemy implements GameObject {

    private Rect rectangle;
    private int color;
    private Rect rectangle2;

    public ObjEnemy(int rectHeight, int color, int startX, int startY, int playerGap){
        this.color = color;
        rectangle = new Rect(0, startY, startX, startY + rectHeight);
        rectangle2 = new Rect(startX +  playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
    }

    public void incrementY(float y){
        rectangle.top += y;
        rectangle.bottom += y;
        rectangle2.top += y;
        rectangle2.bottom += y;
    }

    public Rect getRectangle(){
        return rectangle;
    }

    public boolean playerCollide(ObjBomberMan bomberMan){
        if(this.rectangle.intersect(bomberMan.getRectangle())){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(this.rectangle, paint);
        canvas.drawRect(this.rectangle2, paint);
    }

    @Override
    public void update() {

    }
}
