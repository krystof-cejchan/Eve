#!/bin/sh

# shellcheck disable=SC2113
# shellcheck disable=SC2120
function runJar {
  # shellcheck disable=SC2164
  java -jar "$FILE"
}

# shellcheck disable=SC2039
for i in {1..1}; do
  if [ -z "$i" ]; then
    echo "\$var is empty"
    exit 255
  else
    FILE=$1
  fi
done

if [ ! -f "$FILE" ]; then
  echo "File $FILE DOES NOT exist."
  exit 255

else
  runJar
fi
