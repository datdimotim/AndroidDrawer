package kate_example.dimotim.com.kateexample;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.function.BiConsumer;

public class MeasureView extends View {
    private boolean mesured=false;
    private final BiConsumer<Integer,Integer> listener;
    public MeasureView(Context context, BiConsumer<Integer,Integer> listener) {
        super(context);
        this.listener=listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mesured)return;
        mesured=true;
        listener.accept(canvas.getWidth(),canvas.getHeight());
    }
}
