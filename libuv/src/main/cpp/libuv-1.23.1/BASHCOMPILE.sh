#!/usr/bin/env bash

export WD=$(dirname `pwd`)
export BASEDIR=$(dirname "$0")
cd ${BASEDIR}

export _NDK=$ANDROID_NDK

rm -fr out arm arm64 x86 x86_64 android-toolchain-**

chmod +x ./gyp_uv.py
chmod +x ./build/gyp -R

source android-configure-arm ${_NDK} gyp 23
BUILDTYPE=Release make -C out
mv -f out arm


source android-configure-arm64 ${_NDK} gyp 23
BUILDTYPE=Release make -C out
mv -f out arm64

source android-configure-x86 ${_NDK} gyp 23
BUILDTYPE=Release make -C out
mv -f out x86

source android-configure-x86_64 ${_NDK} gyp 23
BUILDTYPE=Release make -C out
mv -f out x86_64

unset _NDK

cd ${WD}

unset WD
unset BASEDIR