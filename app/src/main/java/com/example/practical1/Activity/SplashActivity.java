package com.example.practical1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practical1.Model.CurrencyItem;
import com.example.practical1.R;
import com.example.practical1.Client.ApiClient;
import com.example.practical1.Client.ApiService;
import com.example.practical1.databinding.ActivitySplashBinding;
import com.example.practical1.Model.CurrencyRates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    public static final List<CurrencyItem> splashCurrencyItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ActivitySplashBinding binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getCurrencyData();
    }

    private void getCurrencyData() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<CurrencyRates> call = apiService.getCurrencyRates("usd");

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<CurrencyRates> call, @NonNull Response<CurrencyRates> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CurrencyRates currencyRates = response.body();
                    Map<String, Double> rates = currencyRates.getRates();
                    splashCurrencyItems.clear();
                    for (Map.Entry<String, Double> entry : rates.entrySet()) {
                        splashCurrencyItems.add(new CurrencyItem(entry.getKey(), entry.getValue()));
                    }
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SplashActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrencyRates> call, @NonNull Throwable t) {
                Toast.makeText(SplashActivity.this, "API error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}