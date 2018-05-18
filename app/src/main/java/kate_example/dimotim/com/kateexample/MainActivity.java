package kate_example.dimotim.com.kateexample;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import static android.graphics.Color.BLACK;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAIN_ACTIVITY:";

    // наша картинка в памяти //
    private Bitmap bm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout f = findViewById(R.id.frame);
        f.addView(new MesureView(this,this::onBitmapSizeMesured));
    }

    void onBitmapSizeMesured(int w,int h){
        FrameLayout f=findViewById(R.id.frame);
        f.removeAllViews();
        Log.i(TAG,w+" "+h);
        bm=Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
        final View mv = new DisplayView(this, bm);
        f.addView(mv);
        mv.setOnTouchListener(new OnTouchDrawer(bm, mv));
        findViewById(R.id.button).setOnClickListener(v -> new Canvas(bm).drawColor(BLACK));
    }
}

