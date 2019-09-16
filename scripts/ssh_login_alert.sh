#!/bin/bash
# append "session optional pam_exec.so /<path_to_script.sh" to /etc/pam.d/sshd
# check permissions of this file!
echo $(env) > /home/luca/asdfile
USERID="142916851" 
KEY="845502747:AAHMcrVEVfpweE0nxnZRDWWUGDu3-PkFW9Q" 
TIMEOUT="10"
URL="https://api.telegram.org/bot$KEY/sendMessage"
DATE_EXEC="$(date "+%d %b %Y %H:%M")" #Collect date & time.
TMPFILE='/tmp/ipinfo-$DATE_EXEC.txt' #Create a temporary file to keep data in.
if [ -n "$SSH_CONNECTION" ] && [ -z "$TMUX" ] && [[ ! $PAM_TYPE =~ "close_session" ]] ; then #Trigger
	IP=$(echo $SSH_CONNECTION | awk '{print $1}') #Get Client IP address.
	HOSTNAME=$(hostname -f) #Get hostname
	IPADDR=$(hostname -i | awk '{print $1}') 
	curl https://ipinfo.io/$IP -s -o $TMPFILE #Get info on client IP.
	CITY=$(cat $TMPFILE | sed -n 's/^  "city":[[:space:]]*//p' | tr "\"," "  ") #Client IP info parsing
	REGION=$(cat $TMPFILE | sed -n 's/^  "region":[[:space:]]*//p' | tr "\"," "  ")
	COUNTRY=$(cat $TMPFILE | sed -n 's/^  "country":[[:space:]]*//p' | tr "\"," "  ")
	ORG=$(cat $TMPFILE | sed -n 's/^  "org":[[:space:]]*//p' | tr "\"," "  ")
	TEXT="$DATE_EXEC \n${PAM_USER} logged in to $HOSTNAME ($IPADDR) \nip:$IP \ncountry: $COUNTRY\ncity: $CITY \nregion: $REGION \norg:$ORG"
	TEXT=$(echo $TEXT | sed "s/\\\n/%0a/g")
	curl -s --max-time $TIMEOUT -d "chat_id=$USERID&disable_web_page_preview=1&text=$TEXT" $URL > /dev/null
	rm $TMPFILE #clean up after
fi
