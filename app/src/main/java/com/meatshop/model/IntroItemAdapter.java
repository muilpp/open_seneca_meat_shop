package com.meatshop.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meatshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class IntroItemAdapter extends RecyclerView.Adapter<IntroItemAdapter.ViewHolder>  {
    private List<IntroItem> itemsList;
    private Context adapterContext;

    public IntroItemAdapter(List<IntroItem> list, Context context) {
        itemsList = new ArrayList<>(list);
        adapterContext = context;
    }

    @Override
    public IntroItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.intro_item_cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IntroItemAdapter.ViewHolder holder, int position) {
        final IntroItem item = itemsList.get(position);

        holder.itemTitleTv.setText(item.getTitle());
        holder.itemDescriptionTv.setText(item.getDescription());

        Picasso.with(adapterContext)
                .load(item.getImageURL())
                .into(holder.itemImageIv);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitleTv, itemDescriptionTv;
        ImageView itemImageIv;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTitleTv = (TextView) itemView.findViewById(R.id.item_title);
            itemDescriptionTv = (TextView) itemView.findViewById(R.id.item_description);
            itemImageIv = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}
