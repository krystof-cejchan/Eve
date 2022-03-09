import socket, os
import threading, wave, pyaudio, pickle, struct

host_name = socket.gethostname()
host_ip = socket.gethostbyname(host_name)
print(host_ip)
port = 9611
#"C:\\Users\\vecer\\Music\\Ak nie si moja.wav"

def audio_stream():
    p = pyaudio.PyAudio()
    CHUNK = 1024
    stream = p.open(format=p.get_format_from_width(2),
                    channels=2,
                    rate=44100,
                    output=True,
                    frames_per_buffer=CHUNK)

    # create socket
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    socket_address = (host_ip, port)
    print('server listening at', socket_address)
    client_socket.connect(socket_address)
    print("CLIENT CONNECTED TO", socket_address)
    data = b""
    payload_size = struct.calcsize("Q")
    while True:
        try:
            print("1")
            while len(data) < payload_size:
                print("2")
                packet = client_socket.recv(1 * 1024)  # 4K
                if not packet: break
                data += packet
            packed_msg_size = data[:payload_size]
            data = data[payload_size:]
            msg_size = struct.unpack("Q", packed_msg_size)[0]
            while len(data) < msg_size:
                print("3")
                data += client_socket.recv(1 * 1024)
            frame_data = data[:msg_size]
            data = data[msg_size:]
            frame = pickle.loads(frame_data)
            stream.write(frame)
            print("4")



        except:

            break

    client_socket.close()
    print('Audio closed')
    os._exit(1)


t1 = threading.Thread(target=audio_stream, args=())
t1.start()
