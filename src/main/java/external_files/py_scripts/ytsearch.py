from youtubesearchpython.__future__ import *

import sys

import asyncio


# async def to_be_run() -> asyncio:
#     print(await Suggestions.get('yyyyyyXXXXXX___', language='en', region='US'))
#
# asyncio.run(to_be_run())

async def to_be_run() -> asyncio:
    i = 1
    txt = ""
    while i < len(sys.argv):
        txt = txt + sys.argv[i] + " "
        i += 1

    customSearch = CustomSearch(txt, VideoSortOrder.relevance, limit=3)
    customResult = await customSearch.next()
    res = ""
    for i in range(len(customResult['result'])):
        res += (customResult['result'][i]['title']) + "\n"
    print(res)


asyncio.run(to_be_run())
