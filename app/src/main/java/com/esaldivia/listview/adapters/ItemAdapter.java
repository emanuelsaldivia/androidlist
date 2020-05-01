package com.esaldivia.listview.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.esaldivia.listview.R;
import com.esaldivia.listview.model.ItemInterface;
import com.esaldivia.listview.model.LaptopItem;

import java.util.List;

// todo hacer andar este extend
//public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
public class ItemAdapter extends RecyclerView.Adapter {

    private List<LaptopItem> dataList;
    private Context context;

    public ItemAdapter(List<LaptopItem> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    // todo no me gusta este view holder aca
    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        TextView txtDescription;
        private ImageView itemImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
            txtDescription = mView.findViewById(R.id.description);
            itemImage = mView.findViewById(R.id.coverImage);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        return new CustomViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CustomViewHolder customViewHolder = (CustomViewHolder) holder;
        customViewHolder.txtTitle.setText(dataList.get(position).getTitle());
        customViewHolder.txtDescription.setText(dataList.get(position).getDescription());

        Glide.with(context)
                .load(dataList.get(position).getImageUrl())
                .apply(RequestOptions.placeholderOf(context.getDrawable(R.drawable.ic_broken_image_black_18dp)))
                .into(customViewHolder.itemImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
