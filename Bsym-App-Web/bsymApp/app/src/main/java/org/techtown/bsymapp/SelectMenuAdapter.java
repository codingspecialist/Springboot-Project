package org.techtown.bsymapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class SelectMenuAdapter extends RecyclerView.Adapter<SelectMenuAdapter.ViewHolder>
        implements OnSelectMenuItemClickListener {
    ArrayList<SelectMenu> items = new ArrayList<SelectMenu>();

    OnSelectMenuItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.selectmenuitem, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SelectMenu item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(SelectMenu item) {
        items.add(item);
    }

    public void setItems(ArrayList<SelectMenu> items) {
        this.items = items;
    }

    public SelectMenu getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, SelectMenu item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnSelectMenuItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView2;
        public ViewHolder(View itemView, final OnSelectMenuItemClickListener listener) {
            super(itemView);

            imageView2=itemView.findViewById(R.id.imageView22);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(SelectMenu item) {
            imageView2.setImageResource(item.getImage());
        }

    }
}
