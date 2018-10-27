package com.elijaxapps.androidminer;
/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.MobileAds;
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.elijaxapps.androidminer.IntersticialActivity.getBundle;

public class LauncherActivity extends Activity {
    private Button create, benchmark;
    private Activity activity;
    private static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(getBundle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().hide();
        }
        setContentView(R.layout.activity_launcher);
        //MobileAds.initialize(this, getString(R.string.admob_app_id));
        activity = this;


        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        //Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
        benchmark = findViewById(R.id.benchmark);
        create = findViewById(R.id.create);

        final Intent i1 = new Intent(LauncherActivity.this, MainActivity.class);
        i1.putExtras(getBundle());
        final Intent i2 = new Intent(LauncherActivity.this, BenchmarkActivity.class);
        i2.putExtras(getBundle());

        benchmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(i2, getBundle());
                }
            }
        });

            // Create the next level button, which tries to show an interstitial when clicked.
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(i1,  getBundle());
                }
            }/*
                Tools.ads(activity, new AdListener(){
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }
                });
*/
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
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
}
