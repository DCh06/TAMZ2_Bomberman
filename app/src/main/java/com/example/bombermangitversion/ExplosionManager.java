package com.example.bombermangitversion;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ExplosionManager {
    private ArrayList<ObjExplosion> explosion;
    private int color;
    private int maximum;
    private long startTime;
    private boolean exploded;
    private boolean visible;

    public ExplosionManager(){
        maximum = 1;
        explosion = new ArrayList<ObjExplosion>();
        exploded = false;
        visible = false;
    }

    public void setExploded(){
        exploded = false;
    }

    public void enemyCollide(ArrayList<Nepritel> nepratele) {
        for (ObjExplosion ex : explosion) {
            for (Nepritel ne : nepratele) {
                if (ex.enemyCollide(ne)) {
                    ne.visible = false;
                }
            }
        }
    }

    public void playerCollide(ObjBomberMan bomberMan){
        for (ObjExplosion ex : explosion) {
                if (ex.playerCollide(bomberMan)) {
                    bomberMan.visible = false;
                }
        }
    }

    public void wallCollide(ArrayList<BrickWall> walls){
        for (ObjExplosion ex : explosion) {
            for (BrickWall bw : walls) {
                if (ex.wallCollide(bw)) {
                    bw.visible = false;
                }
            }
        }
    }

    public boolean stillExploding(){
        return this.exploded;
    }

    public void createExplosion(int x, int y) {
        if(!exploded)
        for(int i = 0; i < 3; i++){
            explosion.add(new ObjExplosion((Constants.SCREEN_HEIGHT / 16), (Constants.SCREEN_WIDTH/10), x + ((Constants.SCREEN_WIDTH/9) * i), y ));
            explosion.add(new ObjExplosion((Constants.SCREEN_HEIGHT / 16), (Constants.SCREEN_WIDTH/10), x , (y) + ((Constants.SCREEN_HEIGHT / 15) * i)));
            explosion.add(new ObjExplosion((Constants.SCREEN_HEIGHT / 16), (Constants.SCREEN_WIDTH/10), x + ((Constants.SCREEN_WIDTH/9) * -i), y ));
            explosion.add(new ObjExplosion((Constants.SCREEN_HEIGHT / 16), (Constants.SCREEN_WIDTH/10), x , (y) + ((Constants.SCREEN_HEIGHT / 15) * -i)));
            startTime = System.currentTimeMillis();
            visible = true;
            exploded = true;
        }
    }

    public void update(){
        if(System.currentTimeMillis() - startTime > 1000){
            /* bombs.get(0).visible = false;*/
            for(ObjExplosion ex : explosion){

                ex.visible = false;
            }
            exploded = false;
        }
    }

    /* public void exploded(Explosion explosion){
         return true;
     }
 */
    public void draw(Canvas canvas){
        for(ObjExplosion ex : explosion){
            if(ex.visible)
                ex.draw(canvas);
            else
                explosion.remove(ex);
        }
    }

}
