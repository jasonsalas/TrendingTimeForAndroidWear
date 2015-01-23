package wearables.jasonsalas.com.trendingtime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootstrapReceiver extends BroadcastReceiver {

    /*
    * ensures that the updater service persists during cases when the mobile devices reboots
    * */

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            // create a recurring alarm to fire scheduled updates
            Intent alarmIntent = new Intent(context, UpdateTrendingTopicsReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
            Toast.makeText(context, "Updating Trending Time topics...", Toast.LENGTH_LONG).show();
        }
    }
}
