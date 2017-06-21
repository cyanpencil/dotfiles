# Setup fzf
# ---------
if [[ ! "$PATH" == */home/luca/.fzf/bin* ]]; then
  export PATH="$PATH:/home/luca/.fzf/bin"
fi

# Man path
# --------
if [[ ! "$MANPATH" == */home/luca/.fzf/man* && -d "/home/luca/.fzf/man" ]]; then
  export MANPATH="$MANPATH:/home/luca/.fzf/man"
fi

# Auto-completion
# ---------------
[[ $- == *i* ]] && source "/home/luca/.fzf/shell/completion.bash" 2> /dev/null

# Key bindings
# ------------
source "/home/luca/.fzf/shell/key-bindings.bash"

