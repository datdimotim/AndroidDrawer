package com.dimotim.android_drawer.drawer;

import android.graphics.Color;

public class DrawerParams {
    public final int color;
    public final float strokeWidth;
    public DrawerParams(){
        this(Color.BLUE,10);
    }
    private DrawerParams(int color, float strokeWidth){
        this.color=color;
        this.strokeWidth=strokeWidth;
    }
    public DrawerParams setColor(int color){
        return new DrawerParams(color,strokeWidth);
    }
    public DrawerParams setStrokeWidth(float strokeWidth){
        return new DrawerParams(color,strokeWidth);
    }
}
