#!/bin/bash
mkdir -p /tmp/ts
echo -e "[\x1b[33;1;5m+\x1b[0m] Mounting /tmp/ts to avoid disk usage "
podman run --rm -d -p 10.0.0.1:1935:1935 -p 8080:8080 -v /tmp/ts:/home/user/tmp -v $(pwd)/video:/video --device /dev/dri/renderD128 --name nginx-rtmp  demostream 
