package ir.psoft.psoftcoolcontrolls.Widgets;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import ir.psoft.psoftlayoutlib.helper.AndroidUtilities;

/**
 * Created by pouyadark on 12/4/18.
 */

public class TEditText extends AppCompatEditText {
    private int backgroundcolor=0x55353b48;
    private int inputradius=AndroidUtilities.dp(10);

    public TEditText(Context context) {
        super(context);
        init();
    }
    public TEditText(Context context,int backgroundcolor,int inputradius) {
        super(context);
        this.backgroundcolor=backgroundcolor;
        this.inputradius=inputradius;
        init();
    }
    public TEditText(Context context,int backgroundcolor) {
        super(context);
        this.backgroundcolor=backgroundcolor;
        init();
    }

    public TEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setColor(backgroundcolor);
        setPadding(AndroidUtilities.dp15,AndroidUtilities.dp10,AndroidUtilities.dp15,AndroidUtilities.dp10);
        gradientDrawable.setCornerRadius(inputradius);
        setTextColor(0xffffffff);
        setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        setHintTextColor(0xaaffffff);
        setBackgroundDrawable(gradientDrawable);
    }
}
