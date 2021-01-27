#!/usr/bin/env python

import json
import lz4.block
import pathlib

#,Set up path and regex for files
path = pathlib.Path.home().joinpath('.mozilla/firefox')
files = path.glob('*default*release*/sessionstore-backups/recovery.jsonlz4')

for f in files:
    # decompress if necessary
    b = f.read_bytes()
    if b[:8] == b'mozLz40\0':
        b = lz4.block.decompress(b[8:])

    # load as json
    j = json.loads(b)
    if 'windows' in j.keys():
        for w in j['windows']:

            # Variables for keeping track of most recent tab
            most_recent_url = ''
            min_time = 0

            # run through tabs
            for t in w['tabs']:
                if "youtube" in t['entries'][0]['url']:
                    print(t['entries'][0]['url'])

                try:
                    # Firefox does not 0-index
                    i = t['index'] - 1
                    access_time = t['lastAccessed']
                    if access_time > min_time:
                        min_time = access_time
                        most_recent_url = t['entries'][i]['url']
                except:
                    pass

            print(most_recent_url)
            exit(0)
exit(1)
