FROM debian
MAINTAINER Jesse Nelson <jnels1242012@gmail.com>

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update
RUN apt-get -y install apt-utils
RUN apt-get -y install man
RUN apt-get -y install sudo
RUN apt-get -y install python3-pip

#####################################
###### Install
#####################################
COPY install.sh /usr/bin

#####################################
###### Default Install Plugin Ports
#####################################
EXPOSE 23
EXPOSE 443
EXPOSE 80
EXPOSE 22

#####################################
###### Report Server Port
#####################################
EXPOSE 8080

CMD ["/usr/bin/install.sh"]
#CMD ["/bin/bash"]