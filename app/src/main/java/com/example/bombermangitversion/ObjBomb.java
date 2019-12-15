package com.example.bombermangitversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private int width;
    private int height;

    private Animation bombAnimation;
    private  AnimationManager animationManager;



    public ObjBomb(int rectHeight, int rectWidth, int startX, int startY){
        this.color = Color.GRAY;
        rectangle = new Rect(startX, startY, startX + rectWidth, startY + rectHeight);
        visible = true;
        justPlanted = true;
        bomblocation= new Point(startX, startY);
        BitmapFactory bf = new BitmapFactory();
        Bitmap spriteSheet = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.bomb_8);
        this.width = spriteSheet.getWidth() / 4;
        this.height = spriteSheet.getHeight() / 4;
        bombAnimation = new Animation(spriteSheet, 3, height, width, 3);
        animationManager = new AnimationManager(new Animation[]{ bombAnimation});
        animationManager.playAnim(0);
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
