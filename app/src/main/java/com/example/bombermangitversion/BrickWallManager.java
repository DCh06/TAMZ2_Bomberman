package com.example.bombermangitversion;

import android.graphics.Canvas;

import java.util.ArrayList;

public class BrickWallManager {
    private ArrayList<BrickWall> brickWalls;
    private int color;
    private int[][] arr;



    public BrickWallManager(int color){
        this.color = color;
        arr = new int[][]{
                {0,0,0,0,0,0,0,0,0},
                {0,1,1,1,0,0,0,0,0},
                {0,1,0,0,0,0,0,1,0},
                {0,1,0,0,0,0,0,1,0},
                {0,1,0,0,0,0,0,1,0},
                {0,1,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0},
                {0,1,0,0,0,1,0,0,0},
                {0,1,0,1,0,1,0,0,0},
                {0,1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,1,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0},
        };

        brickWalls = new ArrayList<BrickWall>();
        generateWalls();
    }

    public boolean playerCollide(ObjBomberMan bomberMan){
        for(BrickWall bw : brickWalls){
            if(bw.playerCollide(bomberMan)){
                return true;
            }
        }
        return false;
    }

   public void enemyCollide(ArrayList<Nepritel> nepratele){
        for(BrickWall bw : brickWalls){
            for(Nepritel ne : nepratele)
                if(bw.enemyCollide(ne)){
                    ne.changeDirection();
                }
        }
    }

    private void generateWalls() {
        for(int i = 0; i < 15; i++ ){
            for(int j = 0; j < 9; j++)
          if(arr[i][j]==1){
              brickWalls.add(new BrickWall(Constants.SCREEN_HEIGHT / 16, Constants.SCREEN_WIDTH/10, (Constants.SCREEN_WIDTH/9) *j , i * (Constants.SCREEN_HEIGHT / 15) ));
          }
        }
    }

    public void update(){

    }
    public void draw(Canvas canvas){
        for(BrickWall bw : brickWalls){
            if(bw.visible)
                bw.draw(canvas);
            else
                brickWalls.remove(bw);
        }
    }

}
