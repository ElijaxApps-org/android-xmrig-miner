#!/bin/bash
WD=$(dirname pwd)
BASEDIR=$(dirname "$0")
cd ${BASEDIR}

cp -r ./src/main/cpp/xmrig-2.8.3/src/libs/**  ./src/main/assets/

#cp -f .externalNativeBuild/cmake/debug/x86_64/xmrig  ./src/main/assets/x86_64/xmrig
#cp -f .externalNativeBuild/cmake/debug/x86/xmrig  ./src/main/assets/x86/xmrig
#cp -f .externalNativeBuild/cmake/debug/armeabi-v7a/xmrig ./src/main/assets/armeabi-v7a/xmrig
#cp -f .externalNativeBuild/cmake/debug/arm64-v8a/xmrig ./src/main/assets/arm64-v8a/xmrig

cp -f .externalNativeBuild/cmake/release/x86_64/xmrig  ./src/main/assets/x86_64/xmrig
cp -f .externalNativeBuild/cmake/release/x86/xmrig  ./src/main/assets/x86/xmrig
cp -f .externalNativeBuild/cmake/release/armeabi-v7a/xmrig ./src/main/assets/armeabi-v7a/xmrig
cp -f .externalNativeBuild/cmake/release/arm64-v8a/xmrig ./src/main/assets/arm64-v8a/xmrig


cd ${WD}