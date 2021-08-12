package ir.psoft.psoftcoolcontrolls.Widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.psoft.psoftcoolcontrolls.Models.SpinnerItem;
import ir.psoft.psoftlayoutlib.helper.LayoutHelper;

/**
 * Created by pouyadark on 3/5/19.
 */

public class CoolSpinner extends androidx.appcompat.widget.AppCompatSpinner {
    private ArrayList<SpinnerItem> spinnerItems;
    private int selectedId=0;
    private CustomArrayAdapter adapter;
    private TextView txtTitle;
    private TextView txtsubtitle;

    public CoolSpinner(Context context,int color) {
        super(context);
        this.setBackgroundColor(color);
        init();
//        setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(spinnerItems==null)return;
//                if(position==0){
//                    selectedId=0;
//                }else{
//                    selectedId=spinnerItems.get(position-1).id;
//                }
//            }
//        });
        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerItems==null)return;
                if(position==0){
                    selectedId=0;
                }else{
                    selectedId=spinnerItems.get(position-1).id;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSpinnerItems(ArrayList<SpinnerItem> spinnerItems) {
        this.spinnerItems = spinnerItems;
        adapter.notifyDataSetChanged();
    }

    private void init() {
        adapter=new CustomArrayAdapter(getContext());
        this.setAdapter(adapter);
    }
    class CustomArrayAdapter extends ArrayAdapter<SpinnerItem> {

        private Context context;
        private Typeface typeFace = Typeface.DEFAULT;

        public CustomArrayAdapter(Context context) {
            super(context, 0, new ArrayList<SpinnerItem>());
            this.context = context;
        }

        @Override
        public int getCount() {
            if(spinnerItems==null )return 1;
                return spinnerItems.size()+1;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position);
        }

        public void setTypeFace(Typeface typeFace) {
            this.typeFace = typeFace;
            txtsubtitle.setTypeface(this.typeFace);

            txtTitle.setTypeface(this.typeFace);

        }

        private View getCustomView(int position) {
            FrameLayout frameLayout = new FrameLayout(getContext());
            if(position==0){
                txtTitle = new TextView(getContext());
                txtTitle.setGravity(Gravity.RIGHT);
                txtTitle.setTextColor(0xff000000);
                txtTitle.setText("درخواست پشتیبانی عمومی");
                txtTitle.setTypeface(this.typeFace);
                txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                frameLayout.addView(txtTitle, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.RIGHT, 5, 5, 5, 5));

            }else {
                ImageView imgicon = new ImageView(CoolSpinner.this.getContext());
                imgicon.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.get().load(spinnerItems.get(position-1).drawableUrl).into(imgicon);
                frameLayout.addView(imgicon, LayoutHelper.createFrame(50, 50, Gravity.RIGHT, 2, 2, 2, 2));

                txtTitle = new TextView(getContext());
                txtTitle.setGravity(Gravity.RIGHT);
                txtTitle.setTextColor(0xff000000);
                txtTitle.setText(spinnerItems.get(position-1).title);
                txtTitle.setTypeface(this.typeFace);
                txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                frameLayout.addView(txtTitle, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.RIGHT, 2, 2, 60, 2));

                txtsubtitle = new TextView(getContext());
                txtsubtitle.setGravity(Gravity.RIGHT);
                txtsubtitle.setTextColor(0x99000000);
                txtsubtitle.setText(spinnerItems.get(position-1).subtitle);
                txtsubtitle.setTypeface(this.typeFace);
                txtsubtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                frameLayout.addView(txtsubtitle, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.RIGHT, 2, 24, 60, 2));
            }

            return frameLayout;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position);
        }


    }


}
