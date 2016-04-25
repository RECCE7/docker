FROM centos:7

MAINTAINER Jesse Nelson <jnels1242012@gmail.com>

RUN yum -y install epel-release
RUN yum -y install python34 rpm-build gcc make wget sudo which

RUN mkdir -p /install/ /etc/recce7/plugins /etc/recce7/configs /usr/local/sbin/recce7 /usr/local/lib/recce7/honeyDB /code/recce7 /var/log/recce7

RUN touch  /var/log/recce7/honypot.log

RUN chmod -R 777 /usr/local/lib/recce7/honeyDB/
RUN chmod -R 777 /var/log/recce7/

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

ADD  install.sh /usr/bin/

EXPOSE 80
EXPOSE 23
EXPOSE 8080
EXPOSE 8085
EXPOSE 8023

CMD ["/usr/bin/install.sh"]
#CMD ["/bin/bash"]