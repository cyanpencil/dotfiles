#!/usr/bin/env bash

################################
# Shows current brightness
#
# dependencies:
# - xbacklight
#
# @return {Number}: Current brightness
################################

case $BLOCK_BUTTON in
    # right click
    # set to `20
    3) xbacklight -set 20 ;;

    # scroll up
    # raise brightness
    4) xbacklight -inc 10 ;;

    # scroll down
    # lower brightness
    5) xbacklight -dec 10 ;;
esac

echo $(xbacklight -get | sed -r "s/([0-9]*).*/\1/")
echo &#xf185; 
#printf "%.0f" "$(xbacklight -get | sed -r "s/([0-9]*).*/\1/")"
