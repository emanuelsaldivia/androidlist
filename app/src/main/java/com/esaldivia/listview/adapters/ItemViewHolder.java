package com.esaldivia.listview.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.esaldivia.listview.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private final View mView;

    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageView coverImage;

    ItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

        titleTextView = mView.findViewById(R.id.title);
        descriptionTextView = mView.findViewById(R.id.description);
        coverImage = mView.findViewById(R.id.coverImage);
    }

    public View getmView() {
        return mView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public TextView getDescriptionTextView() {
        return descriptionTextView;
    }

    public ImageView getCoverImage() {
        return coverImage;
    }
}
