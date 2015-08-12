package com.pundroid.appfinance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.pundroid.appfinance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pumba30 on 10.08.2015.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listMenuGroup;// родительские пункты
    private HashMap<String, List<String>> mapChildItemMenu; // дочерние пункты



    public MyExpandableListAdapter(Context context, List<String> listGroup,
                                   HashMap<String, List<String>> mapChildItemMenu) {
        this.context = context;
        this.listMenuGroup = listGroup;
        this.mapChildItemMenu = mapChildItemMenu;
    }

    @Override
    public int getGroupCount() {
        return listMenuGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mapChildItemMenu.get(listMenuGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listMenuGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mapChildItemMenu.get(listMenuGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupMenuText = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.group_parent, null);
        }
        TextView textViewGroup = (TextView) convertView.findViewById(R.id.groupTextView);
        textViewGroup.setText(groupMenuText);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childMenuText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.row_child, null);
        }

        TextView textViewChild = (TextView) convertView.findViewById(R.id.child_textView);
        textViewChild.setText(childMenuText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
