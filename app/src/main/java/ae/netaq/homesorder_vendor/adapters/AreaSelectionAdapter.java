package ae.netaq.homesorder_vendor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ae.netaq.homesorder_vendor.R;
import ae.netaq.homesorder_vendor.models.Areas;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.library.SmoothCheckBox;

/**
 * Created by sabih on 30-Dec-17.
 */

public class AreaSelectionAdapter extends BaseExpandableListAdapter{

    private Areas states;
    private Context mContext;
    private ArrayList<Areas.State.Area> selectedAreas = new ArrayList<>();


    public AreaSelectionAdapter(Context context, Areas states) {
        this.mContext = context;
        this.states = states;

    }

    @Override
    public int getGroupCount() {
        return states.getStates().size();
    }

    @Override
    public int getChildrenCount(int i) {
        return states.getStates().get(i).getAreas().size();
    }

    @Override
    public Areas.State getGroup(int i) {
        return states.getStates().get(i);
    }

    @Override
    public Areas.State.Area getChild(int groupPosition, int childPosition) {
        return states.getStates().get(groupPosition).getAreas().get(childPosition);

    }

    @Override
    public long getGroupId(int i) {
        return states.getStates().get(i).getStateID();
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

        Areas.State state = getGroup(groupPosition);
        String stateNameEN = state.getStateNameEN();
        TextView tvStateName = convertView.findViewById(R.id.tv_state_name);
        tvStateName.setText(stateNameEN);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded,
                             View convertView, ViewGroup viewGroup) {

        Areas.State.Area area = getChild(groupPosition, childPosition);
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.textViewChild = (TextView) convertView.findViewById(R.id.textViewChildItem);
            holder.areaCheckBox = (SmoothCheckBox) convertView.findViewById(R.id.area_checkBox);

            convertView.setTag(holder);

            //ButterKnife.bind(this,convertView);
        }else{

            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewChild.setText(area.getAreaNameEN());
        holder.areaCheckBox.setOnCheckedChangeListener(new AreaSelectionListener(area));

        convertView.setOnClickListener(new AreaSelectionListener(area));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    private class AreaSelectionListener implements View.OnClickListener,
                  SmoothCheckBox.OnCheckedChangeListener {
        private Areas.State.Area projectedArea;

        public AreaSelectionListener(Areas.State.Area area) {
            this.projectedArea = area;
        }

        @Override
        public void onClick(View view) {
            //areaCheckBox.setChecked(true);
        }

        @Override
        public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean isChecked) {
            handleSelection(projectedArea,isChecked);
        }
    }

    private void handleSelection(Areas.State.Area selectedArea, boolean isChecked){
        boolean found = false;

        for(Areas.State.Area area : selectedAreas){
            if(area.getAreaID()==selectedArea.getAreaID()){
                found = true;
                selectedAreas.remove(area);
            }
        }
    }

    public class ViewHolder{
        TextView textViewChild;
        SmoothCheckBox areaCheckBox;
    }


}
