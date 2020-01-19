#!/bin/bash

prev_version=$(cat gradle.properties | grep "VERSION_NAME" |  sed -E 's/VERSION_NAME=//')

#git commit --allow-empty -m "Bump version to ${INPUT_STRING}."
#git tag -a -m "Tag version ${INPUT_STRING}." "v$INPUT_STRING"
#git push origin --tags
