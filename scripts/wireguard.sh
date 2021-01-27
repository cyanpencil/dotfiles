#!/bin/bash

sudo ip netns exec phyns sh -c "wg-quick up wg0; ip l s wg0 netns 1"

sudo ip l s wg0 up
sudo ip a add 10.0.0.7/24 dev wg0
