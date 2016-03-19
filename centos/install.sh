#! /bin/bash
cp -r /code/recce7 /install/
cd /install/recce7/honeypot/src/
python3 setup.py bdist_rpm
cd dist/
rpm -i recce7-1.0-1.noarch.rpm
touch /install/recce7.log
startHoneyPot.sh
#>> /install/recce7.log

