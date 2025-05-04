package com.example.practical1.Client;

import com.example.practical1.Model.CurrencyRates;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Call<CurrencyRates> getCurrencyRates(@Url String url);
}
