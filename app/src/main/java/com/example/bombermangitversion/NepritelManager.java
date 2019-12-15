package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public class NepritelManager {
    private ArrayList<Nepritel> nepratele;
    private int color;

    private long startTime;
    private long elapsedTime;
    private long initTime;

    private int score = 0;

    public NepritelManager( int color){
        this.color = color;

        startTime = System.currentTimeMillis();

        nepratele = new ArrayList<Nepritel>();
        populateEnemies();
    }

    public void playerCollide(ObjBomberMan bomberMan){
        for(Nepritel nepritel : nepratele){
            if(nepritel.playerCollide(bomberMan)){
                bomberMan.visible = false;
            }
        }
    }

    private void populateEnemies() {
       /* int currY = -5*Constants.SCREEN_HEIGHT/4;

        while(currY < 0){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            enemies.add(new ObjEnemy(enemyHeight, color, xStart, currY, playerGap ));
            currY += enemyHeight + enemyGap;
        }
*/

        nepratele.add(new Nepritel(81,81,(Constants.SCREEN_WIDTH/9) *5 , 6 * (Constants.SCREEN_HEIGHT / 15), true));
        nepratele.add(new Nepritel(81,81,(Constants.SCREEN_WIDTH/9) *7 , 13 * (Constants.SCREEN_HEIGHT / 15), false));
        nepratele.add(new Nepritel(81,81,(Constants.SCREEN_WIDTH/9) * 1 , 13 * (Constants.SCREEN_HEIGHT / 15), true));
        Log.d("Enemy Added", "yiss");
    }

    public void update(){
        /*int dt = (int)(System.currentTimeMillis() - startTime);
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
*/
        int dt = (int)(System.currentTimeMillis() - startTime);

        Log.d("enemydt", ""+dt);
        for(Nepritel nepritel : nepratele) {
            //pridat podminku aby ubehlo par chvil na zmenu direcke
            if(nepritel.direction){
                nepritel.walkUp(dt);
            }
            else
                nepritel.walkDown(dt);

            nepritel.update();
        }
        startTime = System.currentTimeMillis();

    }

    public ArrayList<Nepritel> getNepratele(){
        return this.nepratele;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        for(Nepritel nepritel : nepratele){
            if(nepritel.visible)
                nepritel.draw(canvas);
            else
                nepratele.remove(nepritel);
            Log.d("Enemy drawn", "yiss");
        }
    }

}
