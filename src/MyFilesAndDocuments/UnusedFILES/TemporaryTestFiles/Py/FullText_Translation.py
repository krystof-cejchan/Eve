from deep_translator import GoogleTranslator
import sys


def main():
    try:
        target = str(sys.argv[1])
        i = 2
        txt = ""
        while i < len(sys.argv):
            txt = txt + sys.argv[i] + " "
            i += 1

        print(GoogleTranslator(source='auto', target=target).translate(txt))

    except Exception as err:

        print(f"Exception: {err}")


if __name__ == "__main__":
    main()
