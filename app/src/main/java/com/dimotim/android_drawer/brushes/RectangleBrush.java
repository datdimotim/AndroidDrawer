package com.dimotim.android_drawer.brushes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.dimotim.android_drawer.drawer.BaseBrush;
import com.dimotim.android_drawer.drawer.DrawerParams;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class RectangleBrush extends BaseBrush {
    private Bitmap bm=null;
    private Bitmap cashed=null;
    private boolean isPressed = false;
    private float xs = 0;
    private float ys = 0;

    @Override
    public void onTouch(MotionEvent event, DrawerParams params) {
        Paint p=new Paint();
        p.setStrokeWidth(params.strokeWidth);
        p.setColor(params.color);
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
                c.drawRect(xs,ys,event.getX(),event.getY(),p);
                updatePreview(cashed);
                return;
            }
            case ACTION_UP: {
                if (!isPressed) return;
                isPressed = false;
                Canvas c = new Canvas(bm);
                c.drawRect(xs, ys, event.getX(), event.getY(), p);
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
