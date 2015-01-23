# Trending Time
## A watch face for Android Wear that displays the latest trending topics from Twitter - right on your wrist!

![Trending Time for Android Wear displays the latest trending topics from Twitter - right on your wrist!](https://dl.dropboxusercontent.com/u/12019700/glass-dev/tester-images/TrendingTimePreview.png) ![Trending Time for Android Wear logo](https://dl.dropboxusercontent.com/u/12019700/glass-dev/tester-images/TrendingTime-logo.png) 

### Genesis
I built Trending Time as a teaching tool for watch face development and also as an entertainment utility - the stuff that pops up throughout the day cracks me up. The lesson here is that watch faces don't always have to be all about expressing the time itself - the APIs provide the faciliy to do this easily. 

In this implmentation, pulling data into a watch face is the takeaway. So the re-drawing of the screen with fresh data according to when data becomes available is more dependent on the visibility state of the watch rather than some hard-coded timer or tick or animation loop.

### Usage
Select the "Trending Time" watch face on your smartwatch or from the Android Wear companion application. Then, launch the Trending Time app on your paired smartphone and tap the "Start updater" button, which activates live updates once per hour, and then sends them to the watch. You can safely close the app once the updater is running. 

The update process fires once per hour (inexactly), but you can configure the code to check for updates more or less frequently.
		
### Under the hood
Trending Time uses a custom proxy web handler hosted on Google App Engine to return the latest trending topics. The app uses the Data API to sync fresh trending topics between the phone and watch. In the wearable app, data is written to/pulled from SharedPreferences. Temboo's library for Twitter is used to handle the OAuth 1.0a credentials.

### Future development plans
Other features in the works include giving the user the ability to choose their region, and formatting the trending topics themselves in a neat visual tag cloud, without violating the wearable technology ethos of making UIs glanceable.


_Disclaimer: This was written as a personal utility and for public demonstration purposes, but I don't work for Google, Twitter, or Temboo. I'm just a total fanboy._

---

You can find out more wearable development tips in my book, [Designing and Developing for Google Glass](http://www.amazon.com/Designing-Developing-Google-Glass-Differently/dp/1491946458), by O'Reilly & Associates. **Thanks for your support! :)**
