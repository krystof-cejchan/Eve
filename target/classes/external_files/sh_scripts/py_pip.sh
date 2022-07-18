#!/bin/sh


# shellcheck disable=SC2039
# shellcheck disable=SC2181
if [[ $? == 0 ]]; then
    echo "Succeed"
else
    echo "Failed"
fi

sudo apt install python3-pip
