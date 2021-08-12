package ir.psoft.psoftcoolcontrolls.Widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;
import com.wang.avi.indicators.LineScalePulseOutRapidIndicator;

import ir.psoft.psoftcoolcontrolls.helper.CircleTransform;
import ir.psoft.psoftlayoutlib.helper.LayoutHelper;


/**
 * Created by pouyadark on 11/22/18.
 */

public class CircleLoadingImageView extends FrameLayout {
    private AVLoadingIndicatorView progressbar;
    private CircleImageView imageView;
    private TextView txterror;
    private Indicator indicator;
    private String defaulterror;
    public static final int LOADING_IMAGE_VIEW_BACKGROUND = 0xffa4b0be;
    public static final int LOADING_IMAGE_VIEW_ERROR_COLOR = 0xffffffff;

    public CircleLoadingImageView(Context context, Indicator indicator, String defaulterror) {
        super(context);
        this.indicator=indicator;
        this.defaulterror=defaulterror;
        init();
    }
    public CircleLoadingImageView(Context context, Indicator indicator) {
        super(context);
        this.indicator=indicator;
        init();
    }
    public CircleLoadingImageView(Context context) {
        super(context);
        indicator =new LineScalePulseOutRapidIndicator();
        init();
    }

    public void setTypeFace(Typeface typeFace){
        txterror.setTypeface(typeFace);
    }
    private void init() {
//        removeAllViews();
        imageView=new CircleImageView(getContext());
        imageView.setCircleBackgroundColor(LOADING_IMAGE_VIEW_BACKGROUND);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(imageView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
        progressbar = new AVLoadingIndicatorView(getContext());
        progressbar.setVisibility(VISIBLE);
        progressbar.setIndicator(indicator);

        txterror=new TextView(getContext());
        txterror.setTextColor(LOADING_IMAGE_VIEW_ERROR_COLOR);
        txterror.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        txterror.setText("عکس پیدا نشد");
        txterror.setVisibility(INVISIBLE);

        addView(txterror,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
        addView(progressbar,LayoutHelper.createFrame(60,60, Gravity.CENTER));
    }
    public void setPic(String path){
        progressbar.setVisibility(VISIBLE);
        Picasso.get().load(path).transform(new CircleTransform()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressbar.setVisibility(INVISIBLE);

                txterror.setVisibility(INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                progressbar.setVisibility(INVISIBLE);
                txterror.setVisibility(VISIBLE);
                if(defaulterror!=null) {
                    txterror.setText(defaulterror);
                }
            }
        });
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.imageView.setScaleType(scaleType);

    }

    public void setImageURI(Uri selectedImage) {
        this.imageView.setImageURI(selectedImage);
    }
}
