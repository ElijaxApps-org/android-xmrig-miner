#!/bin/bash
rm -fr ../src/main/assets/x86_64
rm -fr ../src/main/assets/x86
rm -fr ../src/main/assets/armeabi-v7a
rm -fr ../src/main/assets/arm64-v8a

mkdir -p ../src/main/assets/x86_64
mkdir -p ../src/main/assets/x86
mkdir -p ../src/main/assets/armeabi-v7a
mkdir -p ../src/main/assets/arm64-v8a

cp -f ../.externalNativeBuild/cmake/release/x86_64/xmrig  ../src/main/assets/x86_64/xmrig
cp -f ../.externalNativeBuild/cmake/release/x86/xmrig ../src/main/assets/x86/xmrig
cp -f ../.externalNativeBuild/cmake/release/armeabi-v7a/xmrig ../src/main/assets/armeabi-v7a/xmrig
cp -f ../.externalNativeBuild/cmake/release/arm64-v8a/xmrig ../src/main/assets/arm64-v8a/xmrig

cp -f ../libs/x86_64/libuv.a  ../src/main/assets/x86_64/libuv
cp -f ../libs/x86/libuv.a ../src/main/assets/x86/libuv
cp -f ../libs/armeabi-v7a/libuv.a ../src/main/assets/armeabi-v7a/libuv
cp -f ../libs/arm64-v8a/libuv.a ../src/main/assets/arm64-v8a/libuv

cp -rf ../src/main/assets/** ././../../app/src/main/assets/
