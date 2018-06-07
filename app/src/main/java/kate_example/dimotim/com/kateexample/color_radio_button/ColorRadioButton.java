package kate_example.dimotim.com.kateexample.color_radio_button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

public class ColorRadioButton extends android.support.v7.widget.AppCompatRadioButton {
    private int color=0xFF999999;
    public ColorRadioButton(Context context) {
        super(context);
        init();
    }

    public ColorRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setColor(int color) {
        this.color = color;
        setButtonTintList(ColorStateList.valueOf(color));
    }

    private void init(){
        setColor(color);
    }

    public int getColor() {
        return color;
    }
}
