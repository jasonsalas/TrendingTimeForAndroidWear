package wearables.jasonsalas.com.trendingtime;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ConfigurationActivity extends Activity {

    private PendingIntent pendingIntent;
    private static final String TAG = "TrendingTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configration_settings);

        Intent alarmIntent = new Intent(ConfigurationActivity.this, UpdateTrendingTopicsReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(ConfigurationActivity.this, 0, alarmIntent, 0);

        findViewById(R.id.startUpdater).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
                Log.i(TAG, "created recurring updates");
                Toast.makeText(ConfigurationActivity.this, "Trending topics update started. You can safely close this app now.", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.stopUpdater).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                manager.cancel(pendingIntent);
                Toast.makeText(ConfigurationActivity.this, "Trending topics update stopped", Toast.LENGTH_SHORT).show();
            }
        });
    }
}