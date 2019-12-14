package org.techtown.elderlyperson;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class tahDataAdapter extends RecyclerView.Adapter<tahDataAdapter.ViewHolder> {
    ArrayList<tahData> items = new ArrayList<tahData>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fragement3_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        tahData item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(tahData item) {
        items.add(item);
    }

    public void setItems(ArrayList<tahData> items) {
        this.items = items;
    }

    public tahData getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, tahData item) {
        items.set(position, item);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sangse1, sangse2, sangse3;

        public ViewHolder(View itemView) {
            super(itemView);
            sangse1 = (TextView) itemView.findViewById(R.id.sangse1);
            sangse2 = (TextView) itemView.findViewById(R.id.sangse2);
            sangse3 = (TextView) itemView.findViewById(R.id.sangse3);


        }

        public void setItem(tahData item) {
            sangse1.setText(item.getCreateDate());
            sangse2.setText(item.getTemp());
            sangse3.setText(item.getHumi());
        }

    }
}
