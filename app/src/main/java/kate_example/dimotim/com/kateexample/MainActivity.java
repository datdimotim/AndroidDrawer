package kate_example.dimotim.com.kateexample;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import kate_example.dimotim.com.kateexample.brushes.Pen;
import kate_example.dimotim.com.kateexample.brushes.RectangleBrush;
import kate_example.dimotim.com.kateexample.display_view.DisplayView;
import kate_example.dimotim.com.kateexample.drawer.Drawer;
import kate_example.dimotim.com.kateexample.drawer.DrawerParams;
import kate_example.dimotim.com.kateexample.measure_view.MeasureView;
import kate_example.dimotim.com.kateexample.stroke_select_view.StrokeSelectView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAIN_ACTIVITY:";

    DrawerParams params=new DrawerParams();

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
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        final DisplayView mv = new DisplayView(this);
        f.addView(mv);

        Drawer drawer=new Drawer(bm,mv,mv,()->params);
        registerListeners(drawer);
        bindUIControlsWithDrawerParams();
    }

    void bindUIControlsWithDrawerParams(){
        findViewById(R.id.blueButton).performClick();
        findViewById(R.id.penRadioButton).performClick();
    }

    void registerListeners(Drawer drawer){
        registerBrushListeners(drawer);
        registerColorListeners();

        findViewById(R.id.button).setOnClickListener(v -> drawer.clear(Color.BLACK));
        ((StrokeSelectView)findViewById(R.id.strokeSeekBar)).setOnWidthChangeListener(width->params=params.setStrokeWidth(width));

        findViewById(R.id.backButton).setOnClickListener((e)->drawer.back());
        findViewById(R.id.nextButton).setOnClickListener((e)->drawer.next());
    }

    void registerBrushListeners(Drawer drawer){
        findViewById(R.id.penRadioButton).setOnClickListener(v->drawer.setBrush(new Pen()));
        findViewById(R.id.rectRadioButton).setOnClickListener(v->drawer.setBrush(new RectangleBrush()));
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
