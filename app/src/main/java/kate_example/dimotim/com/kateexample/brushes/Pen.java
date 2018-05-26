package kate_example.dimotim.com.kateexample.brushes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import kate_example.dimotim.com.kateexample.drawer.BaseBrush;
import kate_example.dimotim.com.kateexample.drawer.DrawerParams;

import static android.graphics.Color.red;
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
    public void onTouch(MotionEvent event, DrawerParams params) {
        Canvas c = new Canvas(bm);
        Paint p=new Paint();
        p.setStrokeCap(Paint.Cap.ROUND);
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
                c.drawLine(xs, ys, event.getX(), event.getY(), p);
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
