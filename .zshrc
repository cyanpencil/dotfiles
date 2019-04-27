# Lines configured by zsh-newuser-install

. /usr/share/fzf/key-bindings.zsh


HISTFILE=~/.histfile_zsh
HISTSIZE=500000
SAVEHIST=500000
setopt appendhistory autocd
unsetopt beep
bindkey -e

# End of lines configured by zsh-newuser-install
# The following lines were added by compinstall
zstyle :compinstall filename '/home/luca/.zshrc'

autoload -Uz compinit
compinit
# End of lines added by compinstall

function zle-line-init zle-keymap-select {
	case $KEYMAP in
		vicmd) printf "\x1b[1 q";;
		viins|main) printf "\x1b[3 q";;

	esac
}


zle -N zle-line-init
zle -N zle-keymap-select

export KEYTIMEOUT=10 #for faster ESC key
bindkey "^?" backward-delete-char #for delete key in insert mode

source /usr/share/zsh/plugins/zsh-autosuggestions/zsh-autosuggestions.zsh
source /usr/share/zsh/plugins/zsh-history-substring-search/zsh-history-substring-search.zsh
source /usr/share/zsh/plugins/fast-syntax-highlighting/fast-syntax-highlighting.plugin.zsh

bindkey kj vi-cmd-mode
bindkey jk vi-cmd-mode

bindkey -M vicmd 'k' history-substring-search-up
bindkey -M vicmd 'j' history-substring-search-down

setopt histignoredups


export EXA_COLORS="gr=37:gw=37:gx=37:tr=37:tw=37:tx=37:ur=37:uw=37:ux=37:ue=37:da=38;5;240:sn=38;5;240:sb=38;5;237;1:uu=38;5;245:di=38;5;26;1:*.cpp=38;5;34:*.c=38;5;34:*.h=38;5;28:*.py=38;5;34"
alias lsd='exa --group-directories-first -l -snew --time-style=iso'

alias sz='du -had 1 | sort -h'



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

export FZF_DEFAULT_COMMAND='ag --hidden --ignore .git --ignore cache --ignore .cache --ignore Cache --depth 10 -g ""'
source /usr/share/fzf/key-bindings.zsh
source /usr/share/fzf/completion.zsh
