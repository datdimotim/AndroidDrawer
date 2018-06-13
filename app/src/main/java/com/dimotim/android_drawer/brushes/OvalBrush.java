package com.dimotim.android_drawer.brushes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class OvalBrush extends AbstractRectangleBrush {
    @Override
    protected void draw(Canvas c, float xs, float ys, float x, float y, Paint paint) {
        c.drawOval(xs,ys,x,y,paint);
    }
}
