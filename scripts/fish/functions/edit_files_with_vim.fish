function edit_files_with_vim
    fzf > /tmp/fzf
    vim (cat /tmp/fzf)
end
