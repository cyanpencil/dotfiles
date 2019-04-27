function scrotclip
	command scrot -s -e 'xclip -selection clipboard -t "image/png" < $f'
end

