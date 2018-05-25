package kate_example.dimotim.com.kateexample;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import java.util.function.Supplier;

public class Drawer implements View.OnTouchListener{
    private @NonNull Bitmap bitmap;
    private final DisplayView displayView;
    private BaseBrush brush=new BaseBrush();
    private final Supplier<DrawerParams> paramsController;

    public Drawer(Bitmap bitmap, DisplayView displayView, View onTouchable, Supplier<DrawerParams> paramsController) {
        this.displayView = displayView;
        this.paramsController=paramsController;
        onTouchable.setOnTouchListener(this);
        this.bitmap = bitmap.copy(Bitmap.Config.RGB_565,true);
        this.displayView.updateImage(this.bitmap);
    }

    public Bitmap getCurrentImage(){
        return bitmap.copy(Bitmap.Config.RGB_565,true);
    }

    public void setBrush(BaseBrush brush){
        this.brush = brush;
        brush.bind(this, bitmap);
    }

    public void clear(int color){
        new Canvas(bitmap).drawColor(color);
        onCommitImage(bitmap);
        onPreviewUpdated(bitmap);
    }

    void onCommitImage(Bitmap bitmap){
        this.bitmap =bitmap.copy(Bitmap.Config.RGB_565,true);
    }

    void onPreviewUpdated(Bitmap bitmap){
        displayView.updateImage(bitmap);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.performClick();
        brush.onTouch(event, bitmap.copy(Bitmap.Config.RGB_565,true),paramsController.get());
        return true;
    }

    public static class DrawerParams{
        public final int color;
        public final float strokeWidth;
        DrawerParams(){
            this(Color.BLUE,10);
        }
        DrawerParams(int color,float strokeWidth){
            this.color=color;
            this.strokeWidth=strokeWidth;
        }
        DrawerParams setColor(int color){
            return new DrawerParams(color,strokeWidth);
        }
        DrawerParams setStrokeWidth(float strokeWidth){
            return new DrawerParams(color,strokeWidth);
        }
    }
    public static class BaseBrush {
        private Drawer drawer;

        void bind(Drawer drawer,Bitmap bitmap){
            this.drawer=drawer;
        }
        protected void commitImage(Bitmap bitmap){
            updatePreview(bitmap);
            drawer.onCommitImage(bitmap);
        }
        protected void updatePreview(Bitmap bitmap) {
            drawer.onPreviewUpdated(bitmap);
        }
        protected void onTouch(MotionEvent event, Bitmap bitmap, Drawer.DrawerParams params){}

    }
}

