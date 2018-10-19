#!/usr/bin/env bash

WD=$(dirname pwd)
BASEDIR=$(dirname "$0")
cd ${BASEDIR}

export NDK=~/android-ndk/

rm -fr out arm arm64 x86 x86_64 android-toolchain-**

source android-configure-arm ${NDK} gyp 23
BUILDTYPE=Release make -C out
mv -f out arm

source android-configure-arm64 ${NDK} gyp 23
BUILDTYPE=Release make -C out
mv -f out arm64

source android-configure-x86 ${NDK} gyp 23
BUILDTYPE=Release make -C out
mv -f out x86

source android-configure-x86_64 ${NDK} gyp 23
BUILDTYPE=Release make -C out
mv -f out x86_64

mkdir -p ../src/main/assets

rm -fr ../src/main/assets/x86_64
rm -fr ../src/main/assets/x86
rm -fr ../src/main/assets/armeabi-v7a
rm -fr ../src/main/assets/arm64-v8a

mkdir -p ../src/main/assets/x86_64
mkdir -p ../src/main/assets/x86
mkdir -p ../src/main/assets/armeabi-v7a
mkdir -p ../src/main/assets/arm64-v8a

cp -f x86_64/Release/libuv.a ../src/main/assets/x86_64/libuv.a
cp -f x86/Release/libuv.a ../src/main/assets/x86/libuv.a
cp -f arm/Release/libuv.a ../src/main/assets/armeabi-v7a/libuv.a
cp -f arm64/Release/libuv.a ../src/main/assets/arm64-v8a/libuv.a

mkdir -p ../../app/src/main/cpp/xmrig-2.8.0-rc/src/libs
cp -rf ../src/main/assets/** ../../app/src/main/cpp/xmrig-2.8.0-rc/src/libs

cd ${WD}

