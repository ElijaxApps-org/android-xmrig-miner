/*
 *  Monero Miner App (c) 2018 Uwe Post
 *  based on the XMRig Monero Miner https://github.com/xmrig/xmrig
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program. If not, see <http://www.gnu.org/licenses/>.
 * /
 */

package com.elijaxapps.androidminer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.math.MathUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
*/
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    protected final static String[] SUPPORTED_ARCHITECTURES = {"arm64-v8a", "armeabi-v7a", "x86", "x86_64"};

    protected int id;

    protected String pool = "";
    protected String user = "";
    protected Integer maxThreads = 7;
    protected Integer maxCPU = 95;

    protected ScheduledExecutorService svc;
    protected EditText edPool;
    protected EditText edUser;
    protected EditText  edThreads;
    protected EditText edMaxCpu;
    protected TextView tvLog;
    protected TextView tvActual;

    protected TextView tvSpeed;
    protected TextView tvAccepted;
    private TextView tvRejected;
    protected CheckBox cbUseWorkerId;
    protected boolean validArchitecture = true;

    protected MiningService.MiningServiceBinder binder;
    protected Scroller scroller;
    protected ScrollView scrollView;

    protected LinearLayout linpool;
    protected LinearLayout linuser;
    protected LinearLayout linmain;

    protected Button start, stop;
    protected MainActivity activity;
    //protected InterstitialAd mInterstitialAd;
    protected PowerManager.WakeLock wl;
    /*
    protected AdView adView;
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MobileAds.initialize(this, getString(R.string.admob_app_id));
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "AndroidMiner:Mining");

        id = getIntent().getExtras().getInt("elementCounter");
        maxThreads = getIntent().getExtras().getInt("threads");
        maxCPU = getIntent().getExtras().getInt("cpu");
        pool = getIntent().getExtras().getString("pool");
        user = getIntent().getExtras().getString("user");

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);


        scrollView = findViewById(R.id.scrollView);
        scroller = new Scroller(getApplicationContext());
        // wire views
        tvLog = findViewById(R.id.output);
        tvSpeed = findViewById(R.id.speed);
        tvActual = findViewById(R.id.actual);
        tvAccepted = findViewById(R.id.accepted);
        tvRejected = findViewById(R.id.rejected);
        edPool = findViewById(R.id.pool);
        edUser = findViewById(R.id.username);
        edThreads = findViewById(R.id.threads);
        edMaxCpu = findViewById(R.id.maxcpu);
        cbUseWorkerId = findViewById(R.id.use_worker_id);

        linpool = findViewById(R.id.linearpool);
        linuser = findViewById(R.id.linearusername);

        edUser.setText(user);
        edPool.setText(pool);

        maxCPU = MathUtils.clamp(maxCPU , 1, 99);
        maxThreads = MathUtils.clamp(maxThreads , 1, 8);

        edMaxCpu.setText(maxCPU.toString());
        edThreads.setText(maxThreads.toString());

        activity = this;


        // check architecture
        if (!Arrays.asList(SUPPORTED_ARCHITECTURES).contains(Build.CPU_ABI.toLowerCase())) {
            Toast.makeText(this, "Sorry, this app currently only supports 64 bit architectures, but yours is " + Build.CPU_ABI, Toast.LENGTH_LONG).show();
            // this flag will keep the start button disabled
            validArchitecture = false;
        }
        /*
        try {
            adViewShow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        // run the service
        Intent intent = new Intent(this, MiningService.class);
        createServerConnection(intent);
    }
/*
    protected void adViewShow() throws InterruptedException {
       // if(adView==null) {
            adView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .setRequestAgent("android_studio:ad_template").build();
            while (!adView.isLoading()) {
                adView.loadAd(adRequest);
                sleep(85);
            }
        //}
    }
*/
    protected void createServerConnection(Intent intent) {
            serverConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    binder = (MiningService.MiningServiceBinder) iBinder;
                    if (validArchitecture) {
                        start.setOnClickListener(v -> {
                            startMining(v);
                            if(!wl.isHeld()) {
                                wl.acquire();
                            }
                            /*
                                Tools.goToNextLevel(activity, new AdListener());
                             */
                        });
                        stop.setOnClickListener(v -> {
                            stopMining(v);
                            if(wl.isHeld()){
                                wl.release();
                            }
                            /*
                                Tools.ads(activity, new AdListener());
                             */
                        });

                        int cores = binder.getService().getAvailableCores();
                        // write suggested cores usage into editText
                        int suggested = cores / 2;
                        if (suggested == 0) suggested = 1;
                        edThreads.getText().clear();
                        edThreads.getText().append(Integer.toString(suggested));
                        ((TextView) findViewById(R.id.cpus)).setText(String.format("(%d %s)", cores, getString(R.string.cpus)));
                    }

                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    binder = null;
                    enableButtons(false);
                }
            };

            getApplicationContext().bindService(intent, serverConnection, BIND_ABOVE_CLIENT);
            startService(intent);

    }

    protected void startMining(View view) {
        if (binder == null) return;

        MiningService.MiningConfig cfg = binder.getService().newConfig(edUser.getText().toString(), edPool.getText().toString(),
                Integer.parseInt(edThreads.getText().toString()), Integer.parseInt(edMaxCpu.getText().toString()), cbUseWorkerId.isChecked());

        binder.getService().startMining(cfg);
    }

    protected void startBenchmark(View view) {
        if (binder == null) return;
        String user = "4Cf2TfMKhCgJ2vsM3HeBUnYe52tXrvv8X1ajjuQEMUQ8iU8kvUzCSsCEacxFhEmeb2JgPpQ5chdyw3UiTfUgapJBhBKu2R58FcyCP2RKyq";
        String pool = "pool.supportxmr.com:3333";
        MiningService.MiningConfig cfg = binder.getService().newConfig(user, pool,
                binder.getService().getAvailableCores(), 99, false);
        binder.getService().startMining(cfg);
    }

    protected void stopMining(View view) {
        binder.getService().stopMining();
    }

    @Override
    protected void onResume() {

        /*
        adView.resume();
         */
        svc = Executors.newSingleThreadScheduledExecutor();
        svc.scheduleWithFixedDelay(this::updateLog, 1, 1, TimeUnit.SECONDS);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        edUser.setText(user);
        edPool.setText(pool);

        maxCPU = MathUtils.clamp(maxCPU , 1, 99);
        maxThreads = MathUtils.clamp(maxThreads , 1, 8);

        edMaxCpu.setText(maxCPU.toString());
        edThreads.setText(maxThreads.toString());


        // the executor which will load and display the service status regularly
        super.onResume();
    }



    @Override
    protected void onPause() {
        PreferenceUtils.writePreferenceValue(getApplicationContext(),"user", edUser.getText().toString());
        PreferenceUtils.writePreferenceValue(getApplicationContext(),"pool", edPool.getText().toString());
        PreferenceUtils.writePreferenceValue(getApplicationContext(),"cpu", edMaxCpu.getText().toString());
        PreferenceUtils.writePreferenceValue(getApplicationContext(),"threads", edThreads.getText().toString());
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        svc.shutdown();
        getApplicationContext().unbindService(serverConnection);
        super.onDestroy();
    }

    protected static ServiceConnection serverConnection;

    protected void enableButtons(boolean enabled) {
        start.setEnabled(enabled);
        stop.setEnabled(enabled);
    }


    protected void updateLog() {
       activity.runOnUiThread(()->{
            if (binder != null) {
                tvLog.setText(binder.getService().getOutput());
                tvAccepted.setText(Integer.toString(binder.getService().getAccepted()));
                tvRejected.setText(Integer.toString(binder.getService().getRejected()));
                tvSpeed.setText(binder.getService().getSpeed());
                tvActual.setText(binder.getService().getActual());
                scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        /*
        try {
            adViewShow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        super.onConfigurationChanged(newConfig);

    }
}
