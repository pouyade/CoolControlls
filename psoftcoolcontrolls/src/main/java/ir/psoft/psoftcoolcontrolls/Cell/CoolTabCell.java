package ir.psoft.psoftcoolcontrolls.Cell;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import ir.psoft.psoftcoolcontrolls.Models.TabModel;
import ir.psoft.psoftcoolcontrolls.helper.CoolTabBarSetting;
import ir.psoft.psoftlayoutlib.helper.AndroidUtilities;
import ir.psoft.psoftlayoutlib.helper.LayoutHelper;

/**
 * Created by pouyadark on 11/28/18.
 */

public class CoolTabCell extends FrameLayout {
    private TabModel tabModel;
    private CoolTabBarSetting tabsetting= new CoolTabBarSetting();
    private TextView txtitle;
    private boolean selected=false;
    private View linebuttom;
    private GradientDrawable Selectedbackground;
    private GradientDrawable notSelectedbackground;
    private boolean isActionBar;
    private boolean isLastChild=false;

    public CoolTabCell( Context context) {
        super(context);
        init();
    }

    public void setLastChild(boolean lastChild) {
        isLastChild = lastChild;
//        if(lastChild){
//            lineleft.setVisibility(GONE);
//        }else{
//            lineleft.setVisibility(VISIBLE);
//        }
    }

    public void setTabsetting(CoolTabBarSetting tabsetting) {

        this.tabsetting = tabsetting;
        txtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,tabsetting.TabTitleFontSize);
        Selectedbackground=new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT,tabsetting.SelectedTabBackgroundGradianColor);
        Selectedbackground.setCornerRadius(AndroidUtilities.dp(tabsetting.tabBackgroundRadiusdp));

        notSelectedbackground=new GradientDrawable();
        notSelectedbackground.setColor(0xffd0d0d0);
        notSelectedbackground.setCornerRadius(AndroidUtilities.dp(tabsetting.tabBackgroundRadiusdp));

        setLayoutParams(LayoutHelper.createFrame(tabsetting.TabWidthdp,LayoutHelper.MATCH_PARENT,Gravity.RIGHT));


    }
    public void setTypeFace(Typeface typeFace){
        txtitle.setTypeface(typeFace);
    }
    private void init() {
        setLayoutParams(LayoutHelper.createFrame(tabsetting.TabWidthdp,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
        txtitle=new TextView(getContext());
        txtitle.setGravity(Gravity.CENTER);
//        txtitle.setPadding(AndroidUtilities.dp7,AndroidUtilities.dp2,AndroidUtilities.dp7,AndroidUtilities.dp2);
//        lineright=new View(getContext());
        linebuttom=new View(getContext());
        linebuttom.setVisibility(GONE);
        addView(txtitle,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
//        addView(lineright,LayoutHelper.createFrame(1,LayoutHelper.MATCH_PARENT,Gravity.RIGHT));
        addView(linebuttom,LayoutHelper.createFrame(20,2,Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,30,0,2));

    }

    public void setTabModel(TabModel tabModel,boolean selected) {
        this.selected=selected;
        this.tabModel = tabModel;
        txtitle.setText(tabModel.title);
        if(selected){
            txtitle.setTextColor(tabsetting.SelectedTabTextcolorNormal);
            linebuttom.setVisibility(isActionBar?GONE:VISIBLE);


        }else{
            linebuttom.setVisibility(GONE);
            txtitle.setTextColor(tabsetting.TabTextcolorNormal);

        }
//        lineright.setBackgroundColor(0xffffffff);
        linebuttom.setBackgroundColor(0xffffffff);

//        if(isActionBar){
//            line.setLayoutParams(LayoutHelper.createFrame(AndroidUtilities.dp1,AndroidUtilities.dp1,Gravity.CENTER,0,23,0,10));
//        }else{
//            line.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,AndroidUtilities.dp1,Gravity.CENTER,0,23,0,10));
//
//        }
    }

    public TabModel getTabModel() {
        return tabModel;
    }

    public void setIsActionBar(boolean isActionBar) {
        this.isActionBar = isActionBar;
    }
}
