package com.esaldivia.listview.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.esaldivia.listview.R;
import com.esaldivia.listview.Util;
import com.esaldivia.listview.activities.ItemShowcase;
import com.esaldivia.listview.model.LaptopItem;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<LaptopItem> dataList;
    private Context context;

    public ItemAdapter(List<LaptopItem> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final String title = dataList.get(position).getTitle();
        final String description = dataList.get(position).getDescription();
        final String imageUrl = dataList.get(position).getImageUrl();

        holder.getTitleTextView().setText(title);
        holder.getDescriptionTextView().setText(description);

        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.placeholderOf(context.getDrawable(R.drawable.ic_broken_image)))
                .into(holder.getCoverImage());

        holder.getmView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemShowcase.class);
                Bundle bundle = new Bundle();
                bundle.putString(Util.ITEM_TITLE_KEY, title);
                bundle.putString(Util.ITEM_DESCRIPTION_KEY, description);
                bundle.putString(Util.ITEM_IMAGE_URL_KEY, imageUrl);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
