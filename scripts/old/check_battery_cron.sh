#!/bin/bash


AC=$(acpi --ac-adapter | grep -c "off")

if [[ AC -eq 0 ]]; then
    /home/luca/scripts/charger_udev.sh true
else 
    /home/luca/scripts/charger_udev.sh false
fi
