package ir.psoft.psoftcoolcontrolls.Widgets;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ir.psoft.psoftlayoutlib.helper.AndroidUtilities;
import ir.psoft.psoftlayoutlib.helper.LayoutHelper;

/**
 * Created by pouyadark on 11/17/18.
 */

public class BorderButton extends FrameLayout {
    int selectedtextcolor=0xffffffff;
    int interfaceColor;
    TextView textview;
    int backgroundColor;
    Drawable drawable;
    boolean drawborder=true;
    private String text;
    private Rect rect;
    private OnClickListener btnClickListener;

    public BorderButton(Context context,int interfaceColor,int backgroundColor,Drawable drawable) {
        super(context);
        this.interfaceColor = interfaceColor;
        this.backgroundColor = backgroundColor;
        this.drawable = drawable;
        init();

    }
    public BorderButton(Context context,int interfaceColor,int backgroundColor) {
        super(context);
        this.interfaceColor = interfaceColor;
        this.backgroundColor = backgroundColor;
        init();

    }

    public BorderButton(Context context, int interfaceColor, int backgroundColor, int selectedtextcolor) {
        super(context);
        this.interfaceColor = interfaceColor;
        this.backgroundColor = backgroundColor;
        this.selectedtextcolor=selectedtextcolor;
        init();
        updateColor();
    }

    public void ChangeColor(int interfaceColor, int backgroundColor, int selectedtextcolor){
        this.interfaceColor = interfaceColor;
        this.backgroundColor = backgroundColor;
        this.selectedtextcolor=selectedtextcolor;
        updateColor();
        setbackground(false);
    }

    private void updateColor() {
        textview.setTextColor(interfaceColor);

    }

    public void setBtnClickListener(OnClickListener l) {
        btnClickListener=l;
    }

    public void setTypeFace(Typeface typeFace){
        textview.setTypeface(typeFace);
    }
    private void init() {
        textview = new TextView(getContext());
        setbackground(false);
        textview.setGravity(Gravity.CENTER);

        textview.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        textview.setPadding(AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10));
        addView(textview, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));

        ImageView imageView =new ImageView(getContext());
        imageView.setImageDrawable(drawable);
        addView(imageView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.RIGHT|Gravity.CENTER_VERTICAL,0,10,10,0));

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        setbackground(true);
                        textview.setTextColor(BorderButton.this.selectedtextcolor);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        setbackground(false);
                        textview.setTextColor(BorderButton.this.interfaceColor);
                        if(rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
                            if(btnClickListener!=null){
                                btnClickListener.onClick(v);
                            }
                        }

                        break;
                    }
                    default:{
                        if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
                            setbackground(false);
                            textview.setTextColor(BorderButton.this.interfaceColor);
                        }else{
                            setbackground(true);
                            textview.setTextColor(BorderButton.this.selectedtextcolor);
                        }

                    }
                }
                return true;
            }
        });
        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    setbackground(true);
                    textview.setTextColor(BorderButton.this.backgroundColor);
                }else{
                    setbackground(false);
                    textview.setTextColor(BorderButton.this.interfaceColor);
                }
            }
        });
    }


    public void setDrawborder(boolean drawborder) {
        this.drawborder = drawborder;
        setbackground(false);
    }

    public void setbackground(boolean selected) {
        GradientDrawable border = new GradientDrawable();
        border.setColor(selected ? interfaceColor : backgroundColor); //white background
        border.setCornerRadius(AndroidUtilities.dp(4));
        if(drawborder) {
            border.setStroke(AndroidUtilities.dp(1), selected ? backgroundColor : interfaceColor); //black border with full opacity
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            textview.setBackgroundDrawable(border);
        } else {
            textview.setBackground(border);
        }
    }


    public void setText(String text) {
        textview.setText(text);
    }

    public void setTypeface(Typeface typeface) {
        textview.setTypeface(typeface);
    }

    public void setTextSize(int complexUnitSp, int i) {
        textview.setTypeface(Typeface.defaultFromStyle(complexUnitSp),i);
    }
    public void setPadding(int left,int top,int right,int bottom){
        textview.setPadding(left,top,right,bottom);
    }
}
