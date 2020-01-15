#!/bin/bash

#releaseType="$1"

#prev_version=$([ "$2" == "" ] && cat gradle.properties | grep "VERSION_NAME" |  sed -E 's/VERSION_NAME=//' || echo "$2")

#sed -i -e s/.*VERSION_NAME.*/VERSION_NAME="$next_version"/ gradle.properties

#git add gradle.properties
#git commit -m "Bump version from ${prev_version} to ${next_version}."
#git tag "$next_version"
#git push && git push --tags
