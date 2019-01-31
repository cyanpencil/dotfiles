#!/bin/bash

sudo systemctl stop netctl-auto@wlp3s0.service 
sudo systemctl restart netctl-auto@wlp3s0.service 
