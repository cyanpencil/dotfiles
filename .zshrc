#~/.zshrc

# === env vars ===

export HISTCONTROL=ignoredups:ignorespace
export PATH=$PATH:~/scripts
export TERMINAL=st
export TERM=screen-256color
export BROWSER=firefox
export EDITOR=vim
export XDG_RUNTIME_DIR=/run/user/1000/  # to reach pulseaudio from other netns
export LIBVA_DRIVER_NAME=iHD            # use intel graphics hardware driver for vaapi
export EXA_COLORS="gr=37:gw=37:gx=37:tr=37:tw=37:tx=37:ur=37:uw=37:ux=37:ue=37:da=38;5;240:sn=38;5;240:sb=38;5;237;1:uu=38;5;245:di=38;5;26;1:*.cpp=38;5;34:*.c=38;5;34:*.h=38;5;28:*.py=38;5;34"

# ===

# === Options and plugins ===

HISTFILE=~/.histfile_zsh
HISTSIZE=500000
SAVEHIST=500000
setopt appendhistory
setopt hist_ignore_all_dups
setopt hist_ignore_space
setopt hist_reduce_blanks
setopt inc_append_history
setopt share_history
setopt interactivecomments  # to use '#' on the command line
setopt histignoredups
unsetopt beep

# End of lines configured by zsh-newuser-install
# The following lines were added by compinstall
zstyle :compinstall filename '/home/luca/.zshrc'

# this shit below can be optimized
autoload -Uz compinit
compinit;
#if [[ -n ~/.zcompdump(#qN.mh+24) ]]; then
	#else
		#compinit -C;
#fi;
#

# for editing comands in vim
autoload -z edit-command-line
zle -N edit-command-line

zle -N zle-line-init
zle -N zle-keymap-select
zle -N zle-line-finish


source /usr/share/zsh/plugins/zsh-autosuggestions/zsh-autosuggestions.zsh
source /usr/share/zsh/plugins/zsh-history-substring-search/zsh-history-substring-search.zsh
#source /usr/share/zsh/plugins/fast-syntax-highlighting/fast-syntax-highlighting.plugin.zsh

# ===

# === Aliases ===

alias lsd='exa --group-directories-first -l -snew --time-style=iso'

alias sz='du -had 1 | sort -h'

alias gdb='gdb -q' # get right of the initial copyright banner

alias byobu='TERM=xterm byobu' # fix for st

alias ipa='ip -br -c a'
alias -g G=" | grep"

alias buntoohere='podman run --rm -p 3001:3001 -v ${PWD}:/mnt -it buntoo /bin/bash'

alias buntoohereX='podman run --rm -e DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix -v ~/.Xauthority:/home/luca/.Xauthority -v ${PWD}:/mnt --net=host -it buntoo /bin/bash'

alias ctfhere='podman run --rm -e DISPLAY -v /home/luca/stuff/containers/arch_ctf:/ -v /tmp/.X11-unix:/tmp/.X11-unix -v ~/.Xauthority:/root/.Xauthority --mount type=bind,src=${PWD},dst=/mnt,relabel=shared --net=host --user=root  --stop-timeout 0 --shm-size=4g --name ctfhere -it alpine /usr/bin/zsh'

alias cazzeggio='sudo podman run --rm -e DISPLAY -v /home/luca/stuff/containers/cazzeggio:/ -v /tmp/.X11-unix:/tmp/.X11-unix -v ~/.Xauthority:/root/.Xauthority --mount type=bind,src=${PWD},dst=/mnt,relabel=shared --net=host --user=root --device /dev/dri/card0 --device /dev/snd -v /run/user/1000/pulse:/run/user/1000/pulse -v /var/lib/dbus:/var/lib/dbus --stop-timeout 0 --shm-size=5g --privileged --name ctfhere32 -it alpine /usr/bin/zsh'


# ===

# === Fancy prompt ===

prompt_fire_setup () {
	local fire1='#005fff'
	[[ $EUID == 0 ]] && fire1='red'  # if we are root
	local fire2=$fire1
	local fire3='black'
	local userhost='white'
	local cwd='yellow'

	local netns=$(ip netns identify $$)
	[[ $#netns -ge 2 ]] && netns=" %F{#ffffff}[$netns]%f"

	local -a schars
	autoload -Uz prompt_special_chars
	prompt_special_chars

	local GRAD1="$schars[333]$schars[262]$schars[261]$schars[260]"
	local GRAD2="$schars[260]$schars[261]$schars[262]$schars[333]"
	local COLOR1="%B%F{$fire1}%K{$fire2}"
	local COLOR2="%B%F{$userhost}%K{$fire2}"
	local COLOR3="%b%F{$fire3}%K{$fire2}"
	local COLOR4="%b%F{$fire3}%K{black}"
	local COLOR5="%B%F{$cwd}%K{black}"
	local GRAD0="%b%f%k"

	#PS1=$COLOR1$GRAD1$COLOR2'%m'$COLOR3$GRAD2$COLOR4$GRAD1$COLOR5'%~/'$GRAD0' '
	#PS1=$COLOR1$GRAD1$COLOR2' %m '$COLOR3$GRAD2$COLOR4$COLOR5' %~/'$GRAD0' '
	PS1=$COLOR2' %m '$COLOR3$GRAD2$COLOR5$netns$COLOR5' %~/'$GRAD0' '

	prompt_opts=(cr subst percent)
}

vim_ins_mode="%K{blue}[INS]%k"
vim_cmd_mode="%K{red}[CMD]%k"
vim_mode=$vim_ins_mode

function zle-line-init zle-keymap-select {
	case $KEYMAP in
		vicmd) printf "\x1b[1 q"; vim_mode=$vim_cmd_mode;;
		viins|main) printf "\x1b[3 q"; vim_mode=$vim_ins_mode;;
	esac
	zle reset-prompt
}

function zle-line-finish {
	vim_mode=""
	zle reset-prompt
}

prompt_fire_setup 
setopt PROMPT_SUBST
RPROMPT='${vim_mode}'

export FZF_DEFAULT_COMMAND='ag --hidden --ignore .git --ignore cache --ignore .cache --ignore Cache --depth 10 -g ""'
source /usr/share/fzf/key-bindings.zsh
source /usr/share/fzf/completion.zsh

# ===

# === keybindings ===

bindkey -e
bindkey "^?" backward-delete-char #for delete key in insert mode
export KEYTIMEOUT=10 #for faster ESC key

bindkey kj vi-cmd-mode
bindkey jk vi-cmd-mode

bindkey -M vicmd 'k' history-substring-search-up
bindkey -M vicmd 'j' history-substring-search-down

bindkey -M vicmd "^V" edit-command-line

. /usr/share/fzf/key-bindings.zsh


# ===

# === functions ===

# set window title
function title {
	printf "\033]0;$1 \a"
}


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

function transfer { 
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

# ===

source /home/luca/.profile

# vim:fdm=expr:fdl=0
# vim:fde=getline(v\:lnum)=~'===*$'?(getline(v\:lnum)=~'==\\+[^=]\\+==.*'?'>'\:'<').(strlen(matchstr(getline(v\:lnum),'==*$'))-2)\:'='
