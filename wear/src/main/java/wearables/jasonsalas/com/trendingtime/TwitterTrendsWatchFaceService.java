package wearables.jasonsalas.com.trendingtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;

public class TwitterTrendsWatchFaceService extends CanvasWatchFaceService {

    private static final String TAG = "TrendingTime";

    SharedPreferences sharedPrefs;

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine {

        /*
        * for simplicity (and battery-saving) purposes, the background image isn't displayed (but uncomment the code in onCreate() to do so,
        * so that the UI is white text on a blackground, which works in interactive mode and ambient mode
        * */

        Bitmap mBackground;
        Bitmap mBackgroundScaledBitmap;
        Paint trendingTopicsStyle;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);

            Log.i(TAG, "onCreate()");

//            Resources resources = TwitterTrendsWatchFaceService.this.getResources();
//            Drawable backgroundDrawable = resources.getDrawable(R.drawable.app_icon);
//            mBackground = ((BitmapDrawable) backgroundDrawable).getBitmap();

            setWatchFaceStyle(new WatchFaceStyle.Builder(TwitterTrendsWatchFaceService.this)
                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
                    .setPeekOpacityMode(WatchFaceStyle.PEEK_OPACITY_MODE_TRANSLUCENT)
                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                    .setStatusBarGravity(Gravity.LEFT)
                    .setShowSystemUiTime(true)
                    .build());

            trendingTopicsStyle = new Paint();
            trendingTopicsStyle.setColor(Color.LTGRAY);
            trendingTopicsStyle.setStrokeWidth(2.0f);
            trendingTopicsStyle.setStyle(Paint.Style.FILL);
            trendingTopicsStyle.setTextSize(18);
            trendingTopicsStyle.setTextAlign(Paint.Align.CENTER);
            trendingTopicsStyle.setAntiAlias(true);

            sharedPrefs = getSharedPreferences("TrendingTimeData", Context.MODE_PRIVATE);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        /* updates every 60 seconds by default */
        @Override
        public void onTimeTick() {
            super.onTimeTick();

            Log.i(TAG, "onTimeTick()");
            invalidate();
        }

        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);
            Log.i(TAG, "onPropertiesChanged()");
            invalidate();
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);
            Log.i(TAG, "onAmbientModeChanged()");
            invalidate();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            Log.i(TAG, "onVisibilityChanged()");
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            Log.i(TAG, "onDraw()");

            int width = bounds.width();
            int height = bounds.height();

            // scale the background image to fit the watch display
//            if(mBackgroundScaledBitmap == null || mBackgroundScaledBitmap.getWidth() != width || mBackgroundScaledBitmap.getHeight() != height) {
//                mBackgroundScaledBitmap = Bitmap.createScaledBitmap(mBackground, width, height, true);
//            }
//            canvas.drawBitmap(mBackgroundScaledBitmap, 0, 0, null);

            int centerX = width / 2;
            int centerY = (height / 2) - 25;

            // clear the canvas so as to prevent double-draws, which makes the text come out as bold with screen-flickering
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            // fetch the trending topics from the SharedPreferences data store and draw them to the screen
            String freshTopics = sharedPrefs.getString("freshTrendingTopics", "New trending topics soon!");
            Log.i(TAG, "stored trending topics: " + freshTopics);
            String[] topicsArray = freshTopics.split(";", 6);

            for(String topic : topicsArray) {
                canvas.drawText(topic, centerX, centerY, trendingTopicsStyle);
                centerY += 20;
            }
        }
    }
 }