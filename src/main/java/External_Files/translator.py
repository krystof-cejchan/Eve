from deep_translator import GoogleTranslator 
import sys


def main():
    
    try:
        
        print(GoogleTranslator(source='auto', target='cs').translate(str(sys.argv[1])))
        
    except Exception as err:
        
            print(f"Exception: {err}")



if __name__ == "__main__":
    main()
