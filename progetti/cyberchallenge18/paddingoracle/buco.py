import requests
import binascii

BLACK= "\u001b[30m"
RED= "\u001b[31m"
GREEN= "\u001b[32m"
YELLOW= "\u001b[33m"
BLUE= "\u001b[34m"
MAGENTa= "\u001b[35m"
CYAN= "\u001b[36m"
WHITE= "\u001b[37m"
RESET= "\u001b[0m"
BOLD = '\033[1m'
UNDERLINE = '\033[4m'

loading = ["-", "\\", "|", "/"]

cipher = "307FD8658A395B5F103654DE62181973A0F89D45ED71E4B3E77C1B58F9417B91C2E902E6E682D692188BDF64C068CF69C5B94230B64608E7ABB09A976CD6DC8CFA0A5766EF20CEAF2BCDE0EE55899983F6D61EAF3E6E28A749597DEDA0AB2DF5"
cipher_blocks = [cipher[i:i+32] for i in range(0, len(cipher),  32)]


def padding_error(mycipher):
    r = requests.get("http://glocken.hacking-lab.com/12001/login_case7/login7/controller?action=ticketlogin&ticket=" + mycipher)
    return "padding error" in r.text

def decrypt_block(n):
    block = binascii.unhexlify(cipher_blocks[n-1].encode())
    decoded = [0] * 16
    loading_idx = 0
    for i in reversed(range(0, 16)):
        for c in [(k + 0x2e) % 256 for k in range(256)]:
            newblock = block[:i] + bytes([block[i] ^ c ^ (16 - i)]) + bytes([block[k] ^ decoded[k] ^ (16 - i) for k in range(i + 1, 16)])
            newblock = binascii.hexlify(newblock).decode()
            print("\r ["+loading[loading_idx % 4]+"] Block " + str(n) + " " + RED + newblock[:i*2] + 
                    YELLOW + hex(c)[2:] + GREEN + ''.join([chr(k) for k in decoded[i+1:]]) + RESET + " "*40, end="")
            loading_idx += 1
            mycipher = ''.join(cipher_blocks[:n-1]) + newblock.upper() + cipher_blocks[n]
            if not padding_error(mycipher):
                decoded[i] = c
                break
    print("\r Block " + str(n) + " " + GREEN + ''.join([chr(k) for k in decoded[i+1:]]) + RESET + " "*40, end="")


decrypt_block(2)
