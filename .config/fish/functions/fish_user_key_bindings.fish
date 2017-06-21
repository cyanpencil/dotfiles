function fish_user_key_bindings
	bind ! bind_bang
    bind \cp edit_files_with_vim
    bind \cj down-or-search
    bind \ck up-or-search
end

set FZF_DEFAULT_COMMAND 'ag --hidden --ignore .git --ignore cache --ignore .cache --ignore Cache --depth 10 -g ""'
fzf_key_bindings
