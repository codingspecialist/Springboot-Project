package org.techtown.bsymapp;

import android.view.View;

public interface OnContentMenuItemClickListener {
    public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position);
}