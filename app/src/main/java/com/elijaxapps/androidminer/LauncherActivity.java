package com.elijaxapps.androidminer;
/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.MobileAds;
*/
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class LauncherActivity extends Activity {
    private Button create, benchmark;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_launcher);
        //MobileAds.initialize(this, getString(R.string.admob_app_id));
        activity = this;
        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        //Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
        benchmark = findViewById(R.id.benchmark);
        benchmark.setOnClickListener(v -> {
            Intent i = new Intent(LauncherActivity.this, BenchmarkActivity.class);

            i.putExtra("cpu", 95);
            i.putExtra("threads", 4);
            i.putExtra("user", getString(R.string.my_wallet));
            i.putExtra("pool", getString(R.string.my_pool));

            startActivity(i);

        });
        // Create the next level button, which tries to show an interstitial when clicked.
            create = findViewById(R.id.create);
            create.setOnClickListener(view -> {
                Intent i = new Intent(LauncherActivity.this, MainActivity.class);

                Integer maxCpu = null;
                Integer maxThreads = null;
                String user = null;
                String pool = null;
                try {


                    maxCpu = (Integer) PreferenceUtils.getPrefsMap(getApplicationContext()).get("cpu");
                    maxThreads = (Integer) PreferenceUtils.getPrefsMap(getApplicationContext()).get("threads");
                    user = (String) PreferenceUtils.getPrefsMap(getApplicationContext()).get("user");
                    pool = (String) PreferenceUtils.getPrefsMap(getApplicationContext()).get("pool");
                } catch (Exception e) {
                    if (maxCpu == null) {
                        i.putExtra("cpu", 80);
                        maxCpu = 80;
                    }
                    if (maxThreads == null) {
                        i.putExtra("threads", 1);
                        maxThreads = 1;
                    }
                    if (user == null || user.equals("")) {
                        i.putExtra("user", getString(R.string.my_wallet));
                        user = getString(R.string.my_wallet);
                    }
                    if (pool == null || pool.equals("")) {
                        i.putExtra("pool", getString(R.string.my_pool));
                        pool = getString(R.string.my_pool);
                    }
                }

                i.putExtra("cpu", maxCpu);
                i.putExtra("threads", maxThreads);
                i.putExtra("user", user);
                i.putExtra("pool", pool);

                startActivity(i, savedInstanceState);
/*
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

    @Override
    protected void onResume() {
        super.onResume();
    }
}
