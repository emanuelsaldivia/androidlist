package com.esaldivia.listview.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.esaldivia.listview.R;
import com.esaldivia.listview.Util;
import com.esaldivia.listview.model.LaptopItem;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class ItemShowcase extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_showcase);

        Bundle bundle = getIntent().getExtras();

        String title = bundle.getString(Util.ITEM_TITLE_KEY);
        String description = bundle.getString(Util.ITEM_DESCRIPTION_KEY);
        String imageUrl = bundle.getString(Util.ITEM_IMAGE_URL_KEY);

        LaptopItem laptopItem = new LaptopItem(title, description, imageUrl);

        TextView txtTitle = findViewById(R.id.title);
        TextView txtDescription = findViewById(R.id.description);
        ImageView itemImage = findViewById(R.id.coverImage);

        txtTitle.setText(laptopItem.getTitle());
        txtDescription.setText(laptopItem.getDescription());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txtDescription.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        Glide.with(this)
                .load(imageUrl)
                .apply(RequestOptions.placeholderOf(this.getDrawable(R.drawable.ic_broken_image)))
                .into(itemImage);
    }
}
