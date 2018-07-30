package com.telkom.telkom_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter < MyDataModel > {

    List < MyDataModel > modelList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyArrayAdapter(Context context, List < MyDataModel > objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MyDataModel item = getItem(position);

        vh.textViewId.setText("ID : "+item.getId());
        vh.textViewName.setText("Nama : "+item.getNama());
        vh.textViewPerusahaan.setText("Perusahaan : "+item.getPerusahaan());
        vh.textViewKota.setText("Kota : "+item.getKota());

        return vh.rootView;
    }



    private static class ViewHolder {
        public final RelativeLayout rootView;

        public final TextView textViewId;
        public final TextView textViewName;
        public final TextView textViewPerusahaan;
        public final TextView textViewKota;

        private ViewHolder(RelativeLayout rootView, TextView textViewId, TextView textViewName,
                            TextView textViewPerusahaan, TextView textViewKota) {
            this.rootView = rootView;
            this.textViewId = textViewId;
            this.textViewName = textViewName;
            this.textViewPerusahaan = textViewPerusahaan;
            this.textViewKota = textViewKota;

        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewId = (TextView) rootView.findViewById(R.id.textViewId);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewPerusahaan = (TextView) rootView.findViewById(R.id.textViewPerusahaan);
            TextView textViewKota = (TextView) rootView.findViewById(R.id.textViewKota);

            return new ViewHolder(rootView,  textViewId, textViewName, textViewPerusahaan, textViewKota);
        }
    }
}