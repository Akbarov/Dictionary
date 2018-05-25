package com.example.hp_pk.dictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.classes.LevelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zohidjon
 * @since 2018, March 17
 */

public class MyExpandableAdapter extends BaseExpandableListAdapter {


    private List<LevelGroup> groupItemList;
    private Context context;

    public MyExpandableAdapter(Context context, List<LevelGroup> groupItemList) {
        this.context = context;
        this.groupItemList = groupItemList;
    }

    public MyExpandableAdapter() {
        this.groupItemList = new ArrayList<>();
    }

    public void addAllList(List<LevelGroup> groupItemList) {
        this.groupItemList.addAll(groupItemList);
    }

    public void clear() {
        groupItemList.clear();
    }

    @Override
    public int getGroupCount() {
        return groupItemList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return groupItemList.get(i).getLessonList().size();
    }

    @Override
    public Object getGroup(int i) {
        return groupItemList.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupItemList.get(groupPosition).getLessonList().get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean isExpanded, View view, ViewGroup viewGroup) {
        LevelGroup tripGroupItem = (LevelGroup) getGroup(i);
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        view = inflater.inflate(R.layout.level_item_layout, null);

        TextView text = view.findViewById(R.id.text);
        text.setText(tripGroupItem.getLevel());
        ImageView indicator = view.findViewById(R.id.indicator);
        if (isExpanded) {
            indicator.setRotation(180);
        } else indicator.setRotation(0);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        String trip = (String) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_child_view, null);
        }
        view.setPadding(20, 0, 20, 0);
        TextView textView = view.findViewById(R.id.text);
        textView.setText(trip);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
