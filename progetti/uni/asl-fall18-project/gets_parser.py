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



test = {"folder":"gets_1", "title":"Gets and multigets, sharded", "instances":12}
# test = {"folder":"gets_2", "title":"Gets and multigets, non sharded", "instances":12}



# basefolder="./scp_logs/"+test['folder']
basefolder="./archivio/scp_logs/"+test['folder']+"_real/scp_logs/"+test['folder']

memtier_files = subprocess.run("find . -wholename '"+basefolder+"/*_*/*.log'", shell=True, stdout=subprocess.PIPE).stdout.decode('ascii')
test_time = json.load(open(memtier_files.split()[0]))['configuration']['test_time']
print("\x1b[31;1mTest time:", test_time, "\x1b[0m")

data = {}
keywords = []
workers = []
keywords2 = ['throughput', 'latency']


for client in os.listdir(basefolder):
    for log in os.listdir(os.path.join(basefolder, client)):
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
            data[req_type][no_clients][work][rep] = {"throughput":[], "latency":[]}

        if "_" not in client: #its a middleware
            continue
            # with open(os.path.join(os.path.join(basefolder, client, log)), "r") as mylog:
                # for line in mylog.readlines():
                    # if len(line) < 10: continue
                    # d = dict((e1[0], e1[1]) for e1 in [a.split("=") for a in line.split(",")])
                    # # print(d['resp_time'])
                    # data[req_type][no_clients][work][rep]['throughput'] += [float(d['throughput'])]
                    # data[req_type][no_clients][work][rep]['latency'] += [float(d['resp_time'])]
        else: #its a memtier
            myson = json.load(open(os.path.join(os.path.join(basefolder, client, log))))
            req_type_json = "Totals"
            data[req_type][no_clients][work][rep]['throughput'] += [myson["ALL STATS"][req_type_json]["Ops/sec"]]
            data[req_type][no_clients][work][rep]['latency'] += [myson["ALL STATS"][req_type_json]["Latency"]]


x = []
y = dict((e1,dict((w, dict((e2, []) for e2 in keywords2)) for w in workers)) for e1 in keywords)
e = dict((e1,dict((w, dict((e2, []) for e2 in keywords2)) for w in workers)) for e1 in keywords)


for no_clients in sorted(data[keywords[0]]):
    x += [no_clients]
    for req_type in keywords:
        for work in workers:
            l_reps = [np.mean(data[req_type][no_clients][work][rep]['latency']) for rep in data[req_type][no_clients][work]]
            t_reps = [np.sum(data[req_type][no_clients][work][rep]['throughput']) for rep in data[req_type][no_clients][work]]

            y[req_type][work]['latency'] += [np.mean(l_reps)]
            e[req_type][work]['latency'] += [np.std(l_reps)]
            y[req_type][work]['throughput'] += [np.mean(t_reps)]
            e[req_type][work]['throughput'] += [np.std(t_reps)]

subplot = 221
sns.set(style='ticks', palette='Set2')


for req_type in keywords:
    for measure in keywords2:
        plt.subplot(subplot)
        subplot += 1
        print(y[req_type])
        gy = [a[measure] for a in y[req_type].values()]
        # print(reduce(lambda x, y: x[measure] if max(list(x[measure])) > max(list(y[measure])) else y[measure], y[req_type].values()))
        plt.xticks(x)
        plt.ylim(0, max(sum(gy, []))*1.1)
        plt.xlabel("Number of keys in multiget")
        plt.ylabel(measure)
        plt.title("Throughput measured on the client")
        for work in workers:
            gy = y[req_type][work][measure]
            ge = e[req_type][work][measure]
            g = sns.lineplot(x, gy, marker='o', legend='full')
            if len(workers) > 1: plt.legend([str(c)+" workers" for c in workers])
            plt.fill_between(x,np.array(gy)-ge, np.array(gy)+ge, alpha=0.3, antialiased=True)
            plt.errorbar(x, gy, ge, linestyle='None', alpha=0.15, color="green", linewidth=1)

sns.despine()
plt.suptitle(test['title'])
plt.subplots_adjust(wspace=0.2,hspace=0.4)
plt.figure(1).set_size_inches(18,10)
plt.savefig('last_graph.png', bbox_inches="tight", dpi=200)
plt.show()
os.system("mkdir -p plots/"+test['folder']+"/")
os.system("cp last_graph.png plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S').png")
