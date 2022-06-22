# from youtubesearchpython import *
# import sys
#
# import asyncio
#
#
# # async def to_be_run() -> asyncio:
# #     print(await Suggestions.get('yyyyyyXXXXXX___', language='en', region='US'))
# #
# # asyncio.run(to_be_run())
#
# async def to_be_run() -> asyncio:
#     i = 1
#     txt = ""
#     while i < len(sys.argv):
#         txt = txt + sys.argv[i] + " "
#         i += 1
#
#     customsearch = CustomSearch(txt, VideoSortOrder.relevance, limit=20)
#     customresult = await customsearch.next()
#     res = ""
#     for i in range(len(customresult['result'])):
#         res += (customresult['result'][i]['title']) + "\n"
#     print(res)
#
#
# asyncio.run(to_be_run())
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

    #  print(output.isalnum())
    #  print('11/04/2022'.isalnum())

else:
    # print(output.encode("utf-8"))
    ascii_values = [ord(character) for character in output]
    print(*ascii_values)
    # prints the output in ascii values without , or []
