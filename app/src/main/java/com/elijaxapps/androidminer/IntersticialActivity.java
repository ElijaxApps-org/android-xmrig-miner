package com.elijaxapps.androidminer;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class IntersticialActivity extends Activity {
    private static final String BANNER_AD_ID = "ca-app-pub-7452939419560645/3623954709";
    private static InterstitialAd mInterstitialAd;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        MobileAds.initialize(this, getString(R.string.admob_app_id));

        activity = this;

        Tools.goToNextLevel(activity, new AdListener());

        Intent i = new Intent (getApplicationContext(), LauncherActivity.class);
        startActivity(i);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intersticial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static InterstitialAd newInterstitialAd(Activity thizz, AdListener adListener) {
        InterstitialAd interstitialAd = new InterstitialAd(thizz);
        interstitialAd.setAdUnitId(BANNER_AD_ID);
        interstitialAd.setAdListener(adListener);
        return interstitialAd;
    }

    public static boolean showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        boolean ret;
        if (ret = (mInterstitialAd != null && mInterstitialAd.isLoaded())) {
            mInterstitialAd.show();
        }
        return ret;
    }

    private static void loadInterstitial() {
        // Disable the next level button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    protected static void goToNextLevel(Activity context, AdListener listener) {
        // Show the next level and reload the ad to prepare for the level after.
        // mLevelTextView.setText("Level " + (++mLevel));
        mInterstitialAd = newInterstitialAd(context, listener);
        loadInterstitial();
    }
}
