from youtubesearchpython import VideosSearch
import sys

i = 2
txt = ""
while i < len(sys.argv):
    txt = txt + sys.argv[i] + " "
    i += 1
search = VideosSearch(txt, limit=int(sys.argv[1]))
index = 0
res = ""
for video in search.result()['result']:
    res += (video['title'] + "\n")
    res += (video['link'] + "\n")
    index += 1
search.next()
output = res[:-1]
if output.isalnum():
    print(output)

else:
    ascii_values = [ord(character) for character in output]
    print(*ascii_values)
    # prints the output in ascii values without , or []
