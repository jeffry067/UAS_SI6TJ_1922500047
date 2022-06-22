package com.example.uassi6tj1922500047;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private DosenJsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_desen_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.97/UasSI6TJ/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(DosenJsonPlaceHolderApi.class);
        getPosts();
    }

    private void getPosts() {
        Map<String, String> parameters = new HashMap<>();
        Call<List<DosenPost>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<DosenPost>>() {
            @Override
            public void onResponse(Call<List<DosenPost>> call, Response<List<DosenPost>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<DosenPost> posts = response.body();
                for (DosenPost post : posts) {
                    String content = "";
                    content += "NIDN : " + post.getNidn() + "\n";
                    content += "Nama Dosen : " + post.getNamaDosen() + "\n";
                    content += "Jabatan : " + post.getJabatan() + "\n";
                    content += "Golongan Pangkat : " + post.getGolPang() + "\n";
                    content += "Keahlian : " + post.getKeahlian() + "\n";
                    content += "Program Studi : " + post.getProgramStudi() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<DosenPost>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void tambah(View view) {
        Intent i = new Intent( MainActivity.this, EdosenActivity.class);
        startActivity(i);
    }
}