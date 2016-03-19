#! /bin/bash

docker run -i -p 80:80 -p 8083:8083 -p 8023:8023 --name pi -v $RECCE7_BUILD_DIR:/code/recce7 local/pi-centos
