#!/bin/bash

ORG=$(echo $GITHUB_REPOSITORY | tr "/" "\n")
GITHUB_API_ENDPOINT="api.github.com"
FILENAME=api.jar
PARSER=".[0].assets | map(select(.name == \"$FILENAME\"))[0].id"
RESPONSE=`curl -sL -H "Authorization: token $GITHUB_TOKEN" -H "Accept: application/vnd.github.v3.raw" https://$GITHUB_API_ENDPOINT/repos/$GITHUB_REPOSITORY/releases`
ASSET_ID=`echo "$RESPONSE" | jq "$PARSER"`

echo $ORG
echo $GITHUB_REPOSITORY
echo $GITHUB_TOKEN
echo $ASSET_ID

curl -sL -u "$ORG:$GITHUB_TOKEN" --header "Accept: application/octet-stream" https://api.github.com/repos/$GITHUB_REPOSITORY/releases/assets/$ASSET_ID > api.jar