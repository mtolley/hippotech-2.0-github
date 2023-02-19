#!/bin/bash

cd jest && npm install & 
bash ./download-latest-release.sh 
JAVA_OPTS="-Xmx512m"
 
# Deploy the Seeker Java Agent
if [ ! -z "${SEEKER_SERVER_URL}" ];
then
    # Download the Agent package
    curl -k -o /tmp/seeker-agent.zip "${SEEKER_SERVER_URL}/rest/api/latest/installers/agents/binaries/JAVA"
    # Unzip the Agent package
    unzip -d /tmp/seeker /tmp/seeker-agent.zip
    # Append the Seeker options to the JVM options
    JAVA_OPTS="-javaagent:/tmp/seeker/seeker-agent.jar ${JAVA_OPTS}"
fi
 
# Run the application
nohup bash -c "java ${JAVA_OPTS} -jar api.jar 2>&1 | tee hippotech.log &",

server_pid=$!

until grep -q -i 'Started ApiApplication' hippotech.log
do
  if ! ps $server_pid > /dev/null
  then
    echo "The server died" >&2
    exit 1
  fi
  echo -n "."
  sleep 1
done
echo
echo "HippoTech is running."