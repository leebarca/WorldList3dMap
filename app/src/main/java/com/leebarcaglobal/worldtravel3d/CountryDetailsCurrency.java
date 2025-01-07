package com.leebarcaglobal.worldtravel3d;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CountryDetailsCurrency extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_currency);

        ImageButton back_button = findViewById(R.id.back_button);
        Spinner from_country_spinner = findViewById(R.id.from_currency_spinner);
        Spinner to_country_spinner = findViewById(R.id.to_currency_spinner);
        EditText from_currency_value = findViewById(R.id.from_currency_value);
        TextView to_currency_value = findViewById(R.id.to_currency_value);
        TextView from_currency_symbol = findViewById(R.id.from_currency_symbol);
        TextView to_currency_symbol = findViewById(R.id.to_currency_symbol);
        ImageButton swap_button = findViewById(R.id.swap_button);

        String currency = getIntent().getStringExtra("currency");

        HashMap<String, List<String>> currency_types = new HashMap<>();

        currency_types.put(getString(R.string.afghan_afghani), Arrays.asList("AFN", "؋"));
        currency_types.put(getString(R.string.albanian_lek), Arrays.asList("ALL", "L"));
        currency_types.put(getString(R.string.algerian_dinar), Arrays.asList("DZD", "دج"));
        currency_types.put(getString(R.string.euro), Arrays.asList("EUR", "€"));
        currency_types.put(getString(R.string.angolan_kwanza), Arrays.asList("AOA", "Kz"));
        currency_types.put(getString(R.string.eastern_caribbean_dollar), Arrays.asList("XCD", "$"));
        currency_types.put(getString(R.string.argentine_peso), Arrays.asList("ARS", "$"));
        currency_types.put(getString(R.string.armenian_dram), Arrays.asList("AMD", "֏"));
        currency_types.put(getString(R.string.australian_dollar), Arrays.asList("AUD", "$"));
        currency_types.put(getString(R.string.azerbaijani_manat), Arrays.asList("AZN", "₼"));
        currency_types.put(getString(R.string.bahamian_dollar), Arrays.asList("BSD", "$"));
        currency_types.put(getString(R.string.bahraini_dinar), Arrays.asList("BHD", "ب.د"));
        currency_types.put(getString(R.string.bangladeshi_taka), Arrays.asList("BDT", "৳"));
        currency_types.put(getString(R.string.barbadian_dollar), Arrays.asList("BBD", "$"));
        currency_types.put(getString(R.string.belarusian_ruble), Arrays.asList("BYN", "Br"));
        currency_types.put(getString(R.string.belize_dollar), Arrays.asList("BZD", "$"));
        currency_types.put(getString(R.string.westafricancfafranc), Arrays.asList("XOF", "₣"));
        currency_types.put(getString(R.string.bhutanese_ngultrum), Arrays.asList("BTN", "Nu."));
        currency_types.put(getString(R.string.bolivian_boliviano), Arrays.asList("BOB", "Bs."));
        currency_types.put(getString(R.string.bosnian_mark), Arrays.asList("BAM", "KM"));
        currency_types.put(getString(R.string.botswana_pula), Arrays.asList("BWP", "P"));
        currency_types.put(getString(R.string.brazilian_real), Arrays.asList("BRL", "R$"));
        currency_types.put(getString(R.string.brunei_dollar), Arrays.asList("BND", "$"));
        currency_types.put(getString(R.string.bulgarian_lev), Arrays.asList("BGN", "лв"));
        currency_types.put(getString(R.string.burundi_franc), Arrays.asList("BIF", "₣"));
        currency_types.put(getString(R.string.cabo_verdean_escudo), Arrays.asList("CVE", "$"));
        currency_types.put(getString(R.string.cambodian_riel), Arrays.asList("KHR", "៛"));
        currency_types.put(getString(R.string.canadian_dollar), Arrays.asList("CAD", "$"));
        currency_types.put(getString(R.string.central_african_cfa_franc), Arrays.asList("XAF", "₣"));
        currency_types.put(getString(R.string.chilean_peso), Arrays.asList("CLP", "$"));
        currency_types.put(getString(R.string.renminbi), Arrays.asList("CNY", "¥"));
        currency_types.put(getString(R.string.colombian_peso), Arrays.asList("COP", "$"));
        currency_types.put(getString(R.string.comorian_franc), Arrays.asList("KMF", "₣"));
        currency_types.put(getString(R.string.costa_rican_colon), Arrays.asList("CRC", "₡"));
        currency_types.put(getString(R.string.cuban_peso), Arrays.asList("CUP", "$"));
        currency_types.put(getString(R.string.czech_koruna), Arrays.asList("CZK", "Kč"));
        currency_types.put(getString(R.string.congolese_franc), Arrays.asList("CDF", "₣"));
        currency_types.put(getString(R.string.djiboutian_franc), Arrays.asList("DJF", "₣"));
        currency_types.put(getString(R.string.dominican_peso), Arrays.asList("DOP", "RD$"));
        currency_types.put(getString(R.string.usd), Arrays.asList("USD", "$"));
        currency_types.put(getString(R.string.egyptian_pound), Arrays.asList("EGP", "£"));
        currency_types.put(getString(R.string.eritrean_nakfa), Arrays.asList("ERN", "Nfk"));
        currency_types.put(getString(R.string.swazi_lilangeni), Arrays.asList("SZL", "E"));
        currency_types.put(getString(R.string.ethiopian_birr), Arrays.asList("ETB", "Br"));
        currency_types.put(getString(R.string.fiji_dollar), Arrays.asList("FJD", "$"));
        currency_types.put(getString(R.string.georgian_lari), Arrays.asList("GEL", "₾"));
        currency_types.put(getString(R.string.ghanaian_cedi), Arrays.asList("GHS", "₵"));
        currency_types.put(getString(R.string.guatemalan_qeutzal), Arrays.asList("GTQ", "Q"));
        currency_types.put(getString(R.string.guinean_franc), Arrays.asList("GNF", "₣"));
        currency_types.put(getString(R.string.guyanese_dollar), Arrays.asList("GYD", "$"));
        currency_types.put(getString(R.string.haitian_gourde), Arrays.asList("HTG", "G"));
        currency_types.put(getString(R.string.honduran_lempira), Arrays.asList("HNL", "L"));
        currency_types.put(getString(R.string.hungarian_forint), Arrays.asList("HUF", "Ft"));
        currency_types.put(getString(R.string.icelandic_krona), Arrays.asList("ISK", "kr"));
        currency_types.put(getString(R.string.indian_rupee), Arrays.asList("INR", "₹"));
        currency_types.put(getString(R.string.indonesian_rupiah), Arrays.asList("IDR", "Rp"));
        currency_types.put(getString(R.string.iranian_rial), Arrays.asList("IRR", "﷼"));
        currency_types.put(getString(R.string.iraqi_dinar), Arrays.asList("IQD", "ع.د"));
        currency_types.put(getString(R.string.israeli_new_shekel), Arrays.asList("ILS", "₪"));
        currency_types.put(getString(R.string.jamaican_dollar), Arrays.asList("JMD", "$"));
        currency_types.put(getString(R.string.japanese_yen), Arrays.asList("JPY", "¥"));
        currency_types.put(getString(R.string.jordanian_dinar), Arrays.asList("JOD", "د.ا"));
        currency_types.put(getString(R.string.kazakhstani_tenge), Arrays.asList("KZT", "₸"));
        currency_types.put(getString(R.string.kenyan_shilling), Arrays.asList("KES", "KSh"));
        currency_types.put(getString(R.string.kiribati_dollar), Arrays.asList("KID", "$"));
        currency_types.put(getString(R.string.kuwait_dinar), Arrays.asList("KWD", "د.ك"));
        currency_types.put(getString(R.string.kyrgyz_som), Arrays.asList("KGS", "с"));
        currency_types.put(getString(R.string.lao_kip), Arrays.asList("LAK", "₭"));
        currency_types.put(getString(R.string.lebanese_pound), Arrays.asList("LBP", "ل.ل"));
        currency_types.put(getString(R.string.lesotho_loti), Arrays.asList("LSL", "L"));
        currency_types.put(getString(R.string.liberian_dollar), Arrays.asList("LRD", "$"));
        currency_types.put(getString(R.string.libyan_dinar), Arrays.asList("LYD", "ل.د"));
        currency_types.put(getString(R.string.swiss_franc), Arrays.asList("CHF", "₣"));
        currency_types.put(getString(R.string.malagasy_ariary), Arrays.asList("MGA", "Ar"));
        currency_types.put(getString(R.string.malawian_kwacha), Arrays.asList("MWK", "MK"));
        currency_types.put(getString(R.string.malaysian_ringgit), Arrays.asList("MYR", "RM"));
        currency_types.put(getString(R.string.maldivian_rufiyaa), Arrays.asList("MVR", "Rf"));
        currency_types.put(getString(R.string.mauritania_ouguiya), Arrays.asList("MRU", "UM"));
        currency_types.put(getString(R.string.mauritius_rupee), Arrays.asList("MUR", "₨"));
        currency_types.put(getString(R.string.mexico_peso), Arrays.asList("MXN", "$"));
        currency_types.put(getString(R.string.moldova_leu), Arrays.asList("MDL", "L"));
        currency_types.put(getString(R.string.mongolia_togrog), Arrays.asList("MNT", "₮"));
        currency_types.put(getString(R.string.morocco_dirham), Arrays.asList("MAD", "د.م."));
        currency_types.put(getString(R.string.mozambique_metal), Arrays.asList("MZN", "MT"));
        currency_types.put(getString(R.string.myanmar_kyat), Arrays.asList("MMK", "K"));
        currency_types.put(getString(R.string.namibia_dollar), Arrays.asList("NAD", "$"));
        currency_types.put(getString(R.string.nepal_currency), Arrays.asList("NPR", "₨"));
        currency_types.put(getString(R.string.new_zealand_dollar), Arrays.asList("NZD", "$"));
        currency_types.put(getString(R.string.nicaragua_cordoba), Arrays.asList("NIO", "C$"));
        currency_types.put(getString(R.string.nigerian_naira), Arrays.asList("NGN", "₦"));
        currency_types.put(getString(R.string.north_korea_won), Arrays.asList("KPW", "₩"));
        currency_types.put(getString(R.string.macedonian_denar), Arrays.asList("MKD", "ден"));
        currency_types.put(getString(R.string.norwegian_krone), Arrays.asList("NOK", "kr"));
        currency_types.put(getString(R.string.omani_riyal), Arrays.asList("OMR", "ر.ع."));
        currency_types.put(getString(R.string.pakistani_rupee), Arrays.asList("PKR", "₨"));
        currency_types.put(getString(R.string.panama_currency), Arrays.asList("PAB", "B/."));
        currency_types.put(getString(R.string.papua_new_guinea_currency), Arrays.asList("PGK", "K"));
        currency_types.put(getString(R.string.paraguay_currency), Arrays.asList("PYG", "₲"));
        currency_types.put(getString(R.string.peru_currency), Arrays.asList("PEN", "S/"));
        currency_types.put(getString(R.string.philippine_peso), Arrays.asList("PHP", "₱"));
        currency_types.put(getString(R.string.polish_zloty), Arrays.asList("PLN", "zł"));
        currency_types.put(getString(R.string.qatari_riyal), Arrays.asList("QAR", "ر.ق"));
        currency_types.put(getString(R.string.romanian_leu), Arrays.asList("RON", "lei"));
        currency_types.put(getString(R.string.russian_ruble), Arrays.asList("RUB", "₽"));
        currency_types.put(getString(R.string.rwandan_franc), Arrays.asList("RWF", "₣"));
        currency_types.put(getString(R.string.samoan_tala), Arrays.asList("WST", "T"));
        currency_types.put(getString(R.string.sao_tome_and_principe_dobra), Arrays.asList("STN", "Db"));
        currency_types.put(getString(R.string.saudi_riyal), Arrays.asList("SAR", "ر.س"));
        currency_types.put(getString(R.string.serbian_dinar), Arrays.asList("RSD", "дин"));
        currency_types.put(getString(R.string.seychellois_rupee), Arrays.asList("SCR", "₨"));
        currency_types.put(getString(R.string.sierra_leonean_leone), Arrays.asList("SLL", "Le"));
        currency_types.put(getString(R.string.singapore_dollar), Arrays.asList("SGD", "$"));
        currency_types.put(getString(R.string.solomon_islands_dollar), Arrays.asList("SBD", "$"));
        currency_types.put(getString(R.string.somali_shilling), Arrays.asList("SOS", "Sh"));
        currency_types.put(getString(R.string.south_africa_rand), Arrays.asList("ZAR", "R"));
        currency_types.put(getString(R.string.south_korea_won), Arrays.asList("KRW", "₩"));
        currency_types.put(getString(R.string.south_sudan_pound), Arrays.asList("SSP", "£"));
        currency_types.put(getString(R.string.sri_lanka_currency), Arrays.asList("LKR", "Rs"));
        currency_types.put(getString(R.string.sudan_currency), Arrays.asList("SDG", "ج.س."));
        currency_types.put(getString(R.string.suriname_currency), Arrays.asList("SRD", "$"));
        currency_types.put(getString(R.string.sweden_currency), Arrays.asList("SEK", "kr"));
        currency_types.put(getString(R.string.syria_currency), Arrays.asList("SYP", "£S"));
        currency_types.put(getString(R.string.new_taiwan_dollar), Arrays.asList("TWD", "NT$"));
        currency_types.put(getString(R.string.tajikistani_somoni), Arrays.asList("TJS", "SM"));
        currency_types.put(getString(R.string.tanzanian_shilling), Arrays.asList("TZS", "Sh"));
        currency_types.put(getString(R.string.thai_baht), Arrays.asList("THB", "฿"));
        currency_types.put(getString(R.string.gambian_dalasi), Arrays.asList("GMD", "D"));
        currency_types.put(getString(R.string.tongan_pa_anga), Arrays.asList("TOP", "T$"));
        currency_types.put(getString(R.string.trinidad_and_tobago_dollar), Arrays.asList("TTD", "$"));
        currency_types.put(getString(R.string.tunisian_dinar), Arrays.asList("TND", "د.ت"));
        currency_types.put(getString(R.string.turkish_lira), Arrays.asList("TRY", "₺"));
        currency_types.put(getString(R.string.turkmenistani_mana), Arrays.asList("TMT", "m"));
        currency_types.put(getString(R.string.tuvaluan_dollar), Arrays.asList("TVD", "$"));
        currency_types.put(getString(R.string.ugandan_shilling), Arrays.asList("UGX", "Sh"));
        currency_types.put(getString(R.string.ukrainian_hryvnia), Arrays.asList("UAH", "₴"));
        currency_types.put(getString(R.string.uae_currency), Arrays.asList("AED", "د.إ"));
        currency_types.put(getString(R.string.sterling), Arrays.asList("GBP", "£"));
        currency_types.put(getString(R.string.uruguayan_peso), Arrays.asList("UYU", "$U"));
        currency_types.put(getString(R.string.uzbekistani_sum), Arrays.asList("UZS", "сум"));
        currency_types.put(getString(R.string.vanuatu_vatu), Arrays.asList("VUV", "Vt"));
        currency_types.put(getString(R.string.venezuela_currency), Arrays.asList("VES", "Bs."));
        currency_types.put(getString(R.string.vietnam_currency), Arrays.asList("VND", "₫"));
        currency_types.put(getString(R.string.yemen_currency), Arrays.asList("YER", "﷼"));
        currency_types.put(getString(R.string.zambia_currency), Arrays.asList("ZMW", "K"));
        currency_types.put(getString(R.string.zimbabwe_currency), Arrays.asList("ZWL", "$"));

        ArrayList<String> currencyNames = new ArrayList<>(currency_types.keySet());

        if (currency != null && currencyNames.contains(currency)) {
            int toDefaultPosition = currencyNames.indexOf(currency);
            int fromDefaultPosition = currencyNames.indexOf(getString(R.string.sterling));
            from_country_spinner.setSelection(fromDefaultPosition);
            to_country_spinner.setSelection(toDefaultPosition);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, currencyNames);
        from_country_spinner.setAdapter(adapter);
        to_country_spinner.setAdapter(adapter);

        from_currency_value.setText("1");
        from_currency_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCurrencyValue(from_country_spinner, to_country_spinner, from_currency_value, to_currency_value, currency_types);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        from_country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateCurrencySymbol(from_country_spinner, from_currency_symbol, currency_types);
                updateCurrencyValue(from_country_spinner, to_country_spinner, from_currency_value, to_currency_value, currency_types);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        to_country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateCurrencySymbol(to_country_spinner, to_currency_symbol, currency_types);
                updateCurrencyValue(from_country_spinner, to_country_spinner, from_currency_value, to_currency_value, currency_types);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        swap_button.setOnClickListener(v -> {
            int fromPosition = from_country_spinner.getSelectedItemPosition();
            int toPosition = to_country_spinner.getSelectedItemPosition();
            from_country_spinner.setSelection(toPosition);
            to_country_spinner.setSelection(fromPosition);
        });

        back_button.setOnClickListener(v -> finish());
    }

    private void updateCurrencySymbol(Spinner spinner, TextView symbolView, HashMap<String, List<String>> currencyTypes) {
        String selectedCurrency = spinner.getSelectedItem().toString();
        List<String> details = currencyTypes.get(selectedCurrency);
        if (details != null && details.size() > 1) {
            symbolView.setText(details.get(1));
        } else {
            symbolView.setText("");
        }
    }

    private void updateCurrencyValue(Spinner fromSpinner, Spinner toSpinner, EditText fromValue, TextView toValue, HashMap<String, List<String>> currencyTypes) {
        String fromCurrency = fromSpinner.getSelectedItem().toString();
        String toCurrency = toSpinner.getSelectedItem().toString();
        String fromValueString = fromValue.getText().toString();

        if (fromValueString.isEmpty()) {
            toValue.setText("N/A");
            return;
        }

        try {
            double amount = Double.parseDouble(fromValueString);
            List<String> fromDetails = currencyTypes.get(fromCurrency);
            List<String> toDetails = currencyTypes.get(toCurrency);

            if (fromDetails != null && toDetails != null) {
                String fromIso = fromDetails.get(0);
                String toIso = toDetails.get(0);
                fetchExchangeRate(fromIso, toIso, amount, toValue);
            }
        } catch (NumberFormatException e) {
            toValue.setText("N/A");
        }
    }

    private void fetchExchangeRate(String fromCurrency, String toCurrency, double amount, TextView toValue) {
        new Thread(() -> {
            double exchangeRate = ExConvertAPI.convertCurrency(fromCurrency, toCurrency, amount);
            runOnUiThread(() -> {
                if (exchangeRate >= 0) {
                    toValue.setText(String.format("%.2f", exchangeRate * amount));
                } else {
                    toValue.setText("N/A");
                }
            });
        }).start();
    }
}