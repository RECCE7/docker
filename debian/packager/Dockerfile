FROM debian

MAINTAINER Jesse Nelson <jnels1242012@gmail.com>

RUN apt-get update
RUN apt-get -y install apt-utils
RUN apt-get -y install man
RUN apt-get -y install sudo

#######################################
###### Install build deps
#######################################
RUN apt-get -y install python3
RUN apt-get -y install python3-all
RUN apt-get -y install python3-pip
RUN apt-get -y install wget
RUN apt-get -y install debhelper python3-all python-all
RUN pip3 install stdeb

#####################################
####### Deb Location
#####################################
#RUN mkdir /build

#####################################
###### Add files to container
#####################################
COPY recce7-package.sh  /usr/bin/

CMD ["/usr/bin/recce7-package.sh"]
#CMD ["/bin/bash"]