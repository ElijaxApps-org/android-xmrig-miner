#!/bin/bash

WD=$(dirname pwd)
BASEDIR=$(dirname "$0")
cd ${BASEDIR}

mkdir -p ../src/main/assets

rm -fr ../src/main/assets/x86_64
rm -fr ../src/main/assets/x86
rm -fr ../src/main/assets/armeabi-v7a
rm -fr ../src/main/assets/arm64-v8a

mkdir -p ../src/main/assets/x86_64
mkdir -p ../src/main/assets/x86
mkdir -p ../src/main/assets/armeabi-v7a
mkdir -p ../src/main/assets/arm64-v8a

mkdir -p ../../app/src/main/cpp/xmrig-2.8.3/src/libs
mkdir -p ../../app/src/main/assets

cp -f x86_64/Debug/libuv.a ../src/main/assets/x86_64/libuv.a
cp -f x86/Debug/libuv.a ../src/main/assets/x86/libuv.a
cp -f arm/Debug/libuv.a ../src/main/assets/armeabi-v7a/libuv.a
cp -f arm64/Debug/libuv.a ../src/main/assets/arm64-v8a/libuv.a


cp -rf ../src/main/assets/** ../../app/src/main/assets/xmrig-2.8.3/src/libs
cp -rf ../src/main/assets/** ../../app/src/main/assets

cd ${WD}
