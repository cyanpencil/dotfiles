#
# ~/.bash_profile
#

[[ -f ~/.bashrc ]] && . ~/.bashrc
alias ipy="python -c 'import IPython; IPython.terminal.ipapp.launch_new_instance()'"

## auto start X server 
if [[ ! $DISPLAY && $XDG_VTNR -eq 1 ]]; then
  startx
fi

export TERM_PROGRAM=$TERM

