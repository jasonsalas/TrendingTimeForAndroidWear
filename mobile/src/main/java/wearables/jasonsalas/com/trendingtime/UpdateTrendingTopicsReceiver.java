package wearables.jasonsalas.com.trendingtime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class UpdateTrendingTopicsReceiver extends BroadcastReceiver {

    private static final String TAG = "TrendingTime";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "starting updater service");
        Toast.makeText(context, "hit receiver", Toast.LENGTH_SHORT).show();
        Intent trendsIntent = new Intent(context, UpdateTrendingTopicsService.class);
        context.startService(trendsIntent);
     }
}