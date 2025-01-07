package com.leebarcaglobal.worldtravel3d;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ExConvertAPI {

    private static final String API_URL = "https://api.exconvert.com/convert";
    private static final String ACCESS_KEY = "your_access_key";

    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?access_key=%s&from=%s&to=%s&amount=%.2f",
                                   API_URL, ACCESS_KEY, fromCurrency, toCurrency, amount);

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                return jsonObject.get("result").getAsDouble();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }
}