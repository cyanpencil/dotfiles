#!/bin/bash

# Start php service
php-fpm

sleep 2

# render php-fpm socket readable
chmod 777 /run/php-fpm/php-fpm.soc

# Starting nginx rtmp service 
nginx 


cd /home/user

sleep 5

IFS=$'\n' 
while true; do
for f in $(find /video/ \( -iname "*.mkv" -o -iname "*.mp4" -o -iname "*.webm" \) | shuf); do 
ffmpeg \
	-vaapi_device /dev/dri/renderD128 \
	-re -i $f \
	-c:v h264_vaapi  -global_quality 25 -bufsize 16k \
	-c:a aac -b:a 160k -ar 44100 \
	-vf "[in]scale=1920x1080, \
	drawtext=fontfile=fonts/PxPlus_IBM_VGA8.ttf: \
	text='$(basename ${f%.*})': fontcolor=white: fontsize=32: box=1: boxcolor=#260c22@0.9: \
	boxborderw=8: x=20: y=h-text_h-20:  \
	shadowcolor=#b349b1@0.9:shadowx=4:shadowy=2, \
	drawtext=fontfile=fonts/PxPlus_IBM_VGA8.ttf: \
	text=\'Category: $(basename $(dirname $f))\': fontcolor=white: fontsize=32: box=1: boxcolor=#260c22@0.9: \
	boxborderw=8: x=20: y=h-(2*text_h)-40: \
	shadowcolor=#b349b1@0.9:shadowx=4:shadowy=2, \
	format=nv12, hwupload
		"  \
	-f flv -threads 0 \
	rtmp://localhost/media_server/demostream
done;
done;

