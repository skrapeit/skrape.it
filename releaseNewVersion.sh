#!/bin/bash

prev_version=$(cat gradle.properties | grep "VERSION_NAME" |  sed -E 's/VERSION_NAME=//')

type="$1"

major=0
minor=0
build=0
pre=""
preversion=0

# break down the version number into it's components
regex="([0-9]+).([0-9]+).([0-9]+)(-[a-z]+)([0-9]+)"
if [[ $prev_version =~ $regex ]]; then
  major="${BASH_REMATCH[1]}"
  minor="${BASH_REMATCH[2]}"
  build="${BASH_REMATCH[3]}"
  pre="${BASH_REMATCH[4]}"
  preversion="${BASH_REMATCH[5]}"
fi

# check paramater to see which number to increment
if [[ "$type" == "major" ]]; then
  major=$(echo $major + 1 | bc)
  minor=0
  build=0
  pre=""
elif [[ "$type" == "feature" ]]; then
  minor=$(echo $minor + 1 | bc)
  build=0
  pre=""
elif [[ "$type" == "bug" ]]; then
  build=$(echo $build + 1 | bc)
  pre=""
elif [[ "$type" == "alpha" ]]; then
  preversion=$(echo $preversion + 1 | bc)
  if [[ "$pre" != "-alpha" ]]; then
      preversion=1
  fi
  pre="-alpha$preversion"
elif [[ "$type" == "beta" ]]; then
  preversion=$(echo $preversion + 1 | bc)
  if [[ "$pre" != "-beta" ]]; then
      preversion=1
  fi
  pre="-beta$preversion"
else
  echo "valid arguments: [ major | feature | bug | alpha | beta]"
  exit 1
fi

next_version="${major}.${minor}.${build}${pre}"

echo "$next_version"

#git commit --allow-empty -m "Bump version to ${INPUT_STRING}."
#git tag -a -m "Tag version ${INPUT_STRING}." "v$INPUT_STRING"
#git push origin --tags
