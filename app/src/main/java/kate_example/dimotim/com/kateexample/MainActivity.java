package kate_example.dimotim.com.kateexample;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import kate_example.dimotim.com.kateexample.brushes.Pen;
import kate_example.dimotim.com.kateexample.brushes.RectangleBrush;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAIN_ACTIVITY:";

    // наша картинка в памяти //
    private Bitmap bm = null;
    Drawer.DrawerParams params=new Drawer.DrawerParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout f = findViewById(R.id.frame);
        f.addView(new MeasureView(this,this::onBitmapSizeMesured));
    }

    void onBitmapSizeMesured(int w,int h){
        FrameLayout f=findViewById(R.id.frame);
        f.removeAllViews();
        Log.i(TAG,w+" "+h);
        bm=Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
        final DisplayView mv = new DisplayView(this);
        f.addView(mv);
        registerColorListeners();
        Drawer drawer=new Drawer(bm,mv,mv,()->params);
        drawer.setBrush(new RectangleBrush());

        findViewById(R.id.penRadioButton).setOnClickListener(v->drawer.setBrush(new Pen()));
        findViewById(R.id.rectRadioButton).setOnClickListener(v->drawer.setBrush(new RectangleBrush()));
        findViewById(R.id.button).setOnClickListener(v -> drawer.clear(Color.BLACK));
        ((SeekBar)findViewById(R.id.seekBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                params=params.setStrokeWidth(50f*progress/100+1);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    void registerColorListeners(){
        findViewById(R.id.whiteButton)    .setOnClickListener((e)->params=params.setColor(0xffffffff));
        findViewById(R.id.blueButton)     .setOnClickListener((e)->params=params.setColor(0xff0000ff));
        findViewById(R.id.greenButton)    .setOnClickListener((e)->params=params.setColor(0xff00ff00));
        findViewById(R.id.redButton)      .setOnClickListener((e)->params=params.setColor(0xffff0000));
        findViewById(R.id.yellowButton)   .setOnClickListener((e)->params=params.setColor(0xffffff00));
        findViewById(R.id.blueWhiteButton).setOnClickListener((e)->params=params.setColor(0xff00ffff));
        findViewById(R.id.pinkButton)     .setOnClickListener((e)->params=params.setColor(0xffff00ff));
    }
}
