package com.app.sogal.Logic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.sogal.Data.Chip;
import com.app.sogal.R;

import java.util.List;

public class ChipAdapter  extends ArrayAdapter {

    List<Chip> chipList;
    LayoutInflater inflater;
    Context context;
    public ChipAdapter(@NonNull Context context,  List<Chip> list) {
        super(context, 0, list);
        chipList = list;
        this.context= context;
        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.chip_row,parent,false);
// inflate custom layout called row
            holder = new ViewHolder();
            holder.name =(TextView) convertView.findViewById(R.id.chipName);
            holder.function =(TextView) convertView.findViewById(R.id.chipFunction);
// initialize textview
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        Chip in = (Chip)chipList.get(position);
        holder.name.setText(in.getChipName());
        holder.function.setText(in.getAction());
        // set the name to the text;

        return convertView;

    }
    static class ViewHolder
    {

        TextView name;
        TextView function;
    }
}
