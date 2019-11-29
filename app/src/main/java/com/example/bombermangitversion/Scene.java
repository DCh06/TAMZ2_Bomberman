package com.example.bombermangitversion;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void recievedTouch(MotionEvent event);
}
