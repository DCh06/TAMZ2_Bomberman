package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class ObjBomb implements GameObject{

    private Rect rectangle;
    private int color;
    public boolean visible;
    public boolean justPlanted;
    private Point bomblocation;



    public ObjBomb(int rectHeight, int rectWidth, int startX, int startY){
        this.color = Color.GRAY;
        rectangle = new Rect(startX, startY, startX + rectWidth, startY + rectHeight);
        visible = true;
        justPlanted = true;
        bomblocation= new Point(startX, startY);
    }


    public Rect getRectangle(){
        return rectangle;
    }

    public Point getBomblocation() {
        return bomblocation;
    }

    public boolean playerCollide(ObjBomberMan bomberMan){
        Rect copy = new Rect(this.rectangle);
        if(copy.intersect(bomberMan.getRectangle())&&justPlanted){
            return false;
        }
        justPlanted = false;
        if(copy.intersect(bomberMan.getRectangle())){
            return true;
        }
        return false;
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
