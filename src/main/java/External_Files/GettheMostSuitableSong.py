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
        results.per_page = 1  # sets number of shown results per page

        res = str(results.page(1))
        search = "Release "
        str_loc = res.find(search) + len(search)  # should be +-10

        res_trimmed = res[str_loc:]
        search2 = " "
        str_loc2 = res_trimmed.find(search2)

        release = d.release(res_trimmed[:str_loc2])

        print(release.title)
        # print(release.artists)

    except Exception as err:

        print("-ERROR-")


if __name__ == "__main__":
    main()
