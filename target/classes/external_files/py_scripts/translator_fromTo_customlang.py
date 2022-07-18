import sys
from deep_translator import GoogleTranslator


def main():
    try:
        this_source = sys.argv[2]
        this_target = sys.argv[1]
        i = 3
        txt = ""
        while i < len(sys.argv):
            txt = txt + sys.argv[i] + " "
            i += 1

        print(GoogleTranslator(source=this_source, target=this_target).translate(txt))

    except Exception as err:

        print(f"Exception: {err}")


if __name__ == "__main__":
    main()
