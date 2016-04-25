#! /bin/bash


docker run -d -h pi -i -p 8085:8085 -p 8080:8080 -p 8083:8083 -p 23:23 --name pi -v $RECCE7_BUILD_DIR:/code/recce7 local/pi-centos
#docker run -i -p 80:80 -p 8083:8083 -p 23:23 --name pi -v $RECCE7_BUILD_DIR:/code/recce7 local/pi-centos
docker run -d -p 80:80 -p 81:81 -p 8125:8125/udp -p 8126:8126 -p 2003:2003 --link pi:pi --name grafana local/grafana
