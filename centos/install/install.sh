#! /bin/bash

sed -i -e 's/Defaults    requiretty.*/ #Defaults    requiretty/g' /etc/sudoers
cd /build
yum install -y recce7-1.0-1.noarch.rpm
cp /build/global.cfg /etc/recce7/configs/
cp /build/plugins.cfg /etc/recce7/configs/
/etc/init.d/recce7 start
tail -F /usr/sbin/recce7/recce7.log &
startReportServer.sh





