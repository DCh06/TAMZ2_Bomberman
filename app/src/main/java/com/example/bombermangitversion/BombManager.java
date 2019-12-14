package com.example.bombermangitversion;

import android.graphics.Canvas;

import java.util.ArrayList;

public class BombManager {
    private ArrayList<ObjBomb> bombs;
    private int color;
    private int maximum;
    private long startTime;
    private boolean exploded;

    public BombManager(){
        maximum = 1;
        bombs = new ArrayList<ObjBomb>();
        exploded = false;

    }

    public boolean playerCollide(ObjBomberMan bomberMan){
            for(ObjBomb bb:bombs){
                if(bb.playerCollide(bomberMan))
                    return true;
            }
            return false;
    }
    public void setExploded(){
        exploded = false;
    }

    public void enemyCollide(ArrayList<Nepritel> nepratele) {
        for (ObjBomb bomb : bombs) {
            for (Nepritel ne : nepratele) {
                if (bomb.enemyCollide(ne)) {
                    ne.changeDirection();
                }
            }
        }
    }

    public void createBomb(int x, int y) {
        if(bombs.size() <= maximum){
            bombs.add(new ObjBomb((Constants.SCREEN_HEIGHT / 16), (Constants.SCREEN_WIDTH/10), x, y + 20));
            startTime = System.currentTimeMillis();
        }
    }

    public void update(){
        if(System.currentTimeMillis() - startTime > 3000){
           /* bombs.get(0).visible = false;*/
            for(ObjBomb bb : bombs){

                bb.visible = false;
                exploded = true;
            }
        }
    }

   /* public void exploded(Explosion explosion){
        return true;
    }
*/
    public void draw(Canvas canvas){
        for(ObjBomb bb : bombs){
            if(bb.visible)
                bb.draw(canvas);
            else
                bombs.remove(bb);
        }
    }

}
