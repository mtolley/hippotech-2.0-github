#!/bin/bash

echo "Starting HippoTech"
#output=$(mktemp "${TMPDIR:-/tmp/}$(basename 0).XXX")
mkdir -p logs
output=`pwd`/logs/hippotech.log
#java $JAVA_OPTS -jar ./target/api.jar  &> $output &
java $JAVA_OPTS -jar ./target/api.jar | tee $output &
server_pid=$!
echo "Server pid: $server_pid"
echo "Output: $output"
echo "Waiting for ready state"
until grep -q -i 'Started ApiApplication' $output
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
