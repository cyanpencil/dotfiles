function k
    ls -gohrt $argv --color --group-directories-first | awk '{printf "%-5s %s\n", $3, $7}' | tail --lines=+2
end
