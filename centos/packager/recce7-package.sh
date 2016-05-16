#! /bin/bash
mkdir /install/
cp -r /code/recce7 /install/
cd /install/recce7/
python3 setup.py bdist_rpm
cd dist/
cp recce7-1.0-1.noarch.rpm /build
