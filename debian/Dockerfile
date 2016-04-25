FROM debian

MAINTAINER Jesse Nelson <jnels1242012@gmail.com>

ENV RECCE7_OS_DIST centos

RUN apt-get update
RUN apt-get -y install apt-utils
RUN apt-get -y install man
RUN apt-get -y install python3.4
RUN apt-get -y install authbind

RUN mkdir -p /install/ /etc/recce7/plugins /etc/recce7/configs /usr/local/sbin/recce7 /usr/local/lib/recce7/honeyDB /code/recce7
RUN chmod 777 /usr/local/lib/recce7/honeyDB/

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

CMD ["/bin/bash"]