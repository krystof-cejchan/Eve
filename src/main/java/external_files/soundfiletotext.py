import speech_recognition as sr
import sys


def main():
    sound = str(sys.argv[1])
  #  lan = str(sys.argv[2])
    
    r = sr.Recognizer()

    with sr.AudioFile(sound) as source:
        r.adjust_for_ambient_noise(source)

        print("Converting Audio To Text ... ")

        audio = r.listen(source)

    try:
        print("Converted Audio Is : \n" + r.recognize_google(audio, language=str(sys.argv[2])))

    except Exception as e:
        print("-ERROR-")


if __name__ == "__main__":
    main()