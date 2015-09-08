# Android Hackathon Workshop

September 8 2015


[Concordia University District 3 Innovation Center](http://d3center.ca)

[GDG Android Montreal](http://android-montreal.com)

[@cesine](https://github.com/cesine) & [@kanawish](https://github.com/kanawish)


# Firebase/CouchDB Tutorial for Android

A shared todo app that shows how to use a NoSQL cloud database to build an interactive collaborative ToDo app (or similar app, where you have a list if items with pictures) at a hackathon.



[slides](https://docs.google.com/presentation/d/1tRiWAKbiv2D80MhvsP_B9HyOlM1Yv5B-YRCl7bv7YEM/edit?usp=sharing)

# End result

![screenshot](http://f.cl.ly/items/1K2e200t2D3s1l0i473e/ToDoLite.gif)

## Prequisites

* [Android Studio](http://developer.android.com/sdk/installing/studio.html) 
* [Git](https://git-scm.com/downloads) (or use [GitHub app](https://desktop.github.com))

## Get the code

```
$ git clone https://github.com/cesine/AndroidFirebaseTutorial.git
$ cd AndroidFirebaseTutorial.git
$ git submodule init && git submodule update
```

## Build and run the app

* Import the project into your Android Studio by selecting `build.gradle` or `settings.gradle` from the root of the project.
* Run the app using the "play" or "debug" button.


# Try Firebase

## Sign up for Firebase

https://www.firebase.com

## Point the app at your database

```
$ echo FIREBASE_URL=https://YOUR_DATABASE_IDENTIFIER.firebaseio.com >> local.properties
```

## Testing Firebase

Want to see how to do something? You can write a unit test to contact firebase. There are a few tests in the DemoSyncAdapter project.

1. Open the DemoSyncAdapter project in Android Studio
1. Right click on the test you want to run and select "Run"



# Try CouchDB

After you have played with Firebase a bit, you can use the same app to try connecting to a [CouchDB](http://couchdb.apache.org).

## Download CouchDB

http://couchdb.apache.org

## Create a DB 

Open [the Futon http://127.0.0.1:5984/_utils](http://127.0.0.1:5984/_utils) and click on "Create Database" call it `mysupertododlist`. 

## Point the app at your database

Change the `SYNC_URL_HTTP` in the build.gradle to go to your local database http://10.0.2.2:5964/mysupertododlist. ([localhost](http://stackoverflow.com/questions/5806220/how-to-connect-to-my-http-localhost-web-server-from-android-emulator-in-eclips) is `10.0.2.2` if you are using an emulator).



# Next steps

Once you have decided which database you want to use, you can turn off the other one. 


## License

Based on a fork of original [the ToDo Lite Android app](http://github.com/couchbaselabs/ToDoLite-Android), which demos how to connect to a [Couchbase](http://www.couchbase.com/nosql-databases/couchbase-mobile) database.

Released under the Apache license, 2.0.

Copyright 2011-2014, Couchbase, Inc.
