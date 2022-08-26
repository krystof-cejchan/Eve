#!/bin/sh


# shellcheck disable=SC2039
# shellcheck disable=SC2181
if [[ $? == 0 ]]; then
    echo "Succeed"
else
    echo "Failed"
fi

sudo pip install youtube-search-python
sudo pip install SpeechRecognition
sudo pip install deep-translator
sudo pip install randfacts

pip list


