#! /bin/bash
cp -r /code/recce7 /install/
cd /install/recce7/
python3 setup.py bdist_rpm
cd dist/
rpm -i recce7-1.0-1.noarch.rpm
touch /install/recce7.log
export RECCE7_DB=/usr/local/lib/recce7/
export RECCE7_PLUGIN_CONFIG=/etc/recce7/configs/plugins.cfg
export RECCE7_PATH=/usr/local/sbin/recce7/
echo $RECCE7_PATH
cat /etc/recce7/configs/plugins.cfg
cd $RECCE7_PATH
/usr/local/bin/authbind python3 -m framework.frmwork
#>> /install/recce7.log

