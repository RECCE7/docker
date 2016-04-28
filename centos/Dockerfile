FROM centos:7

MAINTAINER Jesse Nelson <jnels1242012@gmail.com>

RUN yum -y install epel-release
RUN yum -y install python34 rpm-build gcc make wget sudo which

#####################################
####### Create honey pot files/dirs
#####################################
RUN mkdir -p /install/ /etc/recce7/plugins /etc/recce7/configs /usr/local/sbin/recce7 /usr/local/lib/recce7/honeyDB /code/recce7
RUN touch /usr/local/sbin/recce7/recce7.log
RUN chmod 666 /usr/local/sbin/recce7/recce7.log
RUN chmod -R 777 /usr/local/lib/recce7/honeyDB/

ENV RECCE7_OS_DIST centos
ENV RECCE7_DB_PATH /usr/local/lib/recce7/
ENV RECCE7_PLUGIN_CONFIG /etc/recce7/configs/plugins.cfg
ENV RECCE7_GLOBAL_CONFIG /etc/recce7/configs/global.cfg
ENV RECCE7_PATH /usr/local/sbin/recce7/

######################################
##### Install authbind
######################################
WORKDIR /install
RUN wget http://ftp.debian.org/debian/pool/main/a/authbind/authbind_2.1.1.tar.gz
RUN tar xvzf authbind_2.1.1.tar.gz
WORKDIR authbind-2.1.1
RUN make
RUN make install

######################################
#### Set up authbind
######################################
RUN touch /etc/authbind/byport/80
RUN chown nobody.nobody /etc/authbind/byport/80
RUN chmod 755 /etc/authbind/byport/80
RUN touch /etc/authbind/byport/23
RUN chown nobody.nobody /etc/authbind/byport/23
RUN chmod 755 /etc/authbind/byport/23


#####################################
###### Install pip dependencies
#####################################
RUN wget https://bootstrap.pypa.io/get-pip.py
RUN python3 get-pip.py
RUN pip3 install python-dateutil

#####################################
###### Add files to container
#####################################
ADD install.sh /usr/bin/

EXPOSE 80
EXPOSE 23
EXPOSE 8080
EXPOSE 8085
EXPOSE 8023

CMD ["/usr/bin/install.sh"]
#CMD ["/bin/bash"]