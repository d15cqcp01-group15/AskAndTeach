package com.example.askandteach.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.askandteach.R;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {

    private List<String> mItems;
    private Context mContext;
    private ISpinnerCallback mCallback;

    public CustomSpinnerAdapter(Context context, List<String> items, ISpinnerCallback callback){
        this.mItems = items;
        this.mContext = context;
        this.mCallback = callback;
    }

    @Override
    public int getCount() {
        return mItems== null ?0 : mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, parent, false);

        TextView name = convertView.findViewById(R.id.tvName);
        View divider = convertView.findViewById(R.id.divider);
        name.setText(mItems.get(position));

        if (position >= mItems.size() -1){
            divider.setVisibility(View.INVISIBLE);
        } else {
            divider.setVisibility(View.VISIBLE);
        }

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onItemClicked(mItems.get(position));
            }
        });

        return convertView;
    }

    public interface ISpinnerCallback{
        public void onItemClicked(String text);
    }
}
