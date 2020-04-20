!/bin/sh

# Setting this, so the repo does not need to be given on the commandline:
export BORG_REPO=ssh://luca@jupiter//backup/borg

# Setting this, so you won't be asked for your repository passphrase:
# or this to ask an external program to supply the passphrase:
export BORG_PASSCOMMAND='bash -c "su luca pass show borg_backup_key | head -c 40"'

# some helpers and error handling:
info() { printf "\n%s %s\n\n" "$( date )" "$*" >&2; }
trap 'echo $( date ) Backup interrupted >&2; exit 2' INT TERM

info "Finding files too big..."
find / -size +100M 2>/dev/null | tr '[(\-\)\] ' '*' >/tmp/files_too_large

info "Starting backup"

# Backup the most important directories into an archive named after
# the machine this script is currently running on:

borg create                         \
    --verbose                       \
    --filter AME                    \
    --list                          \
    --stats                         \
    --show-rc                       \
    --compression lz4               \
    --exclude-caches                \
    --exclude '/home/*/.cache/*'    \
    --exclude '/home/luca/.local/share/containers/*' \
    --exclude '/var/cache/*'        \
    --exclude '/var/tmp/*'          \
    --exclude-from '/tmp/files_too_large' \
                                    \
    ::'{hostname}-{now}'            \
    /etc                            \
    /home                           \
    /root                           \
    /var                            \

backup_exit=$?

info "Pruning repository"

# Use the `prune` subcommand to maintain 7 daily, 4 weekly and 6 monthly
# archives of THIS machine. The '{hostname}-' prefix is very important to
# limit prune's operation to this machine's archives and not apply to
# other machines' archives also:

borg prune                          \
    --list                          \
    --prefix '{hostname}-'          \
    --show-rc                       \
	--keep-hourly   3               \
    --keep-daily    7               \
    --keep-weekly   4               \
    --keep-monthly  6               \

prune_exit=$?

# use highest exit code as global exit code
global_exit=$(( backup_exit > prune_exit ? backup_exit : prune_exit ))

if [ ${global_exit} -eq 0 ]; then
    info "Backup and Prune finished successfully"
	echo ok > /backup/success
elif [ ${global_exit} -eq 1 ]; then
    info "Backup and/or Prune finished with warnings"
else
    info "Backup and/or Prune finished with errors"
fi

exit ${global_exit}
