package com.example.bombermangitversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.concurrent.CopyOnWriteArrayList;

public class GamePlayScene implements Scene {


    //objects
    private Point playerCoords;
    private ObjBomberMan bomberMan;
    //private EnemyManager enemyManager;
    private IronWallManager ironWallManager;
    private BrickWallManager brickWallManager;
    private NepritelManager nepritelManager;
    private ExplosionManager explosionManager;
    private BombManager bombManager;
    private boolean test = false;
    private Point lastPosition;
    private int direction = 0;
    private int x;
    private int y;
    private boolean end;
    BitmapFactory bf;
    Bitmap spriteSheet;

    /*private Bomb bomb;
    private BrickWalls brickWalls;*/

    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private long gameOverTime;
    private boolean gameVictory = false;

    public GamePlayScene() {
        bomberMan = new ObjBomberMan(new Rect(120, 120, 200, 200), Color.rgb(255, 0, 0));
        playerCoords = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        lastPosition = playerCoords;
        bomberMan.update();
        ironWallManager = new IronWallManager(Color.BLACK);
        brickWallManager = new BrickWallManager(Color.RED);
        nepritelManager = new NepritelManager(Color.MAGENTA);
        explosionManager = new ExplosionManager();
        bombManager = new BombManager();
        end = false;
        gameVictory = false;
        bf = new BitmapFactory();
        spriteSheet = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.grass_16);


        //enemyManager = new EnemyManager(200, 350, 75, Color.BLACK);
    }

   /* public void reset() {
        playerCoords = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        bomberMan.update();
        //enemyManager = new EnemyManager(200, 350, 75, Color.BLACK);
        movingPlayer = false;
    }
    */
    //TODO GAME OVeR SCREEN

    @Override
    public void update() {
        if (!gameOver) {


           /* enemyManager.update();
            if(enemyManager.playerCollide(bomberMan)){
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }*/

            if (ironWallManager.playerCollide(bomberMan)||brickWallManager.playerCollide(bomberMan)|| bombManager.playerCollide(bomberMan)) {
                //bomberMan.update();
                if(direction==0) {
                    bomberMan.walkRight();//left 0, up 1 , right 2,  down 3

                }
                if(direction==1) {
                    bomberMan.walkDown();

                }
                if(direction==2) {
                    bomberMan.walkLeft();

                }
                if(direction==3) {
                    bomberMan.walkUp();
                }
                bomberMan.stop();
            }

            if(bombManager.getExploded()){
                explosionManager.createExplosion(bombManager.getBombCoords().x,bombManager.getBombCoords().y);
                bombManager.setExploded();
            }

            explosionManager.playerCollide(bomberMan);
            nepritelManager.playerCollide(bomberMan);
            brickWallManager.enemyCollide(nepritelManager.getNepratele());
            ironWallManager.enemyCollide(nepritelManager.getNepratele());
            bombManager.enemyCollide(nepritelManager.getNepratele());
            explosionManager.enemyCollide(nepritelManager.getNepratele());
            explosionManager.wallCollide(brickWallManager.getBrickWalls());
            if(!bomberMan.visible)
                gameOver = true;
            if(nepritelManager.getNepratele().size()<=0)
                gameVictory = true;

            bombManager.update();
            explosionManager.update();
            nepritelManager.update();
            bomberMan.update();

           // bomberMan.stop();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(spriteSheet, null, new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT), null);

        bomberMan.draw(canvas);
        //enemyManager.draw(canvas);
        ironWallManager.draw(canvas);
        brickWallManager.draw(canvas);
        nepritelManager.draw(canvas);
        bombManager.draw(canvas);
        explosionManager.draw(canvas);


        if (gameOver) {
            Paint p = new Paint(),d = new Paint();
            p.setTextSize(42f);
            p.setColor(Color.WHITE);
            d.setColor(Color.RED);
            canvas.drawRect(new Rect(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT), d);
            canvas.drawText("Game Over", Constants.SCREEN_WIDTH / 2.5f, Constants.SCREEN_HEIGHT / 2, p);
        }

        if (gameVictory) {
            Paint p = new Paint(),d = new Paint();
            p.setTextSize(42f);
            p.setColor(Color.WHITE);
            d.setColor(Color.GREEN);
            canvas.drawRect(new Rect(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT), d);
            canvas.drawText("YOU WON", Constants.SCREEN_WIDTH / 2.5f, Constants.SCREEN_HEIGHT / 2, p);
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void recievedTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
               /* if(!gameOver && bomberMan.getRectangle().contains((int)event.getX(), (int)event.getY()))
                    movingPlayer = true;
                if(gameOver && System.currentTimeMillis() - gameOverTime >= 2000){
                    reset();
                    gameOver = false;
                }*/
                if (event.getY() < Constants.SCREEN_HEIGHT / 4) {
                    bomberMan.walkUp();
                    direction = 1;
                }
                if (event.getY() > (Constants.SCREEN_HEIGHT / 4) * 3) {
                    bomberMan.walkDown();
                    direction = 3;
                }
                if (event.getX() < (Constants.SCREEN_WIDTH / 4)) {
                    bomberMan.walkLeft();
                    direction = 0;
                }
                if (event.getX() > (Constants.SCREEN_WIDTH / 4)*3) {
                    bomberMan.walkRight();
                    direction = 2;
                }


                break;
            case MotionEvent.ACTION_MOVE:
                bombManager.createBomb(Math.round(bomberMan.getCurrentPosition().x/(Constants.SCREEN_WIDTH/9))*(Constants.SCREEN_WIDTH/9),
                        Math.round(bomberMan.getCurrentPosition().y/(Constants.SCREEN_HEIGHT/ 15))*(Constants.SCREEN_HEIGHT/15));
                break;
            case MotionEvent.ACTION_UP:
                bomberMan.stop();
                break;
        }

    }
}
