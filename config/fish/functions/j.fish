function j
    if count $argv > /dev/null
        builtin cd $argv
    end
    set z (ls -rt --group-directories-first); 
    set LS_COLORS "di=105"
    for e in $z
        printf "\033[38;5;245m";
        date -r $e "+[%d/%m/%y %H:%M] " | tr -d '\n';
        printf "\033[38;5;256m";
        du -sh $e 2>/dev/null | tr -d '\n' | sed -re 's/([0-9]*[,]?[0-9]*)(.).*/\1 \2 /' | awk '{printf "\033[38;5;240m%4s\033[1;38;5;240m%1s\033[0;38;5;256m %s", $1, $2, $3}'; 
        ls -d $e;
    end
    if count $argv > /dev/null
        builtin cd ..
    end
end
