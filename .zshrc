# Lines configured by zsh-newuser-install

. /usr/share/fzf/key-bindings.zsh


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
unsetopt beep
bindkey -e

# End of lines configured by zsh-newuser-install
# The following lines were added by compinstall
zstyle :compinstall filename '/home/luca/.zshrc'

autoload -Uz compinit
compinit
# End of lines added by compinstall

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

zle -N zle-line-init
zle -N zle-keymap-select
zle -N zle-line-finish

export KEYTIMEOUT=10 #for faster ESC key
bindkey "^?" backward-delete-char #for delete key in insert mode

source /usr/share/zsh/plugins/zsh-autosuggestions/zsh-autosuggestions.zsh
source /usr/share/zsh/plugins/zsh-history-substring-search/zsh-history-substring-search.zsh
#source /usr/share/zsh/plugins/fast-syntax-highlighting/fast-syntax-highlighting.plugin.zsh

bindkey kj vi-cmd-mode
bindkey jk vi-cmd-mode

bindkey -M vicmd 'k' history-substring-search-up
bindkey -M vicmd 'j' history-substring-search-down

setopt histignoredups


export EXA_COLORS="gr=37:gw=37:gx=37:tr=37:tw=37:tx=37:ur=37:uw=37:ux=37:ue=37:da=38;5;240:sn=38;5;240:sb=38;5;237;1:uu=38;5;245:di=38;5;26;1:*.cpp=38;5;34:*.c=38;5;34:*.h=38;5;28:*.py=38;5;34"
alias lsd='exa --group-directories-first -l -snew --time-style=iso'

alias sz='du -had 1 | sort -h'

alias ipa='ip -br -c a'
alias -g G=" | grep"

alias buntoohere='podman run --rm -p 3001:3001 -v ${PWD}:/mnt -it ubuntu /bin/bash'

alias buntoohereX='podman run --rm -e DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix -v ~/.Xauthority:/home/luca/.Xauthority -v ${PWD}:/mnt --net=host --user luca -it buntoox /bin/bash'

#alias buntoohereX='sudo docker run --rm -e DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix -v ~/.Xauthority:/home/luca/.Xauthority -v ${PWD}:/mnt --net=host --user luca -it buntoo_tor /bin/bash -itc "cd ~/tor-browser_en-US; bash"'




prompt_fire_setup () {
	local fire1='blue'
	local fire2='blue'
	local fire3'blue'
	local userhost='white'
	local cwd='yellow'

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
	PS1=$COLOR2' %m '$COLOR3$GRAD2$COLOR4$COLOR5' %~/'$GRAD0' '

	prompt_opts=(cr subst percent)
}

prompt_fire_setup 
setopt PROMPT_SUBST
RPROMPT='${vim_mode}'

export FZF_DEFAULT_COMMAND='ag --hidden --ignore .git --ignore cache --ignore .cache --ignore Cache --depth 10 -g ""'
source /usr/share/fzf/key-bindings.zsh
source /usr/share/fzf/completion.zsh



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

source /home/luca/.profile
