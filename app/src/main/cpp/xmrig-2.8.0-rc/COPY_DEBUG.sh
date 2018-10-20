#!/bin/bash

WD=$(dirname pwd)
BASEDIR=$(dirname "$0")
cd ${BASEDIR}

cp -f ../../../../.externalNativeBuild/cmake/debug/x86_64/xmrig  ../../assets/x86_64/xmrig
cp -f ../../../../.externalNativeBuild/cmake/debug/x86/xmrig  ../../assets/x86/xmrig
cp -f ../../../../.externalNativeBuild/cmake/debug/armeabi-v7a/xmrig ../../assets/armeabi-v7a/xmrig
cp -f ../../../../.externalNativeBuild/cmake/debug/arm64-v8a/xmrig ../../assets/arm64-v8a/xmrig

cd ${WD}
