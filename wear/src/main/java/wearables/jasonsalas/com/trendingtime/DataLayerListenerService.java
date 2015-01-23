package wearables.jasonsalas.com.trendingtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

public class DataLayerListenerService extends WearableListenerService {

    private static final String PATH = "/trendingtopics";
    private static final String DATAMAP_KEY = "trendingTopics";
    private static final String TAG = "TrendingTime";

    SharedPreferences sharedPrefs;

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for(DataEvent event : dataEvents) {
            if(PATH.equals(event.getDataItem().getUri().getPath())) {
                DataMap dataMap = DataMapItem.fromDataItem(event.getDataItem()).getDataMap();
                String dataMapTopics = dataMap.getString(DATAMAP_KEY, "Waiting for new trending topics...");
                Log.d(TAG, String.format("DataMapItem: %s", dataMapTopics));

                sharedPrefs = getSharedPreferences("TrendingTimeData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.remove("freshTrendingTopics");
                editor.commit();
                editor.putString("freshTrendingTopics", dataMapTopics);
                editor.commit();
            }
        }
    }
}