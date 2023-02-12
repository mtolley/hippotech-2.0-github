#!/bin/bash

echo "Starting HippoTech blog backend..."
output=$(mktemp "${TMPDIR:-/tmp/}$(basename 0).XXX")
npm start  &> $output &
server_pid=$!
echo "Server pid: $server_pid"
echo "Output: $output"
echo "Wait:"
until grep -q -i 'Blog Backend is listening' $output
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
echo "HippoTech blog back-end is running."
