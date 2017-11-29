#
# ~/.bashrc
#

# If not running interactively, don't do anything
[[ $- != *i* ]] && return

export PATH=$PATH:~/scripts
export TERMINAL=termite

alias ls='ls --color=auto'
PS1='[\u@\h \W]\$ '

alias l='ls -lt --color=auto'
alias write='~/Downloads/Write/Write'
alias spotify='LD_PRELOAD=libcurl.so.3 /usr/share/spotify/spotify --force-device-scale-factor=1.0000001 "$@"'
alias genymotion='sudo genymotion'
alias yaourt='yaourt --noconfirm'


# -----------------------------------------------------------------------------
# E[xtr]act any file
# based on https://github.com/xvoland/Extract
# -----------------------------------------------------------------------------
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

[ -f ~/.fzf.bash ] && source ~/.fzf.bash
#export FZF_DEFAULT_COMMAND='rg --files --no-ignore --hidden --follow --glob "!.git/*"'
export FZF_DEFAULT_COMMAND='ag --hidden --ignore .git --ignore cache --ignore .cache --ignore Cache --depth 10 -g ""'
bind -x '"\C-p": vim $(fzf);'


#setxkbmap us
#setxkbmap -option "ctrl:nocaps"
#setxkbmap -option "compose:caps"
alias ipy="python -c 'import IPython; IPython.terminal.ipapp.launch_new_instance()'"
