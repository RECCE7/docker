#! /bin/bash
cp -r /code/recce7 /install/
cd /install/recce7/
python3 setup.py bdist_rpm
cd dist/
rpm -i recce7-1.0-1.noarch.rpm

cat /etc/recce7/configs/plugins.cfg
echo $RECCE7_PATH
echo test
cd $RECCE7_PATH
/usr/local/bin/authbind python3 -m framework.frmwork && python3 -m reportserver.server.main.py
