package ir.psoft.psoftcoolcontrolls.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by pouyadark on 3/5/19.
 */

public class SpinnerItem {
    public int id;
    public String title;
    public String subtitle;
    public String drawableUrl;

    public SpinnerItem(int id, String title, String subtitle, String drawableUrl) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.drawableUrl = drawableUrl;
    }
}
