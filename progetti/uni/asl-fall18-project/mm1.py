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


test_no = 1



# test = {"folder":"middleware_1", "title":"Baseline with one middleware", "instances":6, "from":"memtier"}
# test = {"folder":"middleware_1", "title":"Baseline with one middleware", "instances":6, "from":"middleware"}
# test = {"folder":"middleware_2", "title":"Baseline with two middleware", "instances":6, "from":"memtier"}
# test = {"folder":"middleware_2", "title":"Baseline with two middleware", "instances":6, "from":"middleware"}

# test = {"folder":"writes_1", "title":"Write-only load", "instances":6, "from":"memtier"}
test = {"folder":"writes_1", "title":"Write-only load", "instances":6, "from":"middleware"}

# test = {"folder":"gets_1", "title":"Read-only load, non sharded", "instances":1, "from":"memtier"}
# test = {"folder":"gets_1", "title":"Read-only load, non sharded", "instances":1, "from":"middleware"}
# test = {"folder":"gets_2", "title":"Read-only load, sharded", "instances":1, "from":"memtier"}
# test = {"folder":"gets_2", "title":"Read-only load, sharded", "instances":1, "from":"middleware"}

# test = {"folder":"twok", "title":"Read-only load, sharded", "instances":1, "from":"memtier"}

# basefolder="./archivio/scp_logs/middleware_2_set_real/scp_logs/"+test['folder']
# basefolder="./archivio/scp_logs/middleware_2_get_real/scp_logs/"+test['folder']
# basefolder="./archivio/scp_logs/middleware_2/2018-11-15_12:34:26/"+test['folder']

basefolder="./archivio/scp_logs/"+test['folder']+"_real/scp_logs/"+test['folder']
# basefolder="./scp_logs/"+test['folder']

memtier_files = subprocess.run("find . -wholename '"+basefolder+"/*_*/*.log'", shell=True, stdout=subprocess.PIPE).stdout.decode('ascii')
test_time = json.load(open(memtier_files.split()[0]))['configuration']['test_time']
print("\x1b[31;1mTest time:", test_time, "\x1b[0m")

data = {}
keywords = []
workers = []
# keywords2 = ['throughput', 'resp_time']
keywords2 = ['throughput', 'resp_time', 'queue_time', 'queue_size', 'waiting']
# keywords2 = ['throughput', 'resp_time', 'waiting']


for client in os.listdir(basefolder):
    if test['from'] == 'middleware' and "_" not in client: #its a middleware
        for log in os.listdir(os.path.join(basefolder, client)):
            print(log)
            req_type = 'set' if 'set' in log else 'get'
            numbers = [int(s) for s in re.split("[^0-9]", log) if s.isdigit()]
            no_clients = numbers[0]
            rep = numbers[1]
            work = numbers[2] if len(numbers) > 1 else 0
            if req_type not in data:
                data[req_type] = {}
            if req_type not in keywords:
                keywords += [req_type]
            if no_clients not in data[req_type]:
                data[req_type][no_clients] = {}
            if work not in data[req_type][no_clients]:
                data[req_type][no_clients][work] = {}
            if work not in workers:
                workers += [work]
            if rep not in data[req_type][no_clients][work]:
                # data[req_type][no_clients][work][rep] = {"throughput":[], "latency":[]}
                data[req_type][no_clients][work][rep] = dict((k, []) for k in keywords2)

            no_lines = 0
            with open(os.path.join(os.path.join(basefolder, client, log)), "r") as mylog:
                for line in mylog.readlines():
                    no_lines += 1
            # if (no_clients == 32) and (work == 64): print(log, float(no_lines),float(work))
            tput = []
            with open(os.path.join(os.path.join(basefolder, client, log)), "r") as mylog:
                for line in mylog.readlines():
                    if len(line) < 10: continue
                    d = dict((e1[0], e1[1]) for e1 in [a.split("=") for a in line.split(",")])
                    # data[req_type][no_clients][work][rep]['latency'] += [float(d['resp_time'])]
                    for k in keywords2:
                        if k == 'throughput':
                            data[req_type][no_clients][work][rep]['throughput'] += [(float(d['gets']) + float(d['sets']))/test_time]
                        else:
                            data[req_type][no_clients][work][rep][k] += [float(d[k])]
        if test_no == 2:
            break

# print(data)

x = []
y = dict((e1,dict((w, dict((e2, []) for e2 in keywords2)) for w in workers)) for e1 in keywords)
e = dict((e1,dict((w, dict((e2, []) for e2 in keywords2)) for w in workers)) for e1 in keywords)

mu_dict = {}

for no_clients in sorted(data[keywords[0]]):
    x += [no_clients*test["instances"]]
    for req_type in keywords:
        for work in sorted(workers):
            t_reps = [np.sum(data[req_type][no_clients][work][rep]['throughput']) for rep in data[req_type][no_clients][work]]
            mu_dict[work] = max(t_reps)
            y[req_type][work]['throughput'] += [np.mean(t_reps)]
            e[req_type][work]['throughput'] += [np.std(t_reps)]

            for k in keywords2[1:]:
                if k == 'throughput':
                    reps = [np.sum(data[req_type][no_clients][work][rep][k]) for rep in data[req_type][no_clients][work]]
                else:
                    reps = [np.mean(data[req_type][no_clients][work][rep][k]) for rep in data[req_type][no_clients][work]]
                y[req_type][work][k] += [np.mean(reps)]
                e[req_type][work][k] += [np.std(reps)]

for work in sorted(workers):
    mu = mu_dict[work]
    la = y['set'][work]['throughput']
    ro = [a/mu for a in la]
    print(mu, ro)
    mu /= 1000.0



    print("********************* ",work, " *********************")

    print()
    print("%20s" % "Clients", end=' ')
    for n in [6,12,24,48,96,192]:
        print("%10d" % n, end=' ')
    print()
    print()

    print("%20s" % "$\lambda$", end=' ')
    for n in range(len(y['set'][work]['resp_time'])):
        print("%10.2f" % la[n], end=' ')
    print()
    print("%20s" % "µ", end=' ')
    for n in range(len(y['set'][work]['resp_time'])):
        print("%10.0f" % mu*1000, end=' ')
    print()
    print("%20s" % "$\\rho$", end=' ')
    for n in range(len(y['set'][work]['resp_time'])):
        print("%10.3f" % ro[n], end=' ')
    print()
    print()

    print("%20s" % "Response time", end=' ')
    for n in range(len(y['set'][work]['resp_time'])):
        print("%10.2f" % y['set'][work]['resp_time'][n], end=' ')
    print()
    print("%20s" % "$E[r]$", end=' ')
    for n in range(len(y['set'][work]['resp_time'])):
        print("%10.2f" % ((1.0/float(mu)) / (1.0 - float(ro[n]))), end=' ')
    print()
    print()

    print("%20s" % "Queue size", end=' ')
    for n in range(len(y['set'][work]['queue_size'])):
        print("%10.2f" % y['set'][work]['queue_size'][n], end=' ')
    print()
    print("%20s" % "$E[n_q]$", end=' ')
    for n in range(len(y['set'][work]['resp_time'])):
        print("%10.2f" % (ro[n]*ro[n] / (1.0 - float(ro[n]))), end=' ')
    print()
    print()

    print("%20s" % "Queue time", end=' ')
    for n in range(len(y['set'][work]['queue_time'])):
        print("%10.2f" % y['set'][work]['queue_time'][n], end=' ')
    print()
    print("%20s" % "$E[w]$", end=' ')
    for n in range(len(y['set'][work]['resp_time'])):
        print("%10.2f" % (ro[n]*(1.0/float(mu)) / (1.0 - float(ro[n]))), end=' ')
    print()
    print()


print(y['set'][32]['throughput'])


# c=json.dumps(data, indent=2)
# print(c.replace(",","."))

# print(json.dumps(data, indent=2))
# print(data)

# subplot = 221
# sns.set(style='ticks', palette='Set2')

# workers = sorted(workers)

