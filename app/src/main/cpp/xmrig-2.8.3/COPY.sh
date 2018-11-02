#!/bin/bash

#WD=$(dirname pwd)
#BASEDIR=$(dirname "$0")
#cd ${BASEDIR}

cp -f ./../../../../.externalNativeBuild/cmake/release/x86_64/xmrig  ./../../assets/x86_64/xmrig
cp -f ./../../../../.externalNativeBuild/cmake/release/x86/xmrig  ./../../assets/x86/xmrig
cp -f ./../../../../.externalNativeBuild/cmake/release/armeabi-v7a/xmrig ./../../assets/armeabi-v7a/xmrig
cp -f ./../../../../.externalNativeBuild/cmake/release/arm64-v8a/xmrig ./../../assets/arm64-v8a/xmrig

#cd ${WD}
