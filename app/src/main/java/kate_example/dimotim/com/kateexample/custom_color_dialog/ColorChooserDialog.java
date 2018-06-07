package kate_example.dimotim.com.kateexample.custom_color_dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.function.Consumer;

public class ColorChooserDialog extends LinearLayout {
    private SeekBar hueSeek;
    private SeekBar valSeek;
    private SeekBar satSeek;
    private View preview;
    public static void run(Context context, Consumer<Integer> onResultListener, int startColor){
        ColorChooserDialog colorChooserDialog =new ColorChooserDialog(context,startColor);
        new AlertDialog.Builder(context)
                .setTitle("Choose color")
                .setView(colorChooserDialog)
                .setPositiveButton("OK", (e,w)->{onResultListener.accept(colorChooserDialog.getCurrentColor());})
                .setNegativeButton("CANCEL",(e,w)->{onResultListener.accept(null);})
                .create()
                .show();
    }

    private ColorChooserDialog(Context context, int startColor) {
        super(context);
        init(startColor);
    }

    private int dpToPixels(int dp){
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private LinearLayout.LayoutParams initLayoutParamsVer(float weight){
        LinearLayout.LayoutParams layoutParams= new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,weight);
        int pxMargins=dpToPixels(5);
        layoutParams.setMargins(pxMargins,pxMargins,pxMargins,pxMargins);
        return layoutParams;
    }

    private LinearLayout.LayoutParams initLayoutParamsHor(float weight){
        LinearLayout.LayoutParams layoutParams= new LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT,weight);
        int pxMargins=dpToPixels(5);
        layoutParams.setMargins(pxMargins,pxMargins,pxMargins,pxMargins);
        return layoutParams;
    }

    private void init(int startColor){
        setOrientation(VERTICAL);
        setBackgroundColor(Color.BLACK);
        setWeightSum(9);
        preview=new View(getContext());
        preview.setBackgroundColor(Color.GREEN);
        addView(preview, initLayoutParamsVer(6));
        hueSeek=new SeekBar(getContext());hueSeek.setMax(360);
        satSeek=new SeekBar(getContext());satSeek.setMax(100);
        valSeek=new SeekBar(getContext());valSeek.setMax(100);
        addView(hueSeek, initLayoutParamsVer(1));
        addView(satSeek, initLayoutParamsVer(1));
        addView(valSeek, initLayoutParamsVer(1));
        setSeekbarListeners();
        bindToUI(startColor);
    }

    private void bindToUI(int startColor){
        float[] hsv=new float[3];
        Color.RGBToHSV(
                (startColor&0x00ff0000)>>>16,
                (startColor&0x0000ff00)>>>8,
                (startColor & 0x000000ff),
                hsv);
        hueSeek.setProgress((int) hsv[0]);
        satSeek.setProgress((int) (hsv[1]*100));
        valSeek.setProgress((int) (hsv[2]*100));
    }

    private int getCurrentColor(){
        return Color.HSVToColor(new float[]{hueSeek.getProgress(),satSeek.getProgress()/100f,valSeek.getProgress()/100f});
    }

    private void setSeekbarListeners(){
        class OnSeekBarChangeAdapter implements SeekBar.OnSeekBarChangeListener {
            private final Runnable listener;
            private OnSeekBarChangeAdapter(Runnable listener){
                this.listener=listener;
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                listener.run();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        }
        OnSeekBarChangeAdapter listener=new OnSeekBarChangeAdapter(()->{
            int rawColor=getCurrentColor();
            preview.setBackgroundColor(rawColor);
        });
        hueSeek.setOnSeekBarChangeListener(listener);
        satSeek.setOnSeekBarChangeListener(listener);
        valSeek.setOnSeekBarChangeListener(listener);
    }
}
