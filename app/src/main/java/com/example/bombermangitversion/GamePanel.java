package com.example.bombermangitversion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    //objects
    private Point playerCoords;
    private ObjBomberMan bomberMan;

    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        bomberMan = new ObjBomberMan(new Rect(100,100,200,200), Color.rgb(255,0,0));
        playerCoords = new Point(150,150);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
         thread = new MainThread(getHolder(), this);

         thread.setRunning(true);
         thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                playerCoords.set((int)event.getX(), (int)event.getY());
                Log.d("playerCoords", (int)event.getX() +" "+(int)event.getY());
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {

        bomberMan.update(playerCoords);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        bomberMan.draw(canvas);
    }
}
