package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class EnemyManager {
    private ArrayList<ObjEnemy> enemies;
    //private ArrayList<ObjWall> enemies;
    private int playerGap;
    private int enemyGap;
    private int enemyHeight;
    private int color;

    private long startTime;
    private long initTime;

    private int score = 0;

    public EnemyManager(int playerGap, int enemyGap, int enemyHeight, int color){
        this.playerGap = playerGap;
        this.enemyGap = enemyGap;
        this.enemyHeight = enemyHeight;
        this.color = color;

        startTime = initTime = System.currentTimeMillis();

        enemies = new ArrayList<ObjEnemy>();
        populateEnemies();
    }

    public boolean playerCollide(ObjBomberMan bomberMan){
        for(ObjEnemy oe : enemies){
            if(oe.playerCollide(bomberMan)){
                return true;
            }
        }
        return false;
    }

    private void populateEnemies() {
        int currY = -5*Constants.SCREEN_HEIGHT/4;

        while(currY < 0){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            enemies.add(new ObjEnemy(enemyHeight, color, xStart, currY, playerGap ));
            currY += enemyHeight + enemyGap;
        }

    }

    public void update(){
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        float speed = (float) (Math.sqrt(1 + (startTime - initTime)/1000.0))*Constants.SCREEN_HEIGHT/10000.0f;
        for(ObjEnemy oe : enemies){
            oe.incrementY(speed * elapsedTime);
        }
        if(enemies.get(enemies.size() - 1 ).getRectangle().top >= Constants.SCREEN_HEIGHT){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            enemies.add(0, new ObjEnemy(enemyHeight, color, xStart,enemies.get(0).getRectangle().top - enemyHeight - enemyGap ,playerGap));
            enemies.remove(enemies.size() - 1);
            score++;
        }


    }
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(45f);
        canvas.drawText(""+score, 50,50, paint);
        for(ObjEnemy oe : enemies){
            oe.draw(canvas);
        }
    }

}
