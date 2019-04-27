function fish_user_key_bindings
	bind ! bind_bang
    bind \cp edit_files_with_vim
    bind \cj down-or-search
    bind \ck up-or-search

	bind -M insert jk "if commandline -P; commandline -f cancel; else; set fish_bind_mode default; commandline -f backward-char force-repaint; end"
	bind -M insert kj "if commandline -P; commandline -f cancel; else; set fish_bind_mode default; commandline -f backward-char force-repaint; end"

end

set FZF_DEFAULT_COMMAND 'ag --hidden --ignore .git --ignore cache --ignore .cache --ignore Cache --depth 10 -g ""'
