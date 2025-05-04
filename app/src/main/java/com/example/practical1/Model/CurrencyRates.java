package com.example.practical1.Model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class CurrencyRates {

    @SerializedName("base")
    private String base;

    @SerializedName("date")
    private String date;

    @SerializedName("time_last_updated")
    private long timeLastUpdated;

    @SerializedName("rates")
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public long getTimeLastUpdated() {
        return timeLastUpdated;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}

