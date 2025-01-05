package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Objects;

public class CountryDetailsCurrency extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_currency);

        // Find the TextView to display country info
        ImageButton back_button = findViewById(R.id.back_button);
        Spinner from_country_spinner = findViewById(R.id.from_currency_spinner);
        Spinner to_country_spinner = findViewById(R.id.to_currency_spinner);
        @SuppressLint({"MissingInflatedId",
                "LocalSuppress"}) EditText from_currency_value = findViewById(R.id.from_currency_value);
        @SuppressLint({"MissingInflatedId",
                "LocalSuppress"}) EditText to_currency_value = findViewById(R.id.to_currency_value);
        TextView from_currency_symbol = findViewById(R.id.from_currency_symbol);
        TextView to_currency_symbol = findViewById(R.id.to_currency_symbol);

        String country_name = getIntent().getStringExtra("countryName");
        String currency = getIntent().getStringExtra("currency");

        // Define your TextView references
        //TextView[] monthTextViews = {jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec};

        String[] currency_symbol_list = {"Lek", "؋", "$", "ƒ", "₼", "p.", "BZ$", "$b", "KM", "P", "лв", "R$", "៛", "¥", "₡", "kn", "₱", "Kč", "kr", "RD$", "£", "€", "₾", "¢", "Q", "L", "₪", "J$", "₩", "₭", "Ls", "ден", "RM", "₨", "₮", "MT", "C$", "₦", "B/.", "Gs", "S/.", "zł", "lei", "₽", "Дин.", "S", "R", "฿", "TT$", "₺", "CHF", "NT$", "Bs", "₴", "₫", "$U", "Z$"};

        back_button.setOnClickListener(v -> {
            finish();
        });
    }
}