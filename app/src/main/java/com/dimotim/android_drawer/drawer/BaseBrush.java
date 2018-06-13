package com.dimotim.android_drawer.drawer;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.MotionEvent;

public abstract class BaseBrush {
    private Drawer drawer;

    void bind(Drawer drawer){
        this.drawer=drawer;
    }
    protected void commitImage(Bitmap bitmap){
        drawer.onCommitImage(bitmap);
    }
    protected void updatePreview(Bitmap bitmap) {
        drawer.onPreviewUpdated(bitmap);
    }
    protected abstract void onTouch(MotionEvent event, Paint paint);
    protected abstract void onBitmapChanged(Bitmap bitmap);
}
