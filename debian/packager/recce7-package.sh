#! /bin/bash

mkdir -p /install/
cp -r /code/recce7 /install/
cd /install/recce7/
python3 setup.py --command-packages=stdeb.command  bdist_deb
cd deb_dist/recce7-1.0/
cp /install/recce7/install/scripts/preinst /install/recce7/deb_dist/recce7-1.0/debian/
cp /install/recce7/install/scripts/postinst /install/recce7/deb_dist/recce7-1.0/debian/
dpkg-buildpackage -rfakeroot -uc -us
cp ../python3-recce7_1.0-1_all.deb /build


