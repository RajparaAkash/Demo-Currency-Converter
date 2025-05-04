package com.example.practical1.Model;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class CurrencyItem {

    private final String code;
    private final Double rate;
    private final String countryName;

    public CurrencyItem(String code, Double rate) {
        this.code = code;
        this.rate = rate;
        this.countryName = getCountryNameFromCode(code);
    }

    public String getCode() {
        return code;
    }

    public Double getRate() {
        return rate;
    }

    public String getCountryName() {
        return countryName;
    }

    @NonNull
    @Override
    public String toString() {
        return countryName + " (" + code + ")";
    }

    private String getCountryNameFromCode(String code) {
        Map<String, String> currencyCountryMap = new HashMap<>();
        currencyCountryMap.put("USD", "United States");
        currencyCountryMap.put("AED", "United Arab Emirates");
        currencyCountryMap.put("AFN", "Afghanistan");
        currencyCountryMap.put("ALL", "Albania");
        currencyCountryMap.put("AMD", "Armenia");
        currencyCountryMap.put("ANG", "Curaçao, Sint Maarten (Netherlands)");
        currencyCountryMap.put("AOA", "Angola");
        currencyCountryMap.put("ARS", "Argentina");
        currencyCountryMap.put("AUD", "Australia");
        currencyCountryMap.put("AWG", "Aruba");
        currencyCountryMap.put("AZN", "Azerbaijan");
        currencyCountryMap.put("BAM", "Bosnia and Herzegovina");
        currencyCountryMap.put("BBD", "Barbados");
        currencyCountryMap.put("BDT", "Bangladesh");
        currencyCountryMap.put("BGN", "Bulgaria");
        currencyCountryMap.put("BHD", "Bahrain");
        currencyCountryMap.put("BIF", "Burundi");
        currencyCountryMap.put("BMD", "Bermuda");
        currencyCountryMap.put("BND", "Brunei");
        currencyCountryMap.put("BOB", "Bolivia");
        currencyCountryMap.put("BRL", "Brazil");
        currencyCountryMap.put("BSD", "Bahamas");
        currencyCountryMap.put("BTN", "Bhutan");
        currencyCountryMap.put("BWP", "Botswana");
        currencyCountryMap.put("BYN", "Belarus");
        currencyCountryMap.put("BZD", "Belize");
        currencyCountryMap.put("CAD", "Canada");
        currencyCountryMap.put("CDF", "Democratic Republic of the Congo");
        currencyCountryMap.put("CHF", "Switzerland");
        currencyCountryMap.put("CLP", "Chile");
        currencyCountryMap.put("CNY", "China");
        currencyCountryMap.put("COP", "Colombia");
        currencyCountryMap.put("CRC", "Costa Rica");
        currencyCountryMap.put("CUP", "Cuba");
        currencyCountryMap.put("CVE", "Cape Verde");
        currencyCountryMap.put("CZK", "Czech Republic");
        currencyCountryMap.put("DJF", "Djibouti");
        currencyCountryMap.put("DKK", "Denmark");
        currencyCountryMap.put("DOP", "Dominican Republic");
        currencyCountryMap.put("DZD", "Algeria");
        currencyCountryMap.put("EGP", "Egypt");
        currencyCountryMap.put("ERN", "Eritrea");
        currencyCountryMap.put("ETB", "Ethiopia");
        currencyCountryMap.put("EUR", "Eurozone");
        currencyCountryMap.put("FJD", "Fiji");
        currencyCountryMap.put("FKP", "Falkland Islands");
        currencyCountryMap.put("FOK", "Faroe Islands");
        currencyCountryMap.put("GBP", "United Kingdom");
        currencyCountryMap.put("GEL", "Georgia");
        currencyCountryMap.put("GGP", "Guernsey");
        currencyCountryMap.put("GHS", "Ghana");
        currencyCountryMap.put("GIP", "Gibraltar");
        currencyCountryMap.put("GMD", "Gambia");
        currencyCountryMap.put("GNF", "Guinea");
        currencyCountryMap.put("GTQ", "Guatemala");
        currencyCountryMap.put("GYD", "Guyana");
        currencyCountryMap.put("HKD", "Hong Kong");
        currencyCountryMap.put("HNL", "Honduras");
        currencyCountryMap.put("HRK", "Croatia");
        currencyCountryMap.put("HTG", "Haiti");
        currencyCountryMap.put("HUF", "Hungary");
        currencyCountryMap.put("IDR", "Indonesia");
        currencyCountryMap.put("ILS", "Israel");
        currencyCountryMap.put("IMP", "Isle of Man");
        currencyCountryMap.put("INR", "India");
        currencyCountryMap.put("IQD", "Iraq");
        currencyCountryMap.put("IRR", "Iran");
        currencyCountryMap.put("ISK", "Iceland");
        currencyCountryMap.put("JEP", "Jersey");
        currencyCountryMap.put("JMD", "Jamaica");
        currencyCountryMap.put("JOD", "Jordan");
        currencyCountryMap.put("JPY", "Japan");
        currencyCountryMap.put("KES", "Kenya");
        currencyCountryMap.put("KGS", "Kyrgyzstan");
        currencyCountryMap.put("KHR", "Cambodia");
        currencyCountryMap.put("KID", "Kiribati");
        currencyCountryMap.put("KMF", "Comoros");
        currencyCountryMap.put("KRW", "South Korea");
        currencyCountryMap.put("KWD", "Kuwait");
        currencyCountryMap.put("KYD", "Cayman Islands");
        currencyCountryMap.put("KZT", "Kazakhstan");
        currencyCountryMap.put("LAK", "Laos");
        currencyCountryMap.put("LBP", "Lebanon");
        currencyCountryMap.put("LKR", "Sri Lanka");
        currencyCountryMap.put("LRD", "Liberia");
        currencyCountryMap.put("LSL", "Lesotho");
        currencyCountryMap.put("LYD", "Libya");
        currencyCountryMap.put("MAD", "Morocco");
        currencyCountryMap.put("MDL", "Moldova");
        currencyCountryMap.put("MGA", "Madagascar");
        currencyCountryMap.put("MKD", "North Macedonia");
        currencyCountryMap.put("MMK", "Myanmar");
        currencyCountryMap.put("MNT", "Mongolia");
        currencyCountryMap.put("MOP", "Macau");
        currencyCountryMap.put("MRU", "Mauritania");
        currencyCountryMap.put("MUR", "Mauritius");
        currencyCountryMap.put("MVR", "Maldives");
        currencyCountryMap.put("MWK", "Malawi");
        currencyCountryMap.put("MXN", "Mexico");
        currencyCountryMap.put("MYR", "Malaysia");
        currencyCountryMap.put("MZN", "Mozambique");
        currencyCountryMap.put("NAD", "Namibia");
        currencyCountryMap.put("NGN", "Nigeria");
        currencyCountryMap.put("NIO", "Nicaragua");
        currencyCountryMap.put("NOK", "Norway");
        currencyCountryMap.put("NPR", "Nepal");
        currencyCountryMap.put("NZD", "New Zealand");
        currencyCountryMap.put("OMR", "Oman");
        currencyCountryMap.put("PAB", "Panama");
        currencyCountryMap.put("PEN", "Peru");
        currencyCountryMap.put("PGK", "Papua New Guinea");
        currencyCountryMap.put("PHP", "Philippines");
        currencyCountryMap.put("PKR", "Pakistan");
        currencyCountryMap.put("PLN", "Poland");
        currencyCountryMap.put("PYG", "Paraguay");
        currencyCountryMap.put("QAR", "Qatar");
        currencyCountryMap.put("RON", "Romania");
        currencyCountryMap.put("RSD", "Serbia");
        currencyCountryMap.put("RUB", "Russia");
        currencyCountryMap.put("RWF", "Rwanda");
        currencyCountryMap.put("SAR", "Saudi Arabia");
        currencyCountryMap.put("SBD", "Solomon Islands");
        currencyCountryMap.put("SCR", "Seychelles");
        currencyCountryMap.put("SDG", "Sudan");
        currencyCountryMap.put("SEK", "Sweden");
        currencyCountryMap.put("SGD", "Singapore");
        currencyCountryMap.put("SHP", "Saint Helena");
        currencyCountryMap.put("SLE", "Sierra Leone");
        currencyCountryMap.put("SLL", "Sierra Leone");
        currencyCountryMap.put("SOS", "Somalia");
        currencyCountryMap.put("SRD", "Suriname");
        currencyCountryMap.put("SSP", "South Sudan");
        currencyCountryMap.put("STN", "São Tomé and Príncipe");
        currencyCountryMap.put("SYP", "Syria");
        currencyCountryMap.put("SZL", "Eswatini (Swaziland)");
        currencyCountryMap.put("THB", "Thailand");
        currencyCountryMap.put("TJS", "Tajikistan");
        currencyCountryMap.put("TMT", "Turkmenistan");
        currencyCountryMap.put("TND", "Tunisia");
        currencyCountryMap.put("TOP", "Tonga");
        currencyCountryMap.put("TRY", "Turkey");
        currencyCountryMap.put("TTD", "Trinidad and Tobago");
        currencyCountryMap.put("TVD", "Tuvalu");
        currencyCountryMap.put("TWD", "Taiwan");
        currencyCountryMap.put("TZS", "Tanzania");
        currencyCountryMap.put("UAH", "Ukraine");
        currencyCountryMap.put("UGX", "Uganda");
        currencyCountryMap.put("UYU", "Uruguay");
        currencyCountryMap.put("UZS", "Uzbekistan");
        currencyCountryMap.put("VES", "Venezuela");
        currencyCountryMap.put("VND", "Vietnam");
        currencyCountryMap.put("VUV", "Vanuatu");
        currencyCountryMap.put("WST", "Samoa");
        currencyCountryMap.put("XAF", "Central African countries (CEMAC)");
        currencyCountryMap.put("XCD", "Eastern Caribbean countries");
        currencyCountryMap.put("XCG", "Unknown");
        currencyCountryMap.put("XDR", "International Monetary Fund (IMF)");
        currencyCountryMap.put("XOF", "West African countries (UEMOA)");
        currencyCountryMap.put("XPF", "French overseas collectivities");
        currencyCountryMap.put("YER", "Yemen");
        currencyCountryMap.put("ZAR", "South Africa");
        currencyCountryMap.put("ZMW", "Zambia");
        currencyCountryMap.put("ZWL", "Zimbabwe");

        return currencyCountryMap.getOrDefault(code, "Unknown Country");
    }
}
