package kate_example.dimotim.com.kateexample;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.graphics.Color.BLUE;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;
import static kate_example.dimotim.com.kateexample.MainActivity.TAG;

public class OnTouchDrawer implements View.OnTouchListener {
    // Картинка на которой рисуем
    private final Bitmap bm;

    // Окно которое пререрисовываем
    private final View mv;

    private final Paint p = new Paint();

    private boolean isPressed = false;
    private float xs = 0;
    private float ys = 0;

    public OnTouchDrawer(Bitmap bm, View mv) {
        this.bm = bm;
        this.mv = mv;
        p.setColor(BLUE);
        p.setStrokeWidth(10);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.performClick();
        Log.i(TAG, "handle");
        Canvas c = new Canvas(bm);
        switch (event.getAction()) {
            case ACTION_DOWN:
                Log.i(TAG, "down");
                isPressed = true;
                xs = event.getX();
                ys = event.getY();
                break;
            case ACTION_MOVE:
                if (!isPressed) break;
                c.drawLine(xs, ys, event.getX(), event.getY(), p);
                c.save();
                mv.invalidate();
                Log.i(TAG, xs + " " + ys + "  |  " + event.getX() + " " + event.getY());
                xs = event.getX();
                ys = event.getY();
                break;
            case ACTION_UP:
                Log.i(TAG, "up");
                isPressed = false;
                break;
        }
        return true;
    }
}
