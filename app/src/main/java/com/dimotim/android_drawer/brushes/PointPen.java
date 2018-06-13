package com.dimotim.android_drawer.brushes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PointPen extends AbstractPen {
    @Override
    protected void draw(Canvas c, float xs, float ys, float x, float y, Paint paint) {
        c.drawPoint(x,y,paint);
    }
}
