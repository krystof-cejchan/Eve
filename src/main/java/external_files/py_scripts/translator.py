import sys
from deep_translator import GoogleTranslator


def main():
    try:
        i = 1
        txt = ""
        while i < len(sys.argv):
            txt = txt + sys.argv[i] + " "
            i += 1
            # tak python má hodně zajímavou syntax
        print(GoogleTranslator(source='auto', target='en').translate(txt))

    except Exception as err:

        print(f"Exception: {err}")


if __name__ == "__main__":
    main()
