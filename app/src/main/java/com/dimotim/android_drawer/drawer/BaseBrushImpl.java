package com.dimotim.android_drawer.drawer;

import android.graphics.Bitmap;
import android.view.MotionEvent;

class BaseBrushImpl extends BaseBrush{
    @Override
    protected void onTouch(MotionEvent event, DrawerParams params) { }
    @Override
    protected void onBitmapChanged(Bitmap bitmap) { }
}
