package com.leebarcaglobal.worldtravel3d.APIs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExConvertAPI {

    private static final List<String> SPECIAL_CURRENCIES = Arrays.asList(
            "BRL", "DOP", "MNT", "KPW", "WST", "SSP", "SYP", "STN", "VUV", "ZWL"
    );

    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) {

        if (SPECIAL_CURRENCIES.contains(fromCurrency) || SPECIAL_CURRENCIES.contains(toCurrency)) {
            return scrapeCurrencyRate(fromCurrency, toCurrency, amount);
        }

        OkHttpClient client = new OkHttpClient();

        // Construct the URL
        String url = "https://api.exconvert.com/convert?from=" + fromCurrency +
                "&to=" + toCurrency +
                "&amount=" + amount +
                "&access_key=1ffbf71e-66a48c6b-9c19b42e-c06bc961";

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();

                // Parse the JSON response
                JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonObject resultObject = jsonObject.getAsJsonObject("result");

                // Retrieve the conversion value (e.g., "AUD": 22.838...)
                double conversionResult = resultObject.entrySet().iterator().next().getValue().getAsDouble();

                // Format the value to 2 decimal places
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                return Double.parseDouble(decimalFormat.format(conversionResult));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // Indicate failure
    }

    private static double scrapeCurrencyRate(String fromCurrency, String toCurrency, double amount) {
        try {
            // Construct the URL for scraping
            String url = "https://www.xe.com/currencyconverter/convert/?Amount=" + amount +
                    "&From=" + fromCurrency +
                    "&To=" + toCurrency;

            // Fetch the HTML content
            Document doc = Jsoup.connect(url).get();

            Element rateElement = doc.selectFirst("p[data-sleek-node-id='9b808f']");

            if (rateElement != null) {
                String fullText = rateElement.text(); // E.g., "1.12 06098 Swiss Francs"
                String numericPart = fullText.split(" ")[0]; // Extract "1.12"
                return Double.parseDouble(numericPart);
            } else {
                System.err.println("Failed to locate rate element on the page.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // Indicate failure
    }
}