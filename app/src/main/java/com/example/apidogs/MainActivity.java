package com.example.apidogs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apidogs.client.RetrofitClient;
import com.example.apidogs.databinding.ActivityMainBinding;
import com.example.apidogs.entity.Message;
import com.example.apidogs.service.RetrofitApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends Activity {
    ImageView ImgView;
    Button btnUpdate;

    private ActivityMainBinding binding;
    private RetrofitApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initValues();
        initViews();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMessage("random");
                //getMessage("meow");
            }
        });
    }

    private  void  initViews(){
        ImgView = findViewById(R.id.ApiImageView);
        btnUpdate = findViewById(R.id.btnActualizar);
    }
    private void initValues(){
        apiService = RetrofitClient.getApiService();
    }
    private void getMessage(String random){
        apiService.getMessageRandom(random).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                try {
                    if (response.isSuccessful()){
                        Message m = response.body();
                        //String URL_IMG = m.getFile();
                        String URL_IMG = m.getMessage();
                        Glide.with(getApplication())
                                .load(URL_IMG)
                                .into(ImgView);
                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}