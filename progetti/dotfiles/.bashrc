# ~/.bashrc
# If not running interactively, don't do anything
[[ $- != *i* ]] && return

#                               === env vars ===

export PATH=$PATH:~/scripts
export TERMINAL=st
PS1='[\u@\h \W]\$ '

# ===

#                               === aliases ===

alias ls='ls --color=auto'
alias l='ls -lt --color=auto'
alias spotify='LD_PRELOAD=libcurl.so.3 /usr/share/spotify/spotify --force-device-scale-factor=1.0000001 "$@"'
alias scrotclip="scrot -s -e 'xclip -selection clipboard -t "image/png" < $f'"

# ===

#                               === fzf ===

if [[ -f ~/.fzf.bash ]]; then source ~/.fzf.bash; fi
export FZF_DEFAULT_COMMAND='ag --hidden --ignore .git --ignore cache --ignore .cache --ignore Cache --depth 10 -g ""'
bind -x '"\C-p": vim $(fzf);'

# ===

#                               === colorize man pages ===

# from: https://wiki.archlinux.org/index.php/Color_output_in_console#man
export LESS_TERMCAP_mb=$'\e[1;31m'     # begin bold
export LESS_TERMCAP_md=$'\e[1;33m'     # begin blink
export LESS_TERMCAP_so=$'\e[01;44;37m' # begin reverse video
export LESS_TERMCAP_us=$'\e[01;37m'    # begin underline
export LESS_TERMCAP_me=$'\e[0m'        # reset bold/blink
export LESS_TERMCAP_se=$'\e[0m'        # reset reverse video
export LESS_TERMCAP_ue=$'\e[0m'        # reset underline
export GROFF_NO_SGR=1                  # for konsole and gnome-terminal

#===

#                               === functions ===

# E[xtr]act any file
# based on https://github.com/xvoland/Extract
function xtr {
  if [ -z "$1" ]; then
	# display usage if no parameters given
	echo "Usage: xtr <path/file_name>.<zip|rar|bz2|gz|tar|tbz2|tgz|Z|7z|xz|ex|tar.bz2|tar.gz|tar.xz>"
  else
	if [[ -f "$1" ]]; then
	  NAME=${1%.*}
	  #mkdir $NAME && cd $NAME
	  case "$1" in
		*.tar.bz2)   tar xvjf ./"$1"    ;;
		*.tar.gz)    tar xvzf ./"$1"    ;;
		*.tar.xz)    tar xvJf ./"$1"    ;;
		*.lzma)      unlzma ./"$1"      ;;
		*.bz2)       bunzip2 ./"$1"     ;;
		*.rar)       unrar x -ad ./"$1" ;;
		*.gz)        gunzip ./"$1"      ;;
		*.tar)       tar xvf ./"$1"     ;;
		*.tbz2)      tar xvjf ./"$1"    ;;
		*.tgz)       tar xvzf ./"$1"    ;;
		*.zip)       unzip ./"$1"       ;;
		*.Z)         uncompress ./"$1"  ;;
		*.7z)        7z x ./"$1"        ;;
		*.xz)        unxz ./"$1"        ;;
		*.exe)       cabextract ./"$1"  ;;
		*)           >&2 echo "xtr: '$1' - unknown archive method" ;;
	  esac
	else
	  >&2 echo "'$1' - file does not exist"
	  return 1
	fi
  fi
}

transfer() { 
    if [ $# -eq 0 ]; then 
        echo "No arguments specified. Usage:\necho transfer /tmp/test.md\ncat /tmp/test.md | transfer test.md"; 
        return 1; 
    fi 
    tmpfile=$( mktemp -t transferXXX ); 
    if tty -s; then basefile=$(basename "$1" | sed -e 's/[^a-zA-Z0-9._-]/-/g'); 
        curl --progress-bar --upload-file "$1" "https://transfer.sh/$basefile" >> $tmpfile; 
    else curl --progress-bar --upload-file "-" "https://transfer.sh/$1" >> $tmpfile ; 
    fi; 
    cat $tmpfile; 
    rm -f $tmpfile; 
} 





# vim:fdm=expr:fdl=0
# vim:fde=getline(v\:lnum)=~'==*$'?(getline(v\:lnum)=~'==\\+[^=]\\+==.*'?'>'\:'<').(strlen(matchstr(getline(v\:lnum),'==*$'))-2)\:'='

