package ir.psoft.psoftcoolcontrolls.Widgets;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ir.psoft.psoftcoolcontrolls.Adapter.CoolTabBarAdapter;
import ir.psoft.psoftcoolcontrolls.Models.TabModel;
import ir.psoft.psoftcoolcontrolls.R;
import ir.psoft.psoftcoolcontrolls.SpacesItemDecoration;
import ir.psoft.psoftcoolcontrolls.helper.CoolTabBarSetting;
import ir.psoft.psoftlayoutlib.helper.AndroidUtilities;
import ir.psoft.psoftlayoutlib.helper.LayoutHelper;

/**
 * Created by pouyadark on 11/28/18.
 */

public class CoolTabBar extends FrameLayout {
    private boolean isactionbar=false;
    private LinearLayout linearlayout;
    private ImageView backImg;
    private CardView cardView;
    private FrameLayout frmrecycler;

    public void makeItActionbar() {
        if(isactionbar)return;
        isactionbar=true;
        adapter.setIsActionbar(true);
        backImg.setVisibility(VISIBLE);
        cardView.setRadius(0);

        recyclerView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.MATCH_PARENT,Gravity.RIGHT));
        cardView.setUseCompatPadding(false);
        int sizefrom = 100;
        int sizeto = 51;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new FloatEvaluator(), sizefrom, sizeto);
        colorAnimation.setDuration(250); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, (Float) animator.getAnimatedValue()));
            }

        });
        colorAnimation.start();
    }

    public void unActionbar() {
        if(!isactionbar)return;
        isactionbar=false;
//        cardView.setRadius(AndroidUtilities.dp7);
//        cardView.setUseCompatPadding(true);
        recyclerView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
        adapter.setIsActionbar(false);
        backImg.setVisibility(GONE);
        int sizefrom = 51;
        int sizeto = 100;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new FloatEvaluator(), sizefrom, sizeto);
        colorAnimation.setDuration(250); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, (Float) animator.getAnimatedValue()));
            }

        });
        colorAnimation.start();
//        setBackgroundColor(coolTabBarSetting.getNormalBackGroundColor());
    }

    public interface CoolTabBarEvents{
        void onTabChanged(TabModel currenttab);
        void onBack();
    }

    public ArrayList<TabModel> tabModels=new ArrayList<>();
    public RecyclerView recyclerView;
    private CoolTabBarSetting coolTabBarSetting =new CoolTabBarSetting();
    private CoolTabBarAdapter adapter;
    private SpacesItemDecoration itemdecoration;


    public void setTabBarEvents(CoolTabBarEvents tabBarEvents) {
        adapter.setTabBarEvents(tabBarEvents);
    }


    public CoolTabBar( Context context) {
        super(context);
        init();
    }

    public CoolTabBar( Context context, CoolTabBarSetting coolTabBarSetting) {
        super(context);
        init();
        this.setCoolTabBarSetting(coolTabBarSetting);

    }

    public void setCoolTabBarSetting(CoolTabBarSetting coolTabBarSetting) {
        this.coolTabBarSetting = coolTabBarSetting;
//        recyclerView.setBackgroundColor(coolTabBarSetting.getNormalBackGroundColor());
//        GradientDrawable gradientDrawable=new GradientDrawable(GradientDrawable.Orientation.BL_TR,
//                new int[]{coolTabBarSetting.getNormalBackGroundColor(),coolTabBarSetting.getNormalBackGroundColor()});
//        gradientDrawable.setCornerRadius(AndroidUtilities.dp4);
        cardView.setCardBackgroundColor(coolTabBarSetting.getNormalBackGroundColor());
        int tabspacedp=AndroidUtilities.dp(coolTabBarSetting.SpaceBetweenTabsDp);
        itemdecoration.setagain(tabspacedp,AndroidUtilities.dp5,AndroidUtilities.dp2,0);
        backImg.setImageDrawable(changeColor(coolTabBarSetting.getBackDrawble(),0xffffffff));
        recyclerView.invalidateItemDecorations();

    }

    public void setTabModels(ArrayList<TabModel> tabModels) {
        this.tabModels = tabModels;
        adapter.setSetting(coolTabBarSetting);
        adapter.setTabModels(tabModels);
    }

    private void init() {
        cardView =new CardView(getContext());
//        cardView.setUseCompatPadding(true);
//        cardView.setRadius(AndroidUtilities.dp7);
        linearlayout=new LinearLayout(getContext());
        linearlayout.setGravity(Gravity.RIGHT);
        linearlayout.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,true));
        recyclerView.setNestedScrollingEnabled(true);
        int tabspacedp=AndroidUtilities.dp(coolTabBarSetting.SpaceBetweenTabsDp);
        itemdecoration = new SpacesItemDecoration(tabspacedp,AndroidUtilities.dp5,AndroidUtilities.dp2,0);
        adapter=new CoolTabBarAdapter(getContext());
        backImg=new ImageView(getContext());
        backImg.setVisibility(GONE);
        backImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getTabBarEvents()!=null)adapter.getTabBarEvents().onBack();
            }
        });
        frmrecycler=new FrameLayout(getContext());
        frmrecycler.addView(recyclerView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
        linearlayout.addView(frmrecycler,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
        linearlayout.addView(backImg,LayoutHelper.createLinear(30,LayoutHelper.MATCH_PARENT,Gravity.CENTER_VERTICAL,5,5,10,5));
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(itemdecoration);
        cardView.addView(linearlayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
        addView(cardView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
        setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,100));

    }
    public static Drawable changeColor(Drawable drawable, int Color){
        if(drawable==null)return null;
        PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;
        drawable.setColorFilter(Color,mMode);
        return drawable;
    }
}
