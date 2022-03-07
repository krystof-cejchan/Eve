import speech_recognition as sr

UserVoiceRecognizer = sr.Recognizer()

takes = 0

while takes < 3:
    try:

        with sr.Microphone() as UserVoiceInputSource:

            UserVoiceRecognizer.adjust_for_ambient_noise(UserVoiceInputSource, duration=0.5)

            UserVoiceInput = UserVoiceRecognizer.listen(UserVoiceInputSource)

            UserVoiceInput_converted_to_Text = UserVoiceRecognizer.recognize_google(UserVoiceInput)
            UserVoiceInput_converted_to_Text = UserVoiceInput_converted_to_Text.lower()
            print(UserVoiceInput_converted_to_Text)
            exit(0)

    except KeyboardInterrupt:
        # ctrl + c  interruption
        exit(0)

    except sr.UnknownValueError:
        takes = takes + 1
        print("impossible to understand")


