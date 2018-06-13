package com.dimotim.android_drawer.brushes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.dimotim.android_drawer.drawer.BaseBrush;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class Pen extends BaseBrush {
    private Bitmap bm=null;
    private boolean isPressed = false;
    private float xs = 0;
    private float ys = 0;

    @Override
    public void onBitmapChanged(Bitmap bitmap){
        bm=bitmap;
    }

    @Override
    public void onTouch(MotionEvent event, Paint paint) {
        Canvas c = new Canvas(bm);
        switch (event.getAction()) {
            case ACTION_DOWN:
                isPressed = true;
                xs = event.getX();
                ys = event.getY();
                return;
            case ACTION_MOVE:
                if (!isPressed) return;
                c.drawLine(xs, ys, event.getX(), event.getY(), paint);
                updatePreview(bm);
                xs = event.getX();
                ys = event.getY();
                return;
            case ACTION_UP:
                if(!isPressed)return;
                isPressed = false;
                commitImage(bm);
                return;
        }
    }
}
