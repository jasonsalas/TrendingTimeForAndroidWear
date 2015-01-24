# Trending Time
## A watch face for Android Wear that displays the latest trending topics from Twitter - right on your wrist!

![Trending Time for Android Wear displays the latest trending topics from Twitter - right on your wrist!](https://dl.dropboxusercontent.com/u/12019700/glass-dev/tester-images/TrendingTimePreview.png) ![Trending Time for Android Wear logo](https://dl.dropboxusercontent.com/u/12019700/glass-dev/tester-images/TrendingTime-logo.png) 

### Download from Google Play
If you'd just like to install and use the app, you can [install it from Google Play](https://play.google.com/store/apps/details?id=wearables.jasonsalas.com.trendingtime) onto your paired Android phone. The APK includes the wearable app, which is automatically pushed to your watch moments after you install the mobile app. The watch face will then be available in the watch face chooser in a few minutes.

### System requirements
To run the app on your own hardware, you'll need the following:
* An Android Wear smartwatch running Android 5.0.x
* An Android smartphone running at least Android 4.4.2 
* The [Android Wear](https://play.google.com/store/apps/details?id=com.google.android.wearable.app) companion app

### Under the hood
Trending Time was build with [Android Studio v.1.0.2](http://developer.android.com/tools/studio/index.html), using the [Watch Face API](https://developer.android.com/training/wearables/watch-faces/index.html) for Android Wear. It was developed for an [LG G Watch](http://www.lg.com/global/gwatch/index.html) using a square watch face. It _should_ render the trending topics fine on circular watch faces, or even devices that use the "chin".

Trending Time uses a custom proxy web handler hosted on Google App Engine to return the latest trending topics. The app uses the [Data API](http://developer.android.com/training/wearables/data-layer/index.html) to sync fresh trending topics between the phone and watch. In the wearable app, data is written to/pulled from [SharedPreferences](http://developer.android.com/reference/android/content/SharedPreferences.html). [Temboo's library for Twitter](https://www.temboo.com/library/Library/Twitter/) calling the [Place API](https://www.temboo.com/library/Library/Twitter/Trends/Place/), is used to handle the required OAuth 1.0a credentials. In Version 1.0, the **'USA'** region is hard-coded to pull trending topics from there. 

You can find out more about the data communication flow [on the project's wiki](https://github.com/jasonsalas/TrendingTime/wiki/Data-communications-architecture).

### Genesis
I built Trending Time as a teaching tool for watch face development and also as an entertainment utility - the stuff that pops up throughout the day cracks me up and is often extremely informative. This type of quick-hit snapshot of what's happening in the world is precisely the type of UX that wearables rock at delivering. The key lesson here is that watch faces don't always have to be about expressing time itself. In this case, the time is actually secondary, being a data-driven utility. 

The trick is manipulating the [WatchfaceService.Engine](https://developer.android.com/reference/android/support/wearable/watchface/WatchFaceService.Engine.html) lifecycle, specifically calling [View.invalidate()](https://developer.android.com/reference/android/view/View.html#invalidate()) within the [onPropertiesChanged](https://developer.android.com/reference/android/support/wearable/watchface/WatchFaceService.Engine.html#onPropertiesChanged(android.os.Bundle)) handler to force a redraw of the screen. This is less draining on the battery overall that a time-based redraw heuristic. So the re-drawing of the screen with fresh data according to when data becomes available is more dependent on the visibility state of the watch rather than some hard-coded timer or tick or animation loop.

### Usage
Here's how to use Trending Time once you've built (or installed) it: 
* Select the **"Trending Time"** watch face on your smartwatch or from the Android Wear companion application
* Launch the Trending Time app on your paired smartphone and tap the **"Start updater"** button, which activates updates and syncs them with your watch
* You can safely close the app once the updater is running
* If you need to stop updates, just launch the mobile app again and tap **"Stop updater"**

The updater process fires once per hour (inexactly) by way of an [AlarmManager](http://developer.android.com/reference/android/app/AlarmManager.html), but you can configure the code to check for updates more or less frequently as you wish.

### Future development plans
Other features in the works include:
* Giving the user the ability to set their own region for which trending topics
* Selectable formatting of the trending topics themselves in a neat visual tag cloud, without violating the wearable technology ethos of making UIs glanceable


_Disclaimer: This was written as a personal utility and for public demonstration purposes, but I don't work for Google, Twitter, or Temboo. I'm just a total fanboy._

---

You can find out more wearable development tips in my book, [Designing and Developing for Google Glass](http://www.amazon.com/Designing-Developing-Google-Glass-Differently/dp/1491946458), by O'Reilly & Associates. **Thanks for your support! :)**
