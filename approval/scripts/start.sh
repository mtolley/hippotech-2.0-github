#!/bin/bash

echo "Starting HippoTech approval backend..."
export JDBC_CONNECTION_STRING="jdbc:h2:mem:demodb"
output=$(mktemp "${TMPDIR:-/tmp/}$(basename 0).XXX")
java $JAVA_OPTS -jar ./target/approval.jar  &> $output &
server_pid=$!
echo "Server pid: $server_pid"
echo "Output: $output"
echo "Wait:"
until grep -q -i 'Started Approval' $output
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
echo "HippoTech approval back-end is running."
