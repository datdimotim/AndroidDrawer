package kate_example.dimotim.com.kateexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class DisplayView extends View {
    // эта картинка редактируется извне//
    private final Bitmap bm;

    public DisplayView(Context context, Bitmap bm) {
        super(context);
        this.bm=bm;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bm==null)return;
        canvas.drawBitmap(bm,0,0,null);
    }
    @Override
    public boolean performClick() {
        super.performClick();
        return false;
    }
}
