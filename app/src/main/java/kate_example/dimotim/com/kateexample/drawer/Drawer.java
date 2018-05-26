package kate_example.dimotim.com.kateexample.drawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.function.Supplier;

import kate_example.dimotim.com.kateexample.DisplayView;
import kate_example.dimotim.com.kateexample.MainActivity;

public class Drawer implements View.OnTouchListener{
    public static final int MAX=10;
    private ArrayList<Bitmap> imageList=new ArrayList<>(MAX);
    private int current=0;
    private final DisplayView displayView;
    private BaseBrush brush=new BaseBrushImpl();
    private final Supplier<DrawerParams> paramsController;

    public Drawer(Bitmap bitmap, DisplayView displayView, View onTouchable, Supplier<DrawerParams> paramsController) {
        this.displayView = displayView;
        this.paramsController=paramsController;
        onTouchable.setOnTouchListener(this);
        imageList.add(bitmap.copy(Bitmap.Config.RGB_565,true));
        this.displayView.updateImage(imageList.get(0));
    }

    public Bitmap getCurrentImage(){
        return imageList.get(current).copy(Bitmap.Config.RGB_565,true);
    }

    public void back(){
        if(current<=0)return;
        current--;
        brush.onBitmapChanged(imageList.get(current).copy(Bitmap.Config.RGB_565,true));
        displayView.updateImage(imageList.get(current));
        Log.i(MainActivity.TAG,"current="+(current+1)+" of "+imageList.size());
    }

    public void next(){
        if(current>=imageList.size()-1)return;
        current++;
        brush.onBitmapChanged(imageList.get(current).copy(Bitmap.Config.RGB_565,true));
        displayView.updateImage(imageList.get(current));
        Log.i(MainActivity.TAG,"current="+(current+1)+" of "+imageList.size());
    }

    public int current(){
        return current;
    }

    public int total(){
        return imageList.size();
    }

    public void setBrush(BaseBrush brush){
        this.brush = brush;
        brush.bind(this);
        brush.onBitmapChanged(imageList.get(current).copy(Bitmap.Config.RGB_565,true));
    }

    public void clear(int color){
        Bitmap bitmap=imageList.get(current).copy(Bitmap.Config.RGB_565,true);
        new Canvas(bitmap).drawColor(color);
        brush.onBitmapChanged(bitmap.copy(Bitmap.Config.RGB_565,true));
        onCommitImage(bitmap);
    }

    void onCommitImage(Bitmap bitmap){
        for(int i=current+1;i<imageList.size();){
            if(imageList.get(i)!=null){
                imageList.get(i).recycle();
                imageList.remove(i);
            }
        }

        if(imageList.size()==MAX)imageList.remove(0);
        else current++;

        imageList.add(bitmap.copy(Bitmap.Config.RGB_565,true));
        Log.i(MainActivity.TAG,"current="+(current+1)+" of "+imageList.size());
        displayView.updateImage(imageList.get(current));
    }

    void onPreviewUpdated(Bitmap bitmap){
        displayView.updateImage(bitmap);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.performClick();
        brush.onTouch(event, paramsController.get());
        return true;
    }
}

