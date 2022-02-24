# this python script uses discogs_client library to verify and find the most suitable song
from re import search

import discogs_client
import sys


def main():
    try:
        i = 1
        searchKeys = ""

        while i < len(sys.argv):
            searchKeys += sys.argv[i] + " "
            i += 1

        d = discogs_client.Client('my_user_agent/1.0', user_token='daRhMyKSYbULWcwtwFOmVPMOfrlpIAsPPFtjdZdI')
        results = d.search(searchKeys, type='release')
        # print(searchKeys)
        print(results.count)

    except Exception:

        print("-ERROR-")


if __name__ == "__main__":
    main()
