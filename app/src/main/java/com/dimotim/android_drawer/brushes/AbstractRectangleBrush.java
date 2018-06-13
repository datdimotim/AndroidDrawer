package com.dimotim.android_drawer.brushes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.dimotim.android_drawer.drawer.BaseBrush;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public abstract class AbstractRectangleBrush extends BaseBrush {
    private Bitmap bm=null;
    private Bitmap cashed=null;
    private boolean isPressed = false;
    private float xs = 0;
    private float ys = 0;

    protected abstract void draw(Canvas c, float xs,float ys,float x,float y,Paint paint);

    @Override
    public void onTouch(MotionEvent event, Paint paint) {
        switch (event.getAction()) {
            case ACTION_DOWN: {
                isPressed = true;
                xs = event.getX();
                ys = event.getY();
                return;
            }
            case ACTION_MOVE:{
                if (!isPressed) return;
                Canvas c = new Canvas(cashed);
                c.drawBitmap(bm,0,0,null);
                draw(c,xs,ys,event.getX(),event.getY(),paint);
                updatePreview(cashed);
                return;
            }
            case ACTION_UP: {
                if (!isPressed) return;
                isPressed = false;
                Canvas c = new Canvas(bm);
                draw(c,xs,ys,event.getX(),event.getY(),paint);
                commitImage(bm);
                return;
            }
        }
    }

    @Override
    protected void onBitmapChanged(Bitmap bitmap) {
        bm=bitmap;
        cashed=bm.copy(Bitmap.Config.RGB_565,true);
    }
}
