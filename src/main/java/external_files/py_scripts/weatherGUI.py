import requests, sys


def getweather():
    i = 1
    searchKeys = ""
    while i < len(sys.argv):
        searchKeys += sys.argv[i] + " "
        i += 1

    url = 'https://wttr.in/{}'.format(searchKeys[:-1])+'?format=j1'
    try:
        data = requests.get(url)
        T = data.text
    except:
        T = "Error Occurred"
    print(T)


if __name__ == "__main__":
    getweather()
