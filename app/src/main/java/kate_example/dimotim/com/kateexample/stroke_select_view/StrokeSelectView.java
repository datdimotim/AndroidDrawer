package kate_example.dimotim.com.kateexample.stroke_select_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.function.Consumer;

public class StrokeSelectView extends LinearLayout {
    public static final int HEIGHT_IN_DP=30;
    private Consumer<Float> listener=(width)->{};
    private float strokeWidth=1;
    public StrokeSelectView(Context context) {
        super(context);
        init();
    }

    public StrokeSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StrokeSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public StrokeSelectView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setOnWidthChangeListener(Consumer<Float> onWidthChangeListener){
        this.listener=onWidthChangeListener;
        listener.accept(strokeWidth);
    }

    private int dpToPixels(int dp){
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private LinearLayout.LayoutParams initLayoutParams(int weight){
        LinearLayout.LayoutParams layoutParams= new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPixels(HEIGHT_IN_DP), weight);
        int pxMargins=dpToPixels(5);
        layoutParams.setMargins(pxMargins,pxMargins,pxMargins,pxMargins);
        return layoutParams;
    }

    private void init(){
        setOrientation(HORIZONTAL);
        SeekBar seekBar=new SeekBar(getContext());
        addView(seekBar,initLayoutParams(1));
        Preview preview=new Preview(getContext());
        addView(preview,initLayoutParams(3));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                strokeWidth=1+progress/2f;
                preview.onStrokeChanged(strokeWidth);
                listener.accept(strokeWidth);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekBar.setProgress(10);
    }

    private static class Preview extends View{
        private float stroke=0;
        private final Paint p=new Paint();
        public Preview(Context context) {
            super(context);
            setBackgroundColor(Color.LTGRAY);
        }

        void onStrokeChanged(float stroke){
            this.stroke=stroke;
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            p.setColor(Color.BLUE);
            p.setStrokeWidth(stroke);
            canvas.drawLine(
                    0,
                    canvas.getHeight()/2,
                    canvas.getWidth(),
                    canvas.getHeight()/2,
                    p
            );
        }
    }
}
