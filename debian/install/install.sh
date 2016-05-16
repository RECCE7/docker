#! /bin/bash

cd /build
dpkg -i python3-recce7_1.0-1_all.deb
apt-get install -f -y
cp /build/global.cfg /etc/recce7/configs/
cp /build/plugins.cfg /etc/recce7/configs/
sudo service recce7 start
tail -F /usr/sbin/recce7/recce7.log &
startReportServer.sh
