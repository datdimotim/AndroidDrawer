package com.dimotim.android_drawer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.dimotim.android_drawer.brushes.Pen;
import com.dimotim.android_drawer.brushes.RectangleBrush;
import com.dimotim.android_drawer.color_radio_button.ColorRadioButton;
import com.dimotim.android_drawer.custom_color_dialog.ColorChooserDialog;
import com.dimotim.android_drawer.display_view.DisplayView;
import com.dimotim.android_drawer.drawer.Drawer;
import com.dimotim.android_drawer.measure_view.MeasureView;
import com.dimotim.android_drawer.stroke_select_view.StrokeSelectView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAIN_ACTIVITY:";

    private Paint paint=new Paint();

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

        Drawer drawer=new Drawer(bm,mv,mv,()->new Paint(paint));
        registerListeners(drawer);
        bindUIControlsWithDrawerParams();
    }

    void bindUIControlsWithDrawerParams(){
        paint.setStrokeCap(Paint.Cap.ROUND);
        findViewById(R.id.blueButton).performClick();
        findViewById(R.id.penRadioButton).performClick();
        findViewById(R.id.strokeStyleRadioButton).performClick();
    }

    void registerListeners(Drawer drawer){
        registerBrushListeners(drawer);
        registerColorListeners();
        registerStrokeStyleListeners();

        findViewById(R.id.button).setOnClickListener(v -> drawer.clear(Color.BLACK));
        ((StrokeSelectView)findViewById(R.id.strokeSeekBar)).setOnWidthChangeListener(width->paint.setStrokeWidth(width));

        findViewById(R.id.backButton).setOnClickListener((e)->drawer.back());
        findViewById(R.id.nextButton).setOnClickListener((e)->drawer.next());
    }

    void registerBrushListeners(Drawer drawer){
        findViewById(R.id.penRadioButton).setOnClickListener(v->drawer.setBrush(new Pen()));
        findViewById(R.id.rectRadioButton).setOnClickListener(v->drawer.setBrush(new RectangleBrush()));
    }

    void registerStrokeStyleListeners(){
        findViewById(R.id.strokeStyleRadioButton).setOnClickListener(v->paint.setStyle(Paint.Style.STROKE));
        findViewById(R.id.fillStyleRadioButton).setOnClickListener(v->paint.setStyle(Paint.Style.FILL));
    }

    void registerColorListeners(){
        View[] prevSelected=new View[1];
        findViewById(R.id.whiteButton).setOnClickListener((e)->{
            paint.setColor(0xffffffff);
            prevSelected[0]=e;
        });
        findViewById(R.id.blueButton).setOnClickListener((e)->{
            paint.setColor(0xff0000ff);
            prevSelected[0]=e;
        });
        findViewById(R.id.greenButton).setOnClickListener((e)->{
            paint.setColor(0xff00ff00);
            prevSelected[0]=e;
        });
        findViewById(R.id.redButton).setOnClickListener((e)->{
            paint.setColor(0xffff0000);
            prevSelected[0]=e;
        });
        findViewById(R.id.yellowButton).setOnClickListener((e)->{
            paint.setColor(0xffffff00);
            prevSelected[0]=e;
        });
        findViewById(R.id.blueWhiteButton).setOnClickListener((e)->{
            paint.setColor(0xff00ffff);
            prevSelected[0]=e;
        });
        findViewById(R.id.pinkButton).setOnClickListener((e)->{
            paint.setColor(0xffff00ff);
            prevSelected[0]=e;
        });
        ColorRadioButton colorRadioButton=findViewById(R.id.customColorButton);
        colorRadioButton.setOnClickListener(
                e->ColorChooserDialog.run(
                        this,
                        (c)-> {
                            if(c!=null) {
                                paint.setColor(c);
                                colorRadioButton.setColor(c);
                                prevSelected[0]=e;
                            }
                            else {
                                if(colorRadioButton!=prevSelected[0])
                                    prevSelected[0].performClick();
                            }
                        },
                        colorRadioButton.getColor()
                )
        );
    }
}
