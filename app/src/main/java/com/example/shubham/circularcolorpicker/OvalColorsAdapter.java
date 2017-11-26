package com.example.shubham.circularcolorpicker;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gunaseelan on 18-12-2016.
 */

public class OvalColorsAdapter extends RecyclerView.Adapter<OvalColorsAdapter.ViewHolder> {
    int[] colors;
    OvalColorsSelectedListener listener;

    public OvalColorsAdapter(int[] colors, OvalColorsSelectedListener listener) {
        this.colors = colors;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oval_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GradientDrawable bgShape = (GradientDrawable) holder.view.getBackground();
        bgShape.setColor(colors[position]);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOvalColorSelected(colors[holder.getAdapterPosition()]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.viewColor);
        }
    }
}