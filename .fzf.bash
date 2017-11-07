# Setup fzf
# ---------
if [[ ! "$PATH" == */home/luca/.fzf/bin* ]]; then
  export PATH="$PATH:/home/luca/.fzf/bin"
fi

# Auto-completion
# ---------------
[[ $- == *i* ]] && source "/home/luca/.fzf/shell/completion.bash" 2> /dev/null

# Key bindings
# ------------
source "/home/luca/.fzf/shell/key-bindings.bash"

