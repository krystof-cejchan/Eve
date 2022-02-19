#this python script uses discogs_client library to verify and find the most suitable song


import discogs_client
import sys
def main():
    try:
        i=1
        searchKeys = ""
        while i < len(sys.argv):
            searchKeys += sys.argv[i] + " "
            i+=1

        d = discogs_client.Client('my_user_agent/1.0', user_token='daRhMyKSYbULWcwtwFOmVPMOfrlpIAsPPFtjdZdI')
        results = d.search(searchKeys,type='release')
        #print(searchKeys)
        results.per_page=1 #sets number of shown results per page
        print(results.page(1)) #prints out the first page
    except Exception as err:

        print("-ERROR-")


if __name__ == "__main__":
    main()