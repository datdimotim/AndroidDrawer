package kate_example.dimotim.com.kateexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.function.Supplier;

public class DisplayView extends View {
    // эта картинка редактируется извне//
    private @NonNull Bitmap bm=Bitmap.createBitmap(10,10, Bitmap.Config.RGB_565);

    public DisplayView(Context context) {
        super(context);
    }

    public void updateImage(Bitmap bm){
        if(this.bm.getWidth()!=bm.getWidth() || this.bm.getHeight()!=bm.getHeight()) {
            this.bm.recycle();
            this.bm=Bitmap.createBitmap(bm.getWidth(),bm.getHeight(), Bitmap.Config.RGB_565);
        }
        new Canvas(this.bm).drawBitmap(bm,0,0,null);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bm,0,0,null);
    }
    @Override
    public boolean performClick() {
        super.performClick();
        return false;
    }
}
