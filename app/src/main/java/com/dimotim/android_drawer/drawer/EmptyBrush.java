package com.dimotim.android_drawer.drawer;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.MotionEvent;

public class EmptyBrush extends BaseBrush{
    @Override
    protected void onTouch(MotionEvent event, Paint paint) { }
    @Override
    protected void onBitmapChanged(Bitmap bitmap) { }
}
