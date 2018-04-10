function l
    if count $argv > /dev/null
        builtin cd $argv
    end
    set LS_COLORS "di=105"
    set z (ls -rt --group-directories-first); 
    for e in $z
	set a (date -r $e "+[%d/%m/%y %H:%M] ");
	set b (du -sh $e | cut -f1)
#set c (ls -d --color $e)
	printf "\033[38;5;245m%s\033[38;5;256m%5s\033[0;38;5;256m %s\n" $a $b $e;
    end
end
