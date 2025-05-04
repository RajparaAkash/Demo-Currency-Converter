package com.example.practical1.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.practical1.Model.CurrencyItem;

import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<CurrencyItem> {
    public CurrencyAdapter(Context context, List<CurrencyItem> currencyList) {
        super(context, android.R.layout.simple_spinner_item, currencyList);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
