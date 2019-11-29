package com.example.bombermangitversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class ObjBomberMan implements GameObject {
    private Rect rectangle;
    private int color;
    private int width;
    private int height;

    private Animation idle;
    private Animation walkDown;
    private Animation walkUp;
    private  AnimationManager animationManager;


    public ObjBomberMan(Rect rectangle, int color){

        this.rectangle = rectangle;
        this.color = color;

        BitmapFactory bf = new BitmapFactory();
        Bitmap spriteSheet = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.ned);
        this.width = spriteSheet.getWidth() / 4;
        this.height = spriteSheet.getHeight() / 4;

        walkDown = new Animation(spriteSheet, 3, height, width, 2);
        walkUp = new Animation(spriteSheet, 3, height, width, 0);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point){
        float oldDown = rectangle.bottom;

        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2 );

        int state = 0;
        if(rectangle.bottom - oldDown > 5 )
            state = 1;
        else if( rectangle.bottom - oldDown < -5  )
            state = 2;


    }

    public Rect getRectangle(){
        return rectangle;
    }
}
