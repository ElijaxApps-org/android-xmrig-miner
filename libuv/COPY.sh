#!/bin/bash

WD=$(dirname pwd)
BASEDIR=$(dirname "$0")
cd ${BASEDIR}

mkdir -p ./src/main/assets

rm -fr ./src/main/assets/x86_64
rm -fr ./src/main/assets/x86
rm -fr ./src/main/assets/armeabi-v7a
rm -fr ./src/main/assets/arm64-v8a

mkdir -p ./src/main/assets/x86_64
mkdir -p ./src/main/assets/x86
mkdir -p ./src/main/assets/armeabi-v7a
mkdir -p ./src/main/assets/arm64-v8a

mkdir -p ./../app/src/main/cpp/xmrig-2.8.3/src/libs
mkdir -p ./../app/src/main/assets

#cp -f ./build/intermediates/cmake/debug/obj/arm64-v8a/libuv.so ./src/main/assets/arm64-v8a/libuv.so
#cp -f ./build/intermediates/cmake/debug/obj/armeabi-v7a/libuv.so ./src/main/assets/armeabi-v7a/libuv.so
#cp -f ./build/intermediates/cmake/debug/obj/x86/libuv.so ./src/main/assets/x86/libuv.so
#cp -f ./build/intermediates/cmake/debug/obj/x86_64/libuv.so ./src/main/assets/x86_64/libuv.so

cp -f ./build/intermediates/cmake/release/obj/arm64-v8a/libuv.so ./src/main/assets/arm64-v8a/libuv.a
cp -f ./build/intermediates/cmake/release/obj/armeabi-v7a/libuv.so ./src/main/assets/armeabi-v7a/libuv.a
cp -f ./build/intermediates/cmake/release/obj/x86/libuv.so ./src/main/assets/x86/libuv.a
cp -f ./build/intermediates/cmake/release/obj/x86_64/libuv.so ./src/main/assets/x86_64/libuv.a


cp -rf ./src/main/assets/** ./../app/src/main/cpp/xmrig-2.8.3/src/libs
cp -rf ./src/main/assets/** ./../app/src/main/assets

cd ${WD}
