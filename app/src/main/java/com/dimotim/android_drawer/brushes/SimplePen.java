package com.dimotim.android_drawer.brushes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class SimplePen extends AbstractPen {
    @Override
    protected void draw(Canvas c, float xs, float ys, float x, float y, Paint paint) {
        c.drawLine(xs,ys,x,y,paint);
    }
}
