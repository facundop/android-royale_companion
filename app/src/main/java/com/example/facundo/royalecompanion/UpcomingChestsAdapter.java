package com.example.facundo.royalecompanion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UpcomingChestsAdapter extends ArrayAdapter<ChestItem> {
        private final Context context;
        private final List<ChestItem> values;

        public UpcomingChestsAdapter(Context context,List<ChestItem> values) {
            super(context, R.layout.list_upcoming_chests, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.list_upcoming_chests, parent, false);
            TextView chestType = (TextView) rowView.findViewById(R.id.chestType);
            TextView chestIndex = (TextView) rowView.findViewById(R.id.chestIndex);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.chestIcon);
            String chestName = values.get(position).getName();
            chestType.setText(chestName);
            chestIndex.setText("+ " + Integer.toString(values.get(position).getIndex()));

            if (chestName.equals("Silver Chest")) {
                imageView.setImageResource(R.mipmap.ic_silver_chest);
            } else if (chestName.equals("Golden Chest")) {
                imageView.setImageResource(R.mipmap.ic_golden_chest);
            } else if (chestName.equals("Giant Chest")) {
                imageView.setImageResource(R.mipmap.ic_giant_chest);
            } else if (chestName.equals("Magical Chest")) {
                imageView.setImageResource(R.mipmap.ic_magical_chest);
            } else if (chestName.equals("Super Magical Chest")) {
                imageView.setImageResource(R.mipmap.ic_supermagical_chest);
            } else if (chestName.equals("Epic Chest")) {
                imageView.setImageResource(R.mipmap.ic_epic_chest);
            } else if (chestName.equals("Legendary Chest")) {
                imageView.setImageResource(R.mipmap.ic_legendary_chest);
            } else {
                imageView.setImageResource(R.mipmap.ic_silver_chest);
            }

            return rowView;
        }
    }
