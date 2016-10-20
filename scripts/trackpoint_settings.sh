#!/bin/bash

echo 250 | sudo tee /sys/devices/platform/i8042/serio1/serio2/speed
echo 150 | sudo tee /sys/devices/platform/i8042/serio1/serio2/sensitivity
