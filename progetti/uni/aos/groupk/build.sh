# configuragion. Note BF_SOURCE and BF_BUILD must be absolute paths!
BF_SOURCE=$(readlink -f `dirname $0`)
BF_BUILD=$BF_SOURCE/build
BF_DOCKER=achreto/barrelfish-ci

# create the build directory
mkdir -p $BF_BUILD

# run the command in the docker image
docker run -u $(id -u) -i -t \
    --mount type=bind,source=$BF_SOURCE,target=/source \
    --mount type=bind,source=$BF_BUILD,target=/source/build \
    $BF_DOCKER /bin/bash -c "(cd /source/build && /bin/sh -c \"make -j$(nproc) qemu_a15ve_4\")"
