package com.example.practical1.Activity;

import static com.example.practical1.Activity.SplashActivity.splashCurrencyItems;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Callback;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practical1.R;
import com.example.practical1.Adapter.CurrencyAdapter;
import com.example.practical1.Client.ApiClient;
import com.example.practical1.Client.ApiService;
import com.example.practical1.databinding.ActivityMainBinding;
import com.example.practical1.Model.CurrencyItem;
import com.example.practical1.Model.CurrencyRates;
import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<CurrencyItem> currencyItems = new ArrayList<>();
    private CurrencyAdapter adapter;

    private boolean isBaseSpinnerInitialized = false;
    private boolean isTarget1SpinnerInitialized = false;
    private boolean isTarget2SpinnerInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.baseAmountEt.setShowSoftInputOnFocus(false);
        binding.baseAmountEt.setFocusable(false);

        PowerPreference.getDefaultFile().setString("t1code", "AUD");
        PowerPreference.getDefaultFile().setString("t2code", "BRL");

        setBackPressed();
        setMainData();
        setAmountEt();
        setSpinner();
        setNumberPad();

        binding.baseLayout.setOnClickListener(v -> {
            binding.baseSpinner.performClick();
        });

        binding.target1Layout.setOnClickListener(v -> {
            binding.targetSpinner1.performClick();
        });

        binding.target2Layout.setOnClickListener(v -> {
            binding.targetSpinner2.performClick();
        });
    }

    private void setMainData() {
        currencyItems = splashCurrencyItems;
        isBaseSpinnerInitialized = false;
        isTarget1SpinnerInitialized = false;
        isTarget2SpinnerInitialized = false;

        adapter = new CurrencyAdapter(MainActivity.this, currencyItems);
        binding.baseSpinner.setAdapter(adapter);
        binding.targetSpinner1.setAdapter(adapter);
        binding.targetSpinner2.setAdapter(adapter);
        binding.baseSpinner.setSelection(0);
        setTargetData();
    }

    public void getCurrencyData(String code) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<CurrencyRates> call = apiService.getCurrencyRates(code);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<CurrencyRates> call, @NonNull Response<CurrencyRates> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CurrencyRates currencyRates = response.body();
                    Map<String, Double> rates = currencyRates.getRates();
                    currencyItems.clear();
                    for (Map.Entry<String, Double> entry : rates.entrySet()) {
                        currencyItems.add(new CurrencyItem(entry.getKey(), entry.getValue()));
                    }

                    isBaseSpinnerInitialized = false;
                    isTarget1SpinnerInitialized = false;
                    isTarget2SpinnerInitialized = false;

                    adapter = new CurrencyAdapter(MainActivity.this, currencyItems);
                    binding.baseSpinner.setAdapter(adapter);
                    binding.targetSpinner1.setAdapter(adapter);
                    binding.targetSpinner2.setAdapter(adapter);
                    binding.baseSpinner.setSelection(0);
                    setTargetData();
                } else {
                    Log.e("Currency", "Response failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrencyRates> call, @NonNull Throwable t) {
                Log.e("Currency", "API Error: " + t.getMessage());
            }
        });
    }

    private void setAmountEt() {
        binding.baseAmountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTargetData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setSpinner() {
        binding.baseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CurrencyItem selectedCurrency = (CurrencyItem) parent.getItemAtPosition(position);

                String amountText = binding.baseAmountEt.getText().toString().trim();
                if (amountText.isEmpty() || amountText.equals("0")) {
                    Toast.makeText(MainActivity.this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isBaseSpinnerInitialized) {
                    getCurrencyData(selectedCurrency.getCode());
                } else {
                    isBaseSpinnerInitialized = true;
                }

                binding.baseCurrencyCodeTxt.setText(selectedCurrency.getCode());
                binding.baseCountryNameTxt.setText(selectedCurrency.getCountryName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.targetSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CurrencyItem selectedCurrency = (CurrencyItem) parent.getItemAtPosition(position);
                if (isTarget1SpinnerInitialized) {
                    Double baseAmount = getAmount();
                    PowerPreference.getDefaultFile().setString("t1code", selectedCurrency.getCode());
                    Double amt1 = getRateByCurrencyCode(selectedCurrency.getCode());
                    binding.target1CodeTxt.setText(selectedCurrency.getCode());
                    binding.target1CountryTxt.setText(getCountryNameByCurrencyCode(selectedCurrency.getCode()));
                    if (amt1 != null) {
                        binding.target1AmountTxt.setText(String.format(Locale.US, "%.2f", amt1 * baseAmount));
                    }
                } else {
                    isTarget1SpinnerInitialized = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.targetSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CurrencyItem selectedCurrency = (CurrencyItem) parent.getItemAtPosition(position);
                if (isTarget2SpinnerInitialized) {
                    Double baseAmount = getAmount();
                    PowerPreference.getDefaultFile().setString("t2code", selectedCurrency.getCode());
                    Double amt1 = getRateByCurrencyCode(selectedCurrency.getCode());
                    binding.target2CodeTxt.setText(selectedCurrency.getCode());
                    binding.target2CountryTxt.setText(getCountryNameByCurrencyCode(selectedCurrency.getCode()));
                    if (amt1 != null) {
                        binding.target2AmountTxt.setText(String.format(Locale.US, "%.2f", amt1 * baseAmount));
                    }
                } else {
                    isTarget2SpinnerInitialized = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setNumberPad() {
        View.OnClickListener numberClickListener = v -> {
            TextView b = (TextView) v;
            String currentText = binding.baseAmountEt.getText().toString();
            binding.baseAmountEt.setText(currentText + b.getText().toString());
        };

        binding.btn1Txt.setOnClickListener(numberClickListener);
        binding.btn2Txt.setOnClickListener(numberClickListener);
        binding.btn3Txt.setOnClickListener(numberClickListener);
        binding.btn4Txt.setOnClickListener(numberClickListener);
        binding.btn5Txt.setOnClickListener(numberClickListener);
        binding.btn6Txt.setOnClickListener(numberClickListener);
        binding.btn7Txt.setOnClickListener(numberClickListener);
        binding.btn8Txt.setOnClickListener(numberClickListener);
        binding.btn9Txt.setOnClickListener(numberClickListener);
        binding.btn0Txt.setOnClickListener(numberClickListener);
        binding.btn00Txt.setOnClickListener(numberClickListener);

        binding.btnPointTxt.setOnClickListener(v -> {
            String currentText = binding.baseAmountEt.getText().toString();
            if (!currentText.contains(".")) {
                binding.baseAmountEt.setText(currentText + ".");
            }
        });

        binding.btnClear.setOnClickListener(v -> {
            String currentText = binding.baseAmountEt.getText().toString();
            if (!currentText.isEmpty()) {
                binding.baseAmountEt.setText(currentText.substring(0, currentText.length() - 1));
            }
        });

        binding.btnAcTxt.setOnClickListener(v -> {
            binding.baseAmountEt.setText("");
        });
    }

    public void setTargetData() {
        Double baseAmount = getAmount();
        Double amt1 = getRateByCurrencyCode(PowerPreference.getDefaultFile().getString("t1code", "AUD"));
        Double amt2 = getRateByCurrencyCode(PowerPreference.getDefaultFile().getString("t2code", "BRL"));

        String t1Code = PowerPreference.getDefaultFile().getString("t1code", "AUD");
        String t2Code = PowerPreference.getDefaultFile().getString("t2code", "BRL");

        binding.target1CodeTxt.setText(t1Code);
        binding.target1CountryTxt.setText(getCountryNameByCurrencyCode(t1Code));
        if (amt1 != null) {
            binding.target1AmountTxt.setText(String.format(Locale.US, "%.2f", amt1 * baseAmount));
        }

        binding.target2CodeTxt.setText(t2Code);
        binding.target2CountryTxt.setText(getCountryNameByCurrencyCode(t2Code));
        if (amt2 != null) {
            binding.target2AmountTxt.setText(String.format(Locale.US, "%.2f", amt2 * baseAmount));
        }
    }

    private Double getRateByCurrencyCode(String code) {
        for (CurrencyItem item : currencyItems) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item.getRate();
            }
        }
        return null;
    }

    private String getCountryNameByCurrencyCode(String code) {
        for (CurrencyItem item : currencyItems) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item.getCountryName();
            }
        }
        return "Unknown";
    }

    public Double getAmount() {
        String amountText = binding.baseAmountEt.getText().toString().trim();
        if (amountText.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void setBackPressed() {
        OnBackPressedCallback onCallBack = new OnBackPressedCallback(true) {
            boolean doubleBackToExitPressedOnce = false;

            @Override
            public void handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    finishAffinity();
                    System.exit(0);
                } else {
                    doubleBackToExitPressedOnce = true;
                    Toast.makeText(MainActivity.this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                    new Handler(Looper.getMainLooper()).postDelayed(() ->
                            doubleBackToExitPressedOnce = false, 2000);
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, onCallBack);

        binding.backImg.setOnClickListener(view -> {
            onCallBack.handleOnBackPressed();
        });
    }
}
