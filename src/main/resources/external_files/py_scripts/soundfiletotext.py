import speech_recognition as sr
import sys


def main():
    sound = str(sys.argv[1])
    #  lan = str(sys.argv[2])

    r = sr.Recognizer()

    with sr.AudioFile(sound) as source:
        r.adjust_for_ambient_noise(source)
        audio = r.listen(source)

    try:
        output = str(r.recognize_google(audio, language=str(sys.argv[2])))
        #  output_encoded = output.encode("utf-8")

        if output.isalnum():
            print(output)

            #  print(output.isalnum())
            #  print('11/04/2022'.isalnum())

        else:
            #print(output.encode("utf-8"))
            ascii_values = [ord(character) for character in output]
            print(*ascii_values)
            # prints the output in ascii values without , or []


    except Exception as e:
        print("-ERROR-",e)


if __name__ == "__main__":
    main()
