package com.example.bombermangitversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private long startTime;
    private int width;
    private int height;


    private Animation walkDown;
    private Animation walkUp;
    private  AnimationManager animationManager;



    public Nepritel(int rectHeight, int rectWidth, int startX, int startY, boolean direction) {
        this.color = Color.MAGENTA;
        rectangle = new Rect(startX, startY, startX + rectWidth, startY + rectHeight);
        visible = true;
        speed = 0.2f;
        startTime = System.currentTimeMillis();

        BitmapFactory bf = new BitmapFactory();
        Bitmap spriteSheet = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.moustaki);
        this.width = spriteSheet.getWidth() / 4;
        this.height = spriteSheet.getHeight() / 4;


        walkDown = new Animation(spriteSheet, 3, height, width, 2);
        walkUp = new Animation(spriteSheet, 3, height, width, 0);//1 left 3 right

        animationManager = new AnimationManager(new Animation[]{ walkUp, walkDown});
        animationManager.playAnim(0);
    }

    public void walkUp(float y) {
        rectangle.top += y*speed;
        rectangle.bottom += y*speed;
        Log.d("enemy speed", ""+y*speed);
        animationManager.playAnim(1);
    }

    public void walkDown(float y) {
        rectangle.top -= y*speed;
        rectangle.bottom -= y*speed;
        animationManager.playAnim(0);
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


        if(System.currentTimeMillis() - startTime > 250) {
            direction = !direction;
            startTime = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        /*Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(this.rectangle, paint);*/
        if(visible)animationManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animationManager.update();
    }
}
