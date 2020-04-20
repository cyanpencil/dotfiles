#! /bin/bash

set -e

[[ -f ~/.tmate.conf ]] || echo 'set -g tmate-server-host "lon1.tmate.io"' > ~/.tmate.conf


# making sure you have tmate 2.4
BIN=$(which tmate)
[[ -d ~/.tmate ]] && BIN=~/.tmate/tmate-2.4.0-static-linux-amd64/tmate
[[ $($BIN -V) =~ "2.4" ]] || {
	mkdir -p ~/.tmate
	wget https://github.com/tmate-io/tmate/releases/download/2.4.0/tmate-2.4.0-static-linux-amd64.tar.xz -O ~/.tmate/tmate.tar.xz 
	cd ~/.tmate/
	tar xvf tmate.tar.xz
	BIN=~/.tmate/tmate-2.4.0-static-linux-amd64/tmate
}

APIKEY="tmk-5qfezrQpECHv2gf5UGMpLbGaMZ"
PASS=$(head /dev/urandom | tr -dc A-Za-z0-9 | head -c 13)

TEXT="ssh cyan/$PASS@lon1.tmate.io"
KEY="945702280:AAGfFOpiwPmBfAAi2SLN8hnLWVFuF1jpLr8"
USERID="142916851"


curl "https://api.telegram.org/bot$KEY/sendMessage" -d "chat_id=$USERID&disable_web_page_preview=1&text=$TEXT" -s >/dev/null

echo -e "\x1b[34;1mCalling for help :P"
sleep 2

$BIN -k $APIKEY -n $PASS

