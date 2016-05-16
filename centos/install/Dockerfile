FROM centos:7

MAINTAINER Jesse Nelson <jnels1242012@gmail.com>

RUN yum -y install epel-release sudo

#####################################
###### Add files to container
#####################################
ADD install.sh /usr/bin/

EXPOSE 80
EXPOSE 23
EXPOSE 443
EXPOSE 8080

CMD ["/usr/bin/install.sh"]
#CMD ["/bin/bash"]