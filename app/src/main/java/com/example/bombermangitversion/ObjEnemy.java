package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class ObjEnemy implements GameObject {

    private Rect rectangle;
    private int color;

    public ObjEnemy(Rect rectangle, int color){

        this.rectangle = rectangle;
        this.color = color;
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
    }

    @Override
    public void update() {

    }
}
