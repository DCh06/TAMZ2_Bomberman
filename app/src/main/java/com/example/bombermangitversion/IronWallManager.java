package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class IronWallManager {
    private ArrayList<IronWall> ironWalls;
    private int color;



    public IronWallManager(int color){
        this.color = color;


        ironWalls = new ArrayList<IronWall>();
        generateWalls();
    }

    public boolean playerCollide(ObjBomberMan bomberMan){
        for(IronWall iw : ironWalls){
            if(iw.playerCollide(bomberMan)){
                return true;
            }
        }
        return false;
    }

    private void generateWalls() {
       /* for(int i = 0; i < 14; i++ ) {
            ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 14, Constants.SCREEN_WIDTH/10, 0, i * Constants.SCREEN_HEIGHT / 15 ));
            ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 14, Constants.SCREEN_WIDTH/10 , (Constants.SCREEN_WIDTH/10) * 9, i * Constants.SCREEN_HEIGHT / 15));

        }
        for (int i = 1; i < 9; i++){
           // ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 15, Constants.SCREEN_WIDTH/9, i * Constants.SCREEN_WIDTH/9,  Constants.SCREEN_HEIGHT / 14 * 14 ));
            ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 15, Constants.SCREEN_WIDTH/10, i * Constants.SCREEN_WIDTH/8, 0 ));
            ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 15, Constants.SCREEN_WIDTH/10, i * Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT / 14 * 13 ));
        }*/

        for(int i = 0; i < 15; i++ ){
            ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, 0, i * Constants.SCREEN_HEIGHT / 15 ));
            ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/9 * 8, i * Constants.SCREEN_HEIGHT / 15 ));
            if(i%2 == 0){
                ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/9 * 2, i * Constants.SCREEN_HEIGHT / 15 ));
                ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/9 * 4, i * Constants.SCREEN_HEIGHT / 15 ));
                ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/9 * 6, i * Constants.SCREEN_HEIGHT / 15 ));
            }
            if(i == 0 || i == 14){
                ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/9 , i * Constants.SCREEN_HEIGHT / 15 ));
                ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/9 * 3, i * Constants.SCREEN_HEIGHT / 15 ));
                ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/9 * 5, i * Constants.SCREEN_HEIGHT / 15 ));
                ironWalls.add(new IronWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/9 * 7, i * Constants.SCREEN_HEIGHT / 15 ));
            }
        }
    }

    public void update(){

    }
    public void draw(Canvas canvas){
        for(IronWall iw : ironWalls){
            iw.draw(canvas);
        }
    }

}
