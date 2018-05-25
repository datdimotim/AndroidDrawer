package kate_example.dimotim.com.kateexample.brushes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import kate_example.dimotim.com.kateexample.Drawer;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class RectangleBrush extends Drawer.BaseBrush {
    private boolean isPressed = false;
    private float xs = 0;
    private float ys = 0;

    @Override
    public void onTouch(MotionEvent event, Bitmap bm, Drawer.DrawerParams params) {
        Canvas c = new Canvas(bm);
        Paint p=new Paint();
        p.setStrokeWidth(params.strokeWidth);
        p.setColor(params.color);
        switch (event.getAction()) {
            case ACTION_DOWN:
                isPressed = true;
                xs = event.getX();
                ys = event.getY();
                return;
            case ACTION_MOVE:
                if (!isPressed) return;
                c.drawRect(xs,ys,event.getX(),event.getY(),p);
                updatePreview(bm);
                return;
            case ACTION_UP:
                if(!isPressed)return;
                isPressed = false;
                c.drawRect(xs,ys,event.getX(),event.getY(),p);
                commitImage(bm);
                return;
        }
    }
}
