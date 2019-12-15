package com.example.bombermangitversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class ObjBomberMan implements GameObject {
    private Rect rectangle;
    private int color;
    private int width;
    private int height;
    private Point currentPosition;
    private Point moveVector;
    private float speed;
    public boolean visible;

    private long startTime;
    private long initTime;

    private Animation idle;
    private Animation walkDown;
    private Animation walkUp;
    private Animation walkLeft;
    private Animation walkRight;
    private  AnimationManager animationManager;


    public ObjBomberMan(Rect rectangle, int color){

        this.rectangle = rectangle;
        this.color = color;
        this.speed =  0.5f;
        BitmapFactory bf = new BitmapFactory();
        Bitmap spriteSheet = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.ned);
        this.width = spriteSheet.getWidth() / 4;
        this.height = spriteSheet.getHeight() / 4;

        this.currentPosition = new Point();
        currentPosition.set(400,400);
        this.moveVector = new Point();
        moveVector.set(0,0);

        idle = new Animation(spriteSheet, 3, height, width, 2);
        walkDown = new Animation(spriteSheet, 3, height, width, 2);
        walkUp = new Animation(spriteSheet, 3, height, width, 0);//1 left 3 right
        walkLeft = new Animation(spriteSheet, 3, height, width, 1);
        walkRight = new Animation(spriteSheet, 3, height, width, 3);

        animationManager = new AnimationManager(new Animation[]{idle, walkDown, walkUp, walkLeft, walkRight});
        animationManager.playAnim(0);
        visible = true;
    }

    public void walkUp() {

        animationManager.playAnim(1);
        moveVector.set(0,-1);
/*
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        //float speed = (float) (Math.sqrt(1 + (startTime - initTime)/1000.0))*Constants.SCREEN_HEIGHT/10000.0f;
        /*for(ObjEnemy oe : enemies){
            oe.incrementY(speed * elapsedTime);
        }
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2 );

        //rectangle.top += speed * elapsedTime;
        //rectangle.bottom += speed * elapsedTime;
        rectangle.set(rectangle.left, rectangle.top + (int)(speed * elapsedTime), rectangle.right, rectangle.bottom + (int)(speed * elapsedTime));
        Log.d("WALKUP", String.valueOf(speed * elapsedTime));
*/
    }

    public void walkDown(){
        animationManager.playAnim(2);
        moveVector.set(0,1);
    }
    public void walkLeft(){
        animationManager.playAnim(3);
        moveVector.set(-1,0);
    }
    public void walkRight(){
        animationManager.playAnim(4);
        moveVector.set(1,0);
    }
    public void stop(){
        update();
        moveVector.set(0,0);
    }

    public Point getCurrentPosition(){
        return this.currentPosition;
    }

    @Override
    public void draw(Canvas canvas) {
        if(visible)animationManager.draw(canvas, rectangle);
    }

    //   @Override
    //   public void update() {
    //       animationManager.update();
    //   }

    public void update(){
        float oldDown = rectangle.bottom;
        float oldLeft = rectangle.left;

        Point point = new Point();

        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        int dt = (int)(speed * elapsedTime);
        point.set( currentPosition.x + moveVector.x * dt,  currentPosition.y + moveVector.y * dt);
        currentPosition = point;

        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2 );


        animationManager.update();
    }

    public Rect getRectangle(){
        return rectangle;
    }
}
