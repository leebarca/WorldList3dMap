package com.leebarcaglobal.worldtravel3d.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.leebarcaglobal.worldtravel3d.MainActivity;
import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.utils.UtilClass;
import com.limurse.iap.DataWrappers;
import com.limurse.iap.IapConnector;
import com.limurse.iap.PurchaseServiceListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SettingsFragment extends Fragment {


    RelativeLayout remove_ads_rl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.settings_frg, container, false);

        remove_ads_rl = frgView.findViewById(R.id.remove_ads_rl);

        remove_ads_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).purchaseAdRemoval();


            }
        });

        return frgView;
    }
}