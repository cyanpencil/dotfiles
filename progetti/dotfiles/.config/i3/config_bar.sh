#! /bin/sh

HEIGHT=17
#FONT="-*-dina-medium-r-*-*-8-*-*-*-*-*-*-*"
FONT="-*-tamzen-medium-*-*-*-12-*-*-*-*-*-*-*"
FONT_ICON="-wuncon-siji-medium-r-normal--10-100-75-75-c-80-iso10646-1"

# icons
GENTOO="\ue1ec"
ARCH="\ue00e"
CSTART="\ue00d"
CSOUND="\ue04d"
CVOLUME="\ue204"
CMAIL="\ue070"
CRAM="\ue19a"
CCPU="\ue021"
CNET="\ue04b"
CWIFI="\ue222"
WIFI_STR="\ue02d"
CIP=" ++ "
# Time
CTIME="\ue018"
CPACK="\ue1b7"
CCLOCK="\ue016"
# panel center
CNONE="\ue190"
CFULL="\ue0e6"
# icons panel music
CPREV="\ue055"
CNEXT="\ue05b"
CPLAY="\ue058"
CPAUS="\ue059"
# icon volume
VPLUS="\ue06c"
VMINUS="\ue06d"
# battery or AC
BAT100="\ue254"
BAT70="\ue252"
BAT50="\ue250"
BAT30="\ue24f"
BAT15="\ue24e"
BAT7="\ue24c"
CAC="\ue215"

# $(pAction ${fg} ${bg} ${${command} ${whatprint})
pAction() {
    echo "%{F$1}%{B$2}%{A:$3:}$(printf '%b' $4)%{A}%{B-}%{F-}"
}

# $(pActionUnderline ${fg} ${bg} ${underline} ${${command} ${whatprint})
pActionUnderline() {
    echo "%{F$1}%{B$2}%{U$3}%{+u}%{A:$4:}$(printf '%b' $5)%{A}%{-u}%{B-}%{F-}"
}

# $(pIcon ${color} ${icon})
pIcon() {
    echo "%{F$1}$(printf '%b' $2)%{F-}"
}

# $(pIconUnderline ${color} ${color_underline} ${icon})
pIconUnderline() {
    echo "%{F$1}%{U$2}%{+u}$(printf '%b' $3)%{-u}%{F-}"
}

# $(pText ${color} ${text})
pText() {
    echo "%{F$1}$2%{F-}"
}

# $(pTextUnderline ${color} ${color_underline} ${text})
pTextUnderline() {
    echo "%{F$1}%{U$2}%{+u}$3%{-u}%{F-}"
}

getXresColor() {
   echo "$( xrdb -query | grep $1: | grep -oE '#[a-zA-Z0-9]{6}' | cut -c 1- )"
}

# colors
BG="$(getXresColor background)"
FG="$(getXresColor foreground)"
BLACK="$(getXresColor color0)"
BLACK2="$(getXresColor color8)"
RED="$(getXresColor color1)"
RED2="$(getXresColor color9)"
GREEN="$(getXresColor color2)"
GREEN2="$(getXresColor color10)"
YELLOW="$(getXresColor color3)"
YELLOW2="$(getXresColor color11)"
BLUE="$(getXresColor color4)"
BLUE2="$(getXresColor color12)"
MAGENTA="$(getXresColor color5)"
MAGENTA2="$(getXresColor color13)"
CYAN="$(getXresColor color6)"
CYAN2="$(getXresColor color14)" 
WHITE="$(getXresColor color7)"
WHITE2="$(getXresColor color15)"
