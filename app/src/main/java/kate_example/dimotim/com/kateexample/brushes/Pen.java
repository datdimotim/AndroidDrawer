package kate_example.dimotim.com.kateexample.brushes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import kate_example.dimotim.com.kateexample.Drawer;

import static android.graphics.Color.BLUE;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;
import static kate_example.dimotim.com.kateexample.MainActivity.TAG;

public class Pen extends Drawer.BaseBrush {
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
                break;
            case ACTION_MOVE:
                if (!isPressed) break;
                c.drawLine(xs, ys, event.getX(), event.getY(), p);
                commitImage(bm);
                xs = event.getX();
                ys = event.getY();
                break;
            case ACTION_UP:
                isPressed = false;
                commitImage(bm);
                break;
        }
    }
}
