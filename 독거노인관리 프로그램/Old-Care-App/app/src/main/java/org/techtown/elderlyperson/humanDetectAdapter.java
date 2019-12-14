package org.techtown.elderlyperson;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class humanDetectAdapter extends RecyclerView.Adapter<humanDetectAdapter.ViewHolder> {
    ArrayList<humanDetect> items = new ArrayList<humanDetect>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fragement4_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        humanDetect item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(humanDetect item) {
        items.add(item);
    }

    public void setItems(ArrayList<humanDetect> items) {
        this.items = items;
    }

    public humanDetect getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, humanDetect item) {
        items.set(position, item);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sangse11, sangse22;

        public ViewHolder(View itemView) {
            super(itemView);
            sangse11 = (TextView) itemView.findViewById(R.id.sangse11);
            sangse22 = (TextView) itemView.findViewById(R.id.sangse22);



        }

        public void setItem(humanDetect item) {
            sangse11.setText(item.getComebackDate());
            sangse22.setText(item.getOutCheck());

        }
    }
}
