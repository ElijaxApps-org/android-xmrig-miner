#!/usr/bin/env bash

rm -rf build arm arm64 x86 x86_64
mkdir build
cd build
git clone https://chromium.googlesource.com/external/gyp
cd gyp
python setup.py install
cd ../..
