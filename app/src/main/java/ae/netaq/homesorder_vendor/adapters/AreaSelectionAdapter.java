package ae.netaq.homesorder_vendor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.db.data_manager.UserDataManager;
import ae.netaq.homesorder_vendor.models.Country;
import cn.refactor.library.SmoothCheckBox;

/**
 * Created by sabih on 30-Dec-17.
 */

public class AreaSelectionAdapter extends BaseExpandableListAdapter{

    private Country country;
    private Context mContext;
    private ArrayList<Country.State.Area> selectedAreas;


    public AreaSelectionAdapter(Context context, Country country) {
        this.mContext = context;
        this.country = country;


    }

    @Override
    public int getGroupCount() {
        return country.getStates().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return country.getStates().get(groupPosition).getAreas().size();
    }

    @Override
    public Country.State getGroup(int i) {
        return country.getStates().get(i);
    }

    @Override
    public Country.State.Area getChild(int groupPosition, int childPosition) {
        return country.getStates().get(groupPosition).getAreas().get(childPosition);

    }

    @Override
    public long getGroupId(int i) {
        return country.getStates().get(i).getStateID();
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_group,null);
        }

        Country.State state = getGroup(groupPosition);
        String stateNameEN = state.getStateNameEN();
        TextView tvStateName = convertView.findViewById(R.id.tv_state_name);
        tvStateName.setText(stateNameEN);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded,
                             View convertView, ViewGroup viewGroup) {

        Country.State.Area area = getChild(groupPosition, childPosition);


        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.textViewChild = convertView.findViewById(R.id.textViewChildItem);
            holder.areaCheckBox =  convertView.findViewById(R.id.area_checkBox);
            holder.parent = convertView.findViewById(R.id.row_parent);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.areaCheckBox.setOnCheckedChangeListener(null);

        //TO:DO
        holder.areaCheckBox.setChecked(area.isSelected());


        holder.textViewChild.setText(area.getAreaNameEN());
        holder.areaCheckBox.setOnCheckedChangeListener(new AreaSelectionListener(holder,area,
                                                       groupPosition,childPosition));

        holder.parent.setOnClickListener(new AreaSelectionListener(holder,area,
                                         groupPosition,childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }



    private class AreaSelectionListener implements View.OnClickListener,
                  SmoothCheckBox.OnCheckedChangeListener {

        private final ViewHolder holder;
        private final int groupPosition;
        private final int childPosition;

        public AreaSelectionListener(ViewHolder holder, Country.State.Area area,
                                     int groupPosition, int childPosition) {
            this.holder = holder;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean isChecked) {
            handleSelection(isChecked,groupPosition,childPosition);
        }
    }

    private void handleSelection( boolean isChecked,
                                 int groupPosition, int childPosition){



        if(isChecked){
            country.getStates().get(groupPosition).getAreas().get(childPosition).setIsSelected(true);
        }else{
            country.getStates().get(groupPosition).getAreas().get(childPosition).setIsSelected(false);
        }
    }

    public Country getSelectedAreas() {
        return country;
    }

    public class ViewHolder{
        RelativeLayout parent;
        TextView textViewChild;
        SmoothCheckBox areaCheckBox;
    }


}
