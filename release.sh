#!/bin/bash

possibleReleaseTypes="major feature bug alpha beta rc"


if [[ ! ${possibleReleaseTypes[*]} =~ $1 ]]; then
    echo "valid argument: [ ${possibleReleaseTypes[*]} ]"
    exit 1
else
  git tag -d "$1"
  git push --delete origin "$1"

  git tag "$1"
  git push --tags
fi
