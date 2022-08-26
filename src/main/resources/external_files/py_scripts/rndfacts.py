from randfacts import get_fact
import sys


try:
    safety = sys.argv[1]
except:
    safety = -1

match safety:
    case 1:
        print(get_fact(filter_enabled=False))
    case 2:
        print(get_fact(only_unsafe=True))
    case _:
        print(get_fact())