FROM centos:7

MAINTAINER Jesse Nelson <jnels1242012@gmail.com>

RUN yum -y install epel-release
RUN yum -y install python34 rpm-build gcc make wget sudo which


#####################################
###### Add files to container
#####################################
ADD recce7-package.sh /usr/bin/

CMD ["/usr/bin/recce7-package.sh"]
#CMD ["/bin/bash"]
