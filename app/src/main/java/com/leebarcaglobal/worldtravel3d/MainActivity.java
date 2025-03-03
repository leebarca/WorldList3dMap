package com.leebarcaglobal.worldtravel3d;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.leebarcaglobal.worldtravel3d.fragments.ExploreFragment;
import com.leebarcaglobal.worldtravel3d.fragments.HomeFragment;
import com.leebarcaglobal.worldtravel3d.fragments.LanguageFragment;
import com.leebarcaglobal.worldtravel3d.fragments.MainFragment;
import com.leebarcaglobal.worldtravel3d.fragments.PlannerFragment;
import com.leebarcaglobal.worldtravel3d.utils.FragmentTags;
import com.leebarcaglobal.worldtravel3d.utils.UtilClass;
import com.limurse.iap.DataWrappers;
import com.limurse.iap.IapConnector;
import com.limurse.iap.PurchaseServiceListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
//
//    App ID is ca-app-pub-7434808266785754~3182088768
//    Reward Interstitial ID is ca-app-pub-7434808266785754/8242187219
//    Regular Interstitial ID is ca-app-pub-7434808266785754/9068447051
//    Banner ID is ca-app-pub-7434808266785754/5710827998

    private AdView bannerAd;
    private InterstitialAd mInterstitialAd;

    public static int adClickCounter = 0;

    private static final String PREFERENCES_NAME = "AppPreferences";
    private static final String LANGUAGE_KEY = "SelectedLanguage";


    IapConnector iapConnector;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(updateLanguageContext(newBase));
    }

    private Context updateLanguageContext(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        String savedLanguage = preferences.getString(LANGUAGE_KEY, "en"); // Default to English
        Locale locale = new Locale(savedLanguage);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        return context.createConfigurationContext(config);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        View decorView = getWindow().getDecorView();
        decorView.setOnApplyWindowInsetsListener((v, insets) -> {
            v.setPadding(0, 0, 0, insets.getSystemWindowInsetBottom());
            return insets.consumeSystemWindowInsets();
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.theme_sel_col));

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        bannerAd = findViewById(R.id.adView);


        if (!UtilClass.isAdRemovalPurchased(MainActivity.this)) {
            //not Purchased Show Ads
            Log.d("AdddddddDebug", "Ads are enabled. Loading ads...");
            canLoadAds(true);
        } else {
            //Purchased DONT SHOW ADS
            Log.d("AdddddddDebug", "Ads are disabled. Skipping ad load.");
            bannerAd.setVisibility(View.GONE);
        }






        MutableLiveData<Boolean> isBillingClientConnected = new MutableLiveData<>();
        isBillingClientConnected.setValue(false);

        List<String> nonConsumablesList = Collections.singletonList("lifetime");
        List<String> consumablesList = Arrays.asList("1dollar",
                "2dollar",
                "3dollar");
        List<String> subsList = Collections.singletonList("subscription");

        iapConnector = new IapConnector(
                MainActivity.this,
                nonConsumablesList, consumablesList, subsList,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvhhx4iT3KD79cwtCLEULuvl0tnM6SzNgvEMmy1dktbGfFLgKMJ2RD1I/gR3I47UCDJY5I7gq6/1a45NoY7GBkSZy3tw5cpRGqSJusrGsVDy2pnkl75vWqL93xJMz2g00PKkc/kbrO569sliIdOow/luy3SNiEBX96ryh82xDK6evZDiS3NGAUAU6YKtOMXQnwWTphZBCkmy5NoOzllPlmofd9iEAV3E0aAMlfuOIftm5QO0IeMJ29In2OJilGkwGZ//ukp2sb1nbFDW+2NWFCds2Rl8g/r0tD/R1pmI3CzIxjPFIKZVS0tv++hCVxNlND6IoT+Zi8ZTc6gEmn86xnwIDAQAB", true);


        iapConnector.addBillingClientConnectionListener((status, billingResponseCode) -> {
            Log.d("KSA_BILLING", "This is the status: " + status + " and response code is: " + billingResponseCode);
            isBillingClientConnected.setValue(status);

        });

        iapConnector.addPurchaseListener(new PurchaseServiceListener() {
            @Override
            public void onPricesUpdated(@NonNull Map<String, DataWrappers.ProductDetails> map) {

            }

            @Override
            public void onProductPurchased(@NonNull DataWrappers.PurchaseInfo purchaseInfo) {
                if (purchaseInfo.getSku().equals("lifetime")) {

                    UtilClass.setAdRemovalPurchased(MainActivity.this,true);
                    Toast.makeText(MainActivity.this, "Thanks for purchasing ad removal!", Toast.LENGTH_SHORT).show();

                    canLoadAds(false);
                }

            }

            @Override
            public void onProductRestored(@NonNull DataWrappers.PurchaseInfo purchaseInfo) {
                if (purchaseInfo.getSku().equals("lifetime")) {
                    UtilClass.setAdRemovalPurchased(MainActivity.this,true);
                    canLoadAds(false);
                }
            }

            @Override
            public void onPurchaseFailed(@Nullable DataWrappers.PurchaseInfo purchaseInfo, @Nullable Integer integer) {

            }
        });





        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, new MainFragment(), FragmentTags.MAIN_FRAGMENT_TAG)
                .commit();


//        if (UtilClass.isAdRemovalPurchased(MainActivity.this)) { // Can Load and show ads
//            admobAdsConfigs();
//        }





    }


//    public void canLoadAds(){
//        if (!UtilClass.isAdRemovalPurchased(MainActivity.this)) {
//            // Load and show ads
//            admobAdsConfigs();
//        } else {
//            // Hide ads
//            bannerAd.setVisibility(View.GONE);
//        }
//
//    }

    private void canLoadAds(boolean loadAds){
        if(loadAds){
            bannerAd.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder().build();
            bannerAd.loadAd(adRequest);


            loadInterstitialAd();
        }else{
            bannerAd.setVisibility(View.GONE);
        }


    }




    public void purchaseAdRemoval(){
        iapConnector.purchase(MainActivity.this,"lifetime","","");
    }





    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();


        Fragment mainFragment = fragmentManager.findFragmentById(R.id.container); // Use the correct container ID for MainFragment
        if (mainFragment instanceof MainFragment) {

            FragmentManager childFragmentManager = ((MainFragment) mainFragment).getChildFragmentManager();


            Fragment childFragment = childFragmentManager.findFragmentById(R.id.fragment_container);


            if (childFragment instanceof HomeFragment) {
                ((MainFragment) mainFragment).updateBottomNavigationView(R.id.nav_home);
            } else if (childFragment instanceof ExploreFragment) {
                ((MainFragment) mainFragment).updateBottomNavigationView(R.id.nav_explore);
            } else if (childFragment instanceof PlannerFragment) {
                ((MainFragment) mainFragment).updateBottomNavigationView(R.id.nav_planner);
            } else if (childFragment instanceof LanguageFragment) {
                ((MainFragment) mainFragment).updateBottomNavigationView(R.id.nav_language);
            }


            if (childFragmentManager.getBackStackEntryCount() > 0) {
                childFragmentManager.popBackStack();
                return;
            }
        }


        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void showAdmobAd(){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(MainActivity.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdClicked() {
                    Log.d("AdddddMob", "Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {

                    Log.d("AdddddMob", "Ad dismissed fullscreen content.");

                    loadInterstitialAd();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {

                    Log.e("AdddddMob", "Ad failed to show fullscreen content.");

                    loadInterstitialAd();
                }

                @Override
                public void onAdImpression() {

                    Log.d("AdddddMob", "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {

                    Log.d("AdddddMob", "Ad showed fullscreen content.");
                }
            });
        } else {
            Log.d("AdddddMob", "The interstitial ad wasn't ready yet.");
            loadInterstitialAd();
        }


    }

    public void loadInterstitialAd() {


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.e("AdddddMob", "onAdLoaded");

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("AdddddMob", loadAdError.toString());

                    }
                });


//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
//            @Override
//            public void onAdLoaded(InterstitialAd ad) {
//                interstitialAd = ad;
//            }
//
//            @Override
//            public void onAdFailedToLoad(LoadAdError adError) {
//                interstitialAd = null;
//            }
//        });
    }

}