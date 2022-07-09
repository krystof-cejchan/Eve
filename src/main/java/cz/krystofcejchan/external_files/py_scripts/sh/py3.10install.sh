#!/bin/sh


# shellcheck disable=SC2039
# shellcheck disable=SC2181
if [[ $? == 0 ]]; then
    echo "Succeed"
else
    echo "Failed"
fi

sudo apt update && sudo apt upgrade
sudo apt install build-essential zlib1g-dev libncurses5-dev libgdbm-dev libnss3-dev libssl-dev libreadline-dev libffi-dev libsqlite3-dev wget libbz2-dev
wget https://www.python.org/ftp/python/3.10.0/Python-3.10.0.tgz
tar -xf Python-3.10.*.tgz
# shellcheck disable=SC2164
cd Python-3.10.*/
./configure --enable-optimizations
make -j 4
sudo make altinstall

