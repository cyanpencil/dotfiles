#!/usr/bin/env bash

##########################################################################
# Copyright (c) 2019, ETH Zurich.
# All rights reserved.
#
# This file is distributed under the terms in the attached LICENSE file.
# If you do not find this file, copies can be found by writing to:
# ETH Zurich D-INFK, Universitaetstr. 6, CH-8092 Zurich. Attn: Systems Group.
##########################################################################

# configuragion. Note BF_SOURCE and BF_BUILD must be absolute paths!
BF_SOURCE=$(readlink -f `dirname $0`)
BF_BUILD=$BF_SOURCE/build
BF_DOCKER=achreto/barrelfish-ci
BF_CMD="$@"

echo "bfdocker: $BF_DOCKER"
echo "bfsrc: $BF_SOURCE  build: $BF_BUILD"
echo "bfcmd: $BF_CMD"

# pull the docker image
#sudo docker pull $BF_DOCKER

# create the build directory
mkdir -p $BF_BUILD

# run the command in the docker image
sudo docker run -u $(id -u) -i -t \
    --mount type=bind,source=$BF_SOURCE,target=/source \
    --mount type=bind,source=$BF_BUILD,target=/source/build \
    --privileged -v /dev/bus/usb:/dev/bus/usb \
    $BF_DOCKER /bin/bash -c "(cd /source/build && /bin/bash)"
    #$BF_DOCKER /bin/bash

    #--device=/dev/ttyUSB0 \
