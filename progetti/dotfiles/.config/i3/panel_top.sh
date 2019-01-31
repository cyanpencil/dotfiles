#! /bin/sh

PANEL_WM=panel_top
PANEL_FIFO=/tmp/panel_top_fifo

if xdo id -a "$PANEL_WM" > /dev/null ; then
    printf "%s\n" "The panel is already running." >&2
    exit 1
fi

trap 'trap - TERM; kill 0' INT TERM QUIT EXIT

[ -e "$PANEL_FIFO" ] && rm "$PANEL_FIFO" 
mkfifo "$PANEL_FIFO"

source $(dirname $0)/config_bar.sh

getName() {
    local icon=$(pIcon ${FG} ${ARCH})
    local cmd="$(uname -n)"
    local cmdEnd=$(pText ${WHITE} "${cmd}")
    echo "${icon} ${cmdEnd}"
}

getMyIp() {
    local icon=$(pIcon ${CYAN} ${CIP})
    local cmd="$(curl -s https://ifcfg.me/)"
    local cmdEnd=$(pText ${FG} "${cmd}")
    echo " ${icon} ${cmdEnd} ${icon}"
}

getDay() {
    local icon=$(pIcon ${MAGENTA} ${CTIME})
    local cmd=" $(date '+%A %d %b')" 
    local cmdEnd=$(pText ${WHITE} "${cmd}")
    echo "${icon}${cmdEnd}"
}

clock() {
    local icon=$(pIcon ${MAGENTA} ${CCLOCK})
    local cmd=$(date +%H:%M)
    local cmdEnd=$(pText ${FG} ${cmd})
    echo "${icon} ${cmdEnd}"
}

#mail() {
    #local gmaildir=/home/username/.mails/Gmail/\[Gmail\].All\ Mail/new
    #local cmd=$(pAction ${BLUE} ${BG} "i3 'exec termite -e mutt'" ${CMAIL})
    #local count=0
    #if [[ ! -n $(ls "${gmaildir}") ]]; then
        #count=0
    #else
        #count=$(ls -1 "${gmaildir}" | wc -l)
    #fi
    #echo "${cmd} ${count}"
#}

energy() {
    local ac=/sys/class/power_supply/AC/online
    local bat=/sys/class/power_supply/BAT0/present
    local icon="$BAT100"
    local color=$WHITE2
    local total=""
    local batCap1="$(cat /sys/class/power_supply/BAT0/capacity)"
    local batCap2="$(cat /sys/class/power_supply/BAT1/capacity)"
    local batCap=$(( ($batCap1 + $batCap2) / 2 ))
    if [[ $(cat $ac) -lt 1 ]]; then
        [ $batCap -lt 90 ] && icon=$BAT70 
        [ $batCap -lt 70 ] && icon=$BAT50 
        [ $batCap -lt 50 ] && icon=$BAT30 && color=$YELLOW
        [ $batCap -lt 30 ] && icon=$BAT15
        [ $batCap -lt 10 ] && icon=$BAT7 && color=$RED
        local energy_now1=$(cat /sys/class/power_supply/BAT0/energy_now)
        local power_now1=$(cat /sys/class/power_supply/BAT0/power_now)
        local energy_now2=$(cat /sys/class/power_supply/BAT1/energy_now)
        local power_now2=$(cat /sys/class/power_supply/BAT1/power_now)
        local power_total=$(($power_now1 + $power_now2))
        local seconds=$(echo "(($energy_now1 + $energy_now2) * 3600) / ($power_total)" | bc)
        local minutes=$(($seconds / 60 % 60))
        if [[ $minutes -lt 10 ]]; then minutes=0$minutes; fi
        local hours=$(($seconds / 3600))
        local power_format=$(echo $power_total / 1000000 | bc -l)
        power_format=${power_format:0:4}
        total=\("$hours":"$minutes $power_format"W\)
        #echo "$total"
    else
        icon=$CAC
    fi
    echo $(pIcon ${MAGENTA} $icon) $(pText $color "$batCap% ${total}")
}

ws() {
    local cmd=$(i3-msg -t get_outputs | sed 's/.*"current_workspace":"\([^"]*\)".*/\1/')
    local icon=$(pIcon ${YELLOW} " >* " )
    local icon2=$(pIcon ${YELLOW} " *< " )
    local cmdEnd=$(pText ${FG} "${cmd}")
    echo "${icon} ${cmdEnd} ${icon2}"
}

{
    while :; do
        echo "A$(ws)"
        sleep 0.4 || break
    done > "$PANEL_FIFO" &

    while :; do
        echo "W$(getName) $(getMyIp)"
        #echo "R$(energy) $(mail) $(getDay) $(clock)"
        echo "R$(energy) $(getDay) $(clock)"
        sleep 1 || break
    done > "$PANEL_FIFO" &
}

{
    while read -r line ; do 
        cmd=( $line )
        case "${cmd[0]}" in
            W*)
                sysL="${line#?}"
                ;;
            A*)
                sysC="${line#?}"
                ;;
            R*)
                sysR="${line#?}"
                ;;
        esac
        printf "%s\n" "%{l} ${sysL}%{c}${sysC}%{r}${sysR} "
    done
} < "$PANEL_FIFO" | lemonbar \
    -g x${HEIGHT} -u 2 -B ${BG} -F ${FG} -f "${FONT}" -f "${FONT_ICON}" | sh &

wid=$(xdo id -a "$PANEL_WM")
tries_left=20

while [ -z "$wid" -a "$tries_left" -gt 0 ] ; do
    sleep 0.05
    wid=$(xdo id -a "$PANEL_WM")
    tries_left=$((tries_left - 1))
    echo $tries_left
done

[ -n "$wid" ] && xdo above -t "$(xdo id -N I3Top -n root | sort | head -n 1)" "$wid"

wait
