#!/bin/bash

sleep_time=0.5

i3-msg "exec urxvt -e sh -c 'ssh -t osboxes@storewz2nt2k6rryc2sshpublicip1.westeurope.cloudapp.azure.com \"./dstat.sh && bash\" || sleep 5';"
sleep $(bc <<< "2 * $sleep_time")
i3-msg "split h;"
i3-msg "exec urxvt -e sh -c 'cd ~/asl-fall18-project && fish';"
sleep $sleep_time
i3-msg "split v;"
i3-msg "exec urxvt -e sh -c 'cd ~/asl-fall18-project/ssh_logs && fish';"
sleep $sleep_time
i3-msg "focus left;"
i3-msg "split v;"
i3-msg "exec urxvt -e sh -c 'ssh -t osboxes@storewz2nt2k6rryc2sshpublicip5.westeurope.cloudapp.azure.com \"./dstat.sh && bash\" || sleep 5';"
sleep $sleep_time
i3-msg "focus up;"
i3-msg "split v;"
i3-msg "exec urxvt -e sh -c 'ssh -t osboxes@storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com \"./dstat.sh && bash\" || sleep 5';"
sleep $sleep_time
i3-msg "split v;"
i3-msg "exec urxvt -e sh -c 'ssh -t osboxes@storewz2nt2k6rryc2sshpublicip4.westeurope.cloudapp.azure.com \"./dstat.sh && bash\" || sleep 5';"
sleep $sleep_time
i3-msg "focus up;"
i3-msg "focus up;"
i3-msg "split v;"
i3-msg "exec urxvt -e sh -c 'ssh -t osboxes@storewz2nt2k6rryc2sshpublicip2.westeurope.cloudapp.azure.com \"./dstat.sh && bash\" || sleep 5';"
sleep $sleep_time
i3-msg "focus down;"
i3-msg "focus down;"
i3-msg "focus down;"
i3-msg "split v;"
i3-msg "exec urxvt -e sh -c 'ssh -t osboxes@storewz2nt2k6rryc2sshpublicip7.westeurope.cloudapp.azure.com \"./dstat.sh && bash\" || sleep 5';"
sleep $sleep_time
i3-msg "focus up;"
i3-msg "split v;"
i3-msg "exec urxvt -e sh -c 'ssh -t osboxes@storewz2nt2k6rryc2sshpublicip6.westeurope.cloudapp.azure.com \"./dstat.sh && bash\" || sleep 5';"
sleep $sleep_time
i3-msg "focus down;"
i3-msg "split v;"
i3-msg "exec urxvt -e sh -c 'ssh -t osboxes@storewz2nt2k6rryc2sshpublicip8.westeurope.cloudapp.azure.com \"./dstat.sh && bash\" || sleep 5';"
