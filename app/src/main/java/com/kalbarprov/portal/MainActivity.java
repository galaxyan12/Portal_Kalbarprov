package com.kalbarprov.portal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvLink;
    private RecyclerView.Adapter adLink;
    private RecyclerView.LayoutManager lmLink;
    List<RedirectModel> listLink = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLink = findViewById(R.id.rv_link);
        progressBar = findViewById(R.id.progressBar);
        lmLink = new GridLayoutManager(this, 3);
        rvLink.setLayoutManager(lmLink);
        retrieveLink();
    }

    public void retrieveLink() {
        APIRequestData apiRequestData = RetrofitServer.connectRetrofit().create(APIRequestData.class);
        Call<List<RedirectModel>> tampilLink = apiRequestData.ardRequestLink();
        tampilLink.enqueue(new Callback<List<RedirectModel>>() {
            @Override
            public void onResponse(Call<List<RedirectModel>> call, Response<List<RedirectModel>> response) {
                listLink = response.body();
                adLink = new LinkAdapter(MainActivity.this, listLink);
                progressBar.setVisibility(View.GONE);
                rvLink.setAdapter(adLink);
                adLink.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<RedirectModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Mengambil Data"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}