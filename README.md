#  Android XMRig Miner

XMRig miner built inside an android app

This app is based on the proof of concept provided by https://github.com/upost/MoneroMiner

Deps have been updated and a lot of the project is at least in working order with modern tooling. Releases are not available at this time, I have done no real Android development in the past, but if the original author would truly like to abandon the project, I will work towards getting it published myself.

# Releases

[<img src="https://f-droid.org/badge/get-it-on.png"
     alt="Get it on F-Droid"
     height="80">](https://f-droid.org/packages/org.elijaxapps.androidxmrigminer/)

Otherwise, you can download the app directly from the [Releases tab](https://github.com/ElijaxApps-org/android-xmrig-miner/releases)

# Compile instructions
Clone the repository and run the following command on base dir:
```
gradle clean :app:assembleRelease
```
Then you can sign your .apk in order to install it (release builds must be digital signed before install).

# Dependencies

Android Studio.
Download both SDK and NDK with sdk-manager.
Gradle 7 (NOTE: There is a warning that this will have to be updated again for Gradle 8 due to JCenter going down)

# Credits
libuv by http://libuv.org
xmrig by https://github.com/xmrig

Original project set up:
https://github.com/upost/MoneroMiner

# Donate:
Paypal: https://paypal.me/ElijaxApps
# Bitcoin:
Address: bc1q4u4pfpl992xfzzka7hvvvgm3ga34dzdmtr5jcx
