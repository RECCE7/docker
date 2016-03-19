FROM centos:7

MAINTAINER Jesse Nelson <jnels1242012@gmail.com>

ENV RECCE7_OS_DIST centos

RUN yum -y install epel-release
RUN yum -y install python34 rpm-build gcc make wget sudo which

RUN mkdir -p /install/ /etc/recce7/plugins /etc/recce7/configs /usr/local/sbin/recce7 /usr/local/lib/recce7/honeyDB /code/recce7
RUN chmod 777 /usr/local/lib/recce7/honeyDB/

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

ADD  install.sh /usr/bin/

EXPOSE 80
EXPOSE 8083
EXPOSE 8023

CMD ["/usr/bin/install.sh"]