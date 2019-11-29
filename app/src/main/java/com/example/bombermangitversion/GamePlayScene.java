package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class GamePlayScene implements Scene {


    //objects
    private Point playerCoords;
    private ObjBomberMan bomberMan;
    private EnemyManager enemyManager;

    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private long gameOverTime;

    public GamePlayScene(){
        bomberMan = new ObjBomberMan(new Rect(100,100,200,200), Color.rgb(255,0,0));
        playerCoords = new Point(Constants.SCREEN_WIDTH/2,3*Constants.SCREEN_HEIGHT/4);
        bomberMan.update(playerCoords);

        enemyManager = new EnemyManager(200, 350, 75, Color.BLACK);
    }

    public void reset(){
        playerCoords = new Point(Constants.SCREEN_WIDTH/2,3*Constants.SCREEN_HEIGHT/4);
        bomberMan.update(playerCoords);
        enemyManager = new EnemyManager(200, 350, 75, Color.BLACK);
        movingPlayer = false;
    }

    @Override
    public void update() {
        if(!gameOver) {
            bomberMan.update(playerCoords);
            enemyManager.update();
            if(enemyManager.playerCollide(bomberMan)){
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        bomberMan.draw(canvas);
        enemyManager.draw(canvas);

        if(gameOver){
            Paint p = new Paint();
            p.setTextSize(42f);
            p.setColor(Color.RED);
            canvas.drawText("Game Over", Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, p);
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void recievedTouch(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && bomberMan.getRectangle().contains((int)event.getX(), (int)event.getY()))
                    movingPlayer = true;
                if(gameOver && System.currentTimeMillis() - gameOverTime >= 2000){
                    reset();
                    gameOver = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!gameOver && movingPlayer)
                    playerCoords.set((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }

    }
}
