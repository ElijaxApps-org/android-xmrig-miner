#!/bin/bash

WD=$(dirname pwd)
BASEDIR=$(dirname "$0")
cd ${BASEDIR}

cp  ./deps/x86_64/lib/libcrypto.a  ../../../assets/x86_64/libcrypto.a
cp  ./deps/x86_64/lib/libssl.a  ../../../assets/x86_64/libssl.a
cp  ./deps/x86_64/lib/libhwloc.a  ../../../assets/x86_64/libhwloc.a
cp  ./deps/x86_64/lib/libuv.a  ../../../assets/x86_64/libuv.a

cp  ./deps/x86/lib/libcrypto.a  ../../../assets/x86/libcrypto.a
cp  ./deps/x86/lib/libssl.a  ../../../assets/x86/libssl.a
cp  ./deps/x86/lib/libhwloc.a  ../../../assets/x86/libhwloc.a
cp  ./deps/x86/lib/libuv.a  ../../../assets/x86/libuv.a


cp  ./deps/armeabi-v7a/lib/libcrypto.a  ../../../assets/armeabi-v7a/libcrypto.a
cp  ./deps/armeabi-v7a/lib/libssl.a  ../../../assets/armeabi-v7a/libssl.a
cp  ./deps/armeabi-v7a/lib/libhwloc.a  ../../../assets/armeabi-v7a/libhwloc.a
cp  ./deps/armeabi-v7a/lib/libuv.a  ../../../assets/armeabi-v7a/libuv.a

cp  ./deps/arm64-v8a/lib/libcrypto.a  ../../../assets/arm64-v8a/libcrypto.a
cp  ./deps/arm64-v8a/lib/libssl.a  ../../../assets/arm64-v8a/libssl.a
cp  ./deps/arm64-v8a/lib/libhwloc.a  ../../../assets/arm64-v8a/libhwloc.a
cp  ./deps/arm64-v8a/lib/libuv.a  ../../../assets/arm64-v8a/libuv.a

cd ${WD}
