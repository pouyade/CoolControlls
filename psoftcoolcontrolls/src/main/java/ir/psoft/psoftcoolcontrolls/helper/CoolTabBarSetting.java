package ir.psoft.psoftcoolcontrolls.helper;

import android.graphics.drawable.Drawable;

import ir.psoft.psoftlayoutlib.helper.LayoutHelper;

/**
 * Created by pouyadark on 11/28/18.
 */

public class CoolTabBarSetting {
    public int NormalBackGroundColor=0xffffffff;
    public int ActionbarBackGroundColor=0xffffffff;
    public int[] SelectedTabBackgroundGradianColor=new int[]{0xff000000,0xff000000};
    public int[] TabBackgroundGradianColor=new int[]{0xffffffff,0xffffffff};
    public int tabBackgroundRadiusdp=4;
    public int SelectedTabTextcolorNormal=0xffffffff;
    public int SelectedTabTextcolorActionbar=0xffffffff;
    public int TabTextcolorNormal=0xaaffffff;
    public int TabTextcolorActionbar=0x97ffffff;
    public int SpaceBetweenTabsDp=4;
    public int TabWidthdp= 70;
    public float TabTitleFontSize=14;
    private Drawable backDrawble;

    public int getNormalBackGroundColor() {
        return NormalBackGroundColor;
    }

    public CoolTabBarSetting setNormalBackGroundColor(int normalBackGroundColor) {
        NormalBackGroundColor = normalBackGroundColor;
        return this;
    }

    public int getActionbarBackGroundColor() {
        return ActionbarBackGroundColor;
    }

    public CoolTabBarSetting setActionbarBackGroundColor(int actionbarBackGroundColor) {
        ActionbarBackGroundColor = actionbarBackGroundColor;
        return this;
    }

    public CoolTabBarSetting setTabWidthdp(int tabWidthdp) {
        TabWidthdp = tabWidthdp;
        return this;
    }

    public CoolTabBarSetting setSelectedTabBackgroundGradianColor(int[] selectedTabBackgroundGradianColor) {
        SelectedTabBackgroundGradianColor = selectedTabBackgroundGradianColor;
        return this;
    }

    public CoolTabBarSetting setTabBackgroundGradianColor(int[] tabBackgroundGradianColor) {
        TabBackgroundGradianColor = tabBackgroundGradianColor;
        return this;
    }

    public CoolTabBarSetting setSpaceBetweenTabsDp(int spaceBetweenTabsDp) {
        SpaceBetweenTabsDp = spaceBetweenTabsDp;
        return this;
    }

    public CoolTabBarSetting setTabTitleFontSize(float tabTitleFontSize) {
        TabTitleFontSize = tabTitleFontSize;
        return this;
    }

    public CoolTabBarSetting setSelectedTabTextcolorNormal(int selectedTabTextcolor) {
        SelectedTabTextcolorNormal = selectedTabTextcolor;
        return this;
    }

    public CoolTabBarSetting setTabTextcolorNormal(int tabTextcolor) {
        TabTextcolorNormal = tabTextcolor;
        return this;
    }
    public CoolTabBarSetting setSelectedTabTextcolorActionbar(int selectedTabTextcolor) {
        SelectedTabTextcolorActionbar = selectedTabTextcolor;
        return this;
    }

    public CoolTabBarSetting setTabTextcolorActionbar(int tabTextcolor) {
        TabTextcolorActionbar = tabTextcolor;
        return this;
    }

    public CoolTabBarSetting setBackDrawble(Drawable backDrawble) {
        this.backDrawble = backDrawble;
        return this;
    }

    public Drawable getBackDrawble() {
        return backDrawble;
    }
}
