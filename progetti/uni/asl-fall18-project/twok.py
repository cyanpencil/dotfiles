import os
import sys
import json
import copy
import re
import subprocess
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from functools import reduce



# test = {"folder":"middleware_1", "title":"Baseline with one middleware", "instances":6, "from":"memtier"}
# test = {"folder":"middleware_1", "title":"Baseline with one middleware", "instances":6, "from":"middleware"}
# test = {"folder":"middleware_2", "title":"Baseline with two middleware", "instances":6, "from":"memtier"}
# test = {"folder":"middleware_2", "title":"Baseline with two middleware", "instances":6, "from":"middleware"}

# test = {"folder":"writes_1", "title":"Write-only load", "instances":6, "from":"memtier"}
# test = {"folder":"writes_1", "title":"Write-only load", "instances":6, "from":"middleware"}

# test = {"folder":"gets_1", "title":"Read-only load, non sharded", "instances":1, "from":"memtier"}
# test = {"folder":"gets_1", "title":"Read-only load, non sharded", "instances":1, "from":"middleware"}
# test = {"folder":"gets_2", "title":"Read-only load, sharded", "instances":1, "from":"memtier"}
# test = {"folder":"gets_2", "title":"Read-only load, sharded", "instances":1, "from":"middleware"}

test = {"folder":"twok", "title":"Read-only load, sharded", "instances":1, "from":"memtier"}

# basefolder="./archivio/scp_logs/middleware_2_set_real/scp_logs/"+test['folder']
# basefolder="./archivio/scp_logs/middleware_2_get_real/scp_logs/"+test['folder']
# basefolder="./archivio/scp_logs/middleware_2/2018-11-15_12:34:26/"+test['folder']

# basefolder="./archivio/scp_logs/"+test['folder']+"_real/scp_logs/"+test['folder']
basefolder="./scp_logs/"+test['folder']

memtier_files = subprocess.run("find . -wholename '"+basefolder+"/*_*/*.log'", shell=True, stdout=subprocess.PIPE).stdout.decode('ascii')
test_time = json.load(open(memtier_files.split()[0]))['configuration']['test_time']
print("\x1b[31;1mTest time:", test_time, "\x1b[0m")


data = {}
keywords = []
workers = []
keywords2 = ['throughput', 'resp_time']
# keywords2 = ['throughput', 'resp_time', 'mainthread', 'queue_size', 'waiting']
# keywords2 = ['throughput', 'resp_time', 'waiting']


for client in os.listdir(basefolder):
    for log in os.listdir(os.path.join(basefolder, client)):
        # print(log)
        req_type = 'set' if 'set' in log else 'get'
        numbers = [int(s) for s in re.split("[^0-9]", log) if s.isdigit()]
        no_clients = numbers[0]
        rep = numbers[1]
        work = numbers[2] if len(numbers) > 1 else 0
        servers = numbers[3]
        # print(servers)
        if req_type not in data:
            data[req_type] = {}
        if req_type not in keywords:
            keywords += [req_type]
        if no_clients not in data[req_type]:
            data[req_type][no_clients] = {}
        if servers not in data[req_type][no_clients]:
            data[req_type][no_clients][servers] = {}
        if work not in data[req_type][no_clients][servers]:
            data[req_type][no_clients][servers][work] = {}
        if work not in workers:
            workers += [work]
        if rep not in data[req_type][no_clients][servers][work]:
            data[req_type][no_clients][servers][work][rep] = dict((k, []) for k in keywords2)

        elif test['from'] == 'memtier' and "_" in client: #its a memtier
            myson = json.load(open(os.path.join(os.path.join(basefolder, client, log))))
            req_type_json = 'Gets' if 'get' in log else 'Sets'
            data[req_type][no_clients][servers][work][rep]['throughput'] += [myson["ALL STATS"][req_type_json]["Ops/sec"]]
            data[req_type][no_clients][servers][work][rep]['resp_time'] += [myson["ALL STATS"][req_type_json]["Latency"]]

# print(data)

sss = [1,3]

y = dict((e1,dict((n1, dict((s1, dict((w, dict((e2, []) for e2 in keywords2)) for w in workers)) for s1 in sss)) for n1 in [0, 1])) for e1 in keywords)

for no_clients in sorted(data[keywords[0]]):
    for req_type in keywords:
        for servers in sss:
            for work in sorted(workers):
                t_reps = [np.sum(data[req_type][no_clients][servers][work][rep]['throughput']) for rep in data[req_type][no_clients][servers][work]]
                y[req_type][no_clients][servers][work]['throughput'] = t_reps

                for k in keywords2[1:]:
                    reps = [np.mean(data[req_type][no_clients][servers][work][rep][k]) for rep in data[req_type][no_clients][servers][work]]
                    y[req_type][no_clients][servers][work][k] += reps


# servers 1, 3
# middlewares 1, 2
# workers 8, 32 

for s in sss:
    for m in [0, 1]:
        for w in [8, 32]:
            print (s, m, w)
            for r in y['set'][m][s][w]['resp_time']:
                print (str(r).replace('.',','), end = ' ')
            print()
            print()


# c=json.dumps(y, indent=2)
# print(c.replace(",","."))
