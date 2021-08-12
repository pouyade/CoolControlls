package ir.psoft.psoftcoolcontrolls.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ir.psoft.psoftcoolcontrolls.Cell.CoolTabCell;
import ir.psoft.psoftcoolcontrolls.Models.TabModel;
import ir.psoft.psoftcoolcontrolls.Widgets.CoolTabBar;
import ir.psoft.psoftcoolcontrolls.helper.CoolTabBarSetting;

/**
 * Created by pouyadark on 11/28/18.
 */

public class CoolTabBarAdapter extends RecyclerView.Adapter<CoolTabBarAdapter.ViewHolder> {
    Context mContext;
    private CoolTabBarSetting setting;
    private int CurrentSelected=0;
    ArrayList<TabModel> tabModels=new ArrayList<>();
    private CoolTabBar.CoolTabBarEvents tabBarEvents;
    private boolean isActionbar;

    public void setTabBarEvents(CoolTabBar.CoolTabBarEvents tabBarEvents) {
        this.tabBarEvents = tabBarEvents;
    }

    public CoolTabBar.CoolTabBarEvents getTabBarEvents() {
        return tabBarEvents;
    }

    public CoolTabBarAdapter(Context mContext) {
        this.mContext=mContext;
    }

    public void setTabModels(ArrayList<TabModel> tabModels) {
        this.tabModels = tabModels;
        notifyDataSetChanged();
    }

    public void setSetting(CoolTabBarSetting setting) {
        this.setting = setting;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new CoolTabCell(mContext));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((CoolTabCell)holder.itemView).setIsActionBar(isActionbar);
        ((CoolTabCell)holder.itemView).setTabsetting(setting);
        ((CoolTabCell)holder.itemView).setLastChild(position==getItemCount()-1);
        ((CoolTabCell)holder.itemView).setTabModel(tabModels.get(position),CurrentSelected==tabModels.get(position).id);
    }

    @Override
    public int getItemCount() {
        return tabModels.size();
    }

    public void setIsActionbar(boolean isActionbar) {
        this.isActionbar = isActionbar;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TabModel currenttabs=((CoolTabCell)v).getTabModel();
                    CurrentSelected = currenttabs.id;
                    notifyDataSetChanged();
                    if(tabBarEvents!=null){
                        tabBarEvents.onTabChanged(currenttabs);
                    }
                }
            });
        }
    }
}
