#!/bin/bash


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
mkdir -p ../../xmrig_deps/libs
cp -rf ../src/main/assets/** ../../xmrig_deps/libs
