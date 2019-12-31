#!/usr/bin/env bash
app_name='springboot-zk-test'
docker stop ${app_name}
echo '----stop container----'
docker rm ${app_name}
echo '----rm container----'
docker run -p 8094:8094 --name ${app_name} \
-v /etc/localtime:/etc/localtime \
-v /mydata/app/${app_name}/logs:/var/logs \
-d mall-tiny/${app_name}:0.0.1-SNAPSHOT
echo '----start container----'
docker rmi `docker images | grep  "<none>" | awk '{print $3}'`
echo 'delete all <none> image'