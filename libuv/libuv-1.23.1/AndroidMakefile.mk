#!/bin/bash

rm -rf build/ arm/ arm64/ x86/ x86_64/
mkdir build/ 
cd build/
git clone https://chromium.googlesource.com/external/gyp
cd gyp/
sudo python setup.py install
cd ../
cd ../

source android-configure-arm /usr/lib/android-ndk/ gyp 23
BUILDTYPE=Release make -C out
mv -f out arm

source android-configure-arm64 /usr/lib/android-ndk/ gyp 23
BUILDTYPE=Release make -C out
mv -f out arm64

source android-configure-x86 /usr/lib/android-ndk/ gyp 23
BUILDTYPE=Release make -C out
mv -f out/ x86/

source android-configure-x86_64 /usr/lib/android-ndk/ gyp 23
BUILDTYPE=Release make -C out
mv -f out x86_64

rm -fr ../../src/main/assets/x86_64
rm -fr ../../src/main/assets/x86
rm -fr ../../src/main/assets/armeabi-v7a
rm -fr ../../src/main/assets/arm64-v8a

mkdir -p ../../src/main/assets/x86_64
mkdir -p ../../src/main/assets/x86
mkdir -p ../../src/main/assets/armeabi-v7a
mkdir -p ../../src/main/assets/arm64-v8a

cp -f x86_64/Release/libuv.a  ../../src/main/assets/x86_64/libuv
cp -f x86/Release/libuv.a ../../src/main/assets/x86/libuv
cp -f arm/Release/libuv.a ../../src/main/assets/armeabi-v7a/libuv
cp -f arm64/Release/libuv.a ../../src/main/assets/arm64-v8a/libuv

cp -r ././../src/main/assets/** ././../../../xmrig_deps/libs/
