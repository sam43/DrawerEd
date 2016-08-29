package com.example.zero.drawer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<parent> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, ArrayList<parent> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getName())
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getName())
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
//        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
            //convertView.setAnimation();
        }


        LinearLayout Linearparent =(LinearLayout)convertView.findViewById(R.id.parent);

        ImageView icon = (ImageView)convertView.findViewById(R.id.icon);

        ImageView arrow = (ImageView)convertView.findViewById(R.id.arrow);

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);

        lblListHeader.setTypeface(null, Typeface.NORMAL);

        if(isExpanded) {

            lblListHeader.setTextColor(_context.getResources().getColor(R.color.listview_group_text_color_after));

            icon.setColorFilter(_context.getResources().getColor(R.color.listview_group_image_color_after));

             // arrow.setImageResource(R.drawable.ic_down);
            arrow.setBackgroundResource(R.drawable.close_list);

            arrow.setColorFilter(_context.getResources().getColor(R.color.listview_group_arrow_image_color_after));
            Linearparent.setBackgroundColor(_context.getResources().getColor(R.color.listview_group_parent_color_after));

        }
        else{
            lblListHeader.setTextColor(_context.getResources().getColor(R.color.listview_group_text_color_before));

            icon.setColorFilter(_context.getResources().getColor(R.color.listview_group_image_color_before));

         //  arrow.setImageResource(R.drawable.ic_up_arrow);
            arrow.setBackgroundResource(R.drawable.open_list);
            arrow.setColorFilter(_context.getResources().getColor(R.color.listview_group_arrow_image_color_before));
            Linearparent.setBackgroundColor(_context.getResources().getColor(R.color.listview_group_parent_color_before));
    }
       // lblListHeader.setText(headerTitle);

        com.example.zero.drawer.parent rowItem = (com.example.zero.drawer.parent) getGroup(groupPosition);

        lblListHeader.setText(rowItem.getName());


        icon.setImageResource(rowItem.getIcon());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}