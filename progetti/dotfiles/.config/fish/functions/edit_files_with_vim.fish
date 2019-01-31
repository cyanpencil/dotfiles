function edit_files_with_vim
    fzf --height 40% --reverse --preview 'highlight -O ansi -l --force {}' --preview-window right:70% --border > /tmp/fzf
    vim (cat /tmp/fzf)
end
