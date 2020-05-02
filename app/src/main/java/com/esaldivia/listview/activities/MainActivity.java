package com.esaldivia.listview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.esaldivia.listview.R;
import com.esaldivia.listview.adapters.ItemAdapter;
import com.esaldivia.listview.model.LaptopItem;
import com.esaldivia.listview.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage(getString(R.string.loading));
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        RetrofitClientInstance.GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(RetrofitClientInstance.GetDataService.class);
        Call<List<LaptopItem>> call = service.getAllItems();
        call.enqueue(new Callback<List<LaptopItem>>() {
            @Override
            public void onResponse(Call<List<LaptopItem>> call, Response<List<LaptopItem>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<LaptopItem>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<LaptopItem> laptopItems) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new ItemAdapter(laptopItems, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
