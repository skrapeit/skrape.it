#!/bin/bash

releaseType="$1"
prev_version="$2"

possibleReleaseTypes="major feature bug alpha beta rc"

if [[ ! ${possibleReleaseTypes[*]} =~ ${releaseType} ]]; then
    echo "valid argument: [ $( IFS='|'; echo "${possibleReleaseTypes[*]}" ) ]"
    exit 1
fi

echo "bump version for new ${releaseType}-release"

major=0; minor=0; build=0; pre=""; preversion=0

# break down the version number into it's components
regex="^([0-9]+).([0-9]+).([0-9]+)((-[a-z]+)([0-9]+))?$"
if [[ $prev_version =~ $regex ]]; then
  major="${BASH_REMATCH[1]}"
  minor="${BASH_REMATCH[2]}"
  build="${BASH_REMATCH[3]}"
  pre="${BASH_REMATCH[5]}"
  preversion="${BASH_REMATCH[6]}"
else
  echo "'$prev_version' is not a symantic version"
  exit 1
fi

# increment version number based on given release type
case "$releaseType" in
  "major")
    ((major++)); minor=0; build=0; pre="";;
  "feature")
    ((minor++)); build=0; pre="";;
  "bug")
    ((build++)); pre="";;
  "alpha")
    ((preversion++))
    if [[ "$pre" != "-alpha" ]]; then
      preversion=1
    fi
    pre="-alpha$preversion"
    ;;
  "beta")
    ((preversion++))
    if [[ "$pre" != "-beta" ]]; then
      preversion=1
    fi
    pre="-beta$preversion"
    ;;
  "rc")
    ((preversion++))
    if [[ "$pre" != "-rc" ]]; then
      preversion=1
    fi
    pre="-rc$preversion"
    ;;
esac

next_version="${major}.${minor}.${build}${pre}"

echo "$prev_version -> $next_version"
