#  Android XMRig Miner

XMRig miner built inside an android app

This app is based on the proof of concept provided by https://github.com/upost/MoneroMiner

I updated dependencies (libuv-1.23.1 and xmrig-2.8.0-rc) and the project to Android Studio 3.2.

# Releases

[<img src="https://f-droid.org/badge/get-it-on.png"
     alt="Get it on F-Droid"
     height="80">](https://f-droid.org/packages/org.elijaxapps.androidxmrigminer/)

Otherwise, you can download the app directly from the [Releases tab](https://github.com/ElijaxApps-org/android-xmrig-miner/releases)

# Compile instructions
Clone the repository and run the following command on base dir:
```
gradle clean :libuv:assembleRelease :app:assembleRelease
```
Then you can sign your .apk in order to install it (release builds must be digital signed before install).

# Dependencies

Android Studio.
Download both SDK and NDK with sdk-manager.
Gradle 4.10.2

# Credits
libuv by http://libuv.org
xmrig by https://github.com/xmrig

Original project set up:
https://github.com/upost/MoneroMiner

# Donate:
Paypal: https://paypal.me/ElijaxApps
# Bitcoin:
Address: 37GpugVZNiof2DzWQX5aivHewc4wZLxATL
