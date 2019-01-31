import os
import sys
import json
import copy
import re
from collections import OrderedDict
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns



# test = {"folder":"baseline_1", "title":"Baseline 3 clients, one server", "instances":6}
# test = {"folder":"baseline_2", "title":"Baseline 1 client, two servers", "instances":2}



# basefolder="./archivio/scp_logs/2018-59-14_11:11:44_/scp_logs/"+test['folder']
basefolder="./scp_logs/"+test['folder']
data = {}
keywords = []
workers = []
keywords2 = ['throughput', 'latency']


for client in os.listdir(basefolder):
    for log in os.listdir(os.path.join(basefolder, client)):
        req_type = 'get' if 'get' in log else 'set'
        req_type_json = 'Gets' if 'get' in log else 'Sets'
        myson = json.load(open(os.path.join(os.path.join(basefolder, client, log))))
        numbers = [int(s) for s in re.split("[^0-9]", log) if s.isdigit()]
        no_clients = numbers[0]
        rep = numbers[1]
        if req_type not in data:
            data[req_type] = {}
        if req_type not in keywords:
            keywords += [req_type]
        if no_clients not in data[req_type]:
            data[req_type][no_clients] = {}
        if rep not in data[req_type][no_clients]:
            data[req_type][no_clients][rep] = {"throughput":[], "latency":[]}
        data[req_type][no_clients][rep]['throughput'] += [myson["ALL STATS"][req_type_json]["Ops/sec"]]
        data[req_type][no_clients][rep]['latency'] += [myson["ALL STATS"][req_type_json]["Latency"]]

x = []
y = dict((e1,dict((e2, []) for e2 in keywords2)) for e1 in keywords)
e = dict((e1,dict((e2, []) for e2 in keywords2)) for e1 in keywords)

for no_clients in sorted(data[keywords[0]]):
    x += [no_clients*test["instances"]]
    for req_type in keywords:
        l_reps = [data[req_type][no_clients][rep]['latency'] for rep in data[req_type][no_clients]]
        t_reps = [np.sum(data[req_type][no_clients][rep]['throughput']) for rep in data[req_type][no_clients]]

        y[req_type]['latency'] += [np.mean(l_reps)]
        e[req_type]['latency'] += [np.std(l_reps)]
        y[req_type]['throughput'] += [np.mean(t_reps)]
        e[req_type]['throughput'] += [np.std(t_reps)]


sns.set(style='ticks', palette='Set2')
subplot = 221



# for req_type in keywords:
    # for measure in keywords2:
        # plt.subplot(subplot)
        # subplot += 1
        # gy = y[req_type][measure]
        # ge = e[req_type][measure]
        # plt.xticks(x)
        # plt.ylim(0, max(gy)*1.1)
        # plt.xlabel("Number of virtual clients per thread")
        # plt.ylabel(measure)
        # plt.title("Aggregated "+measure+" of memcached by number of clients for "+req_type.upper()+" operations")
        # g = sns.lineplot(x, gy, marker='o')
        # plt.fill_between(x,np.array(gy)-ge, np.array(gy)+ge, alpha=0.3, antialiased=True)
        # plt.errorbar(x, gy, ge, linestyle='None', alpha=0.15, color="green", linewidth=1)

# sns.despine()
# plt.suptitle(test['title'])
# plt.subplots_adjust(wspace=0.2,hspace=0.4)
# plt.figure(1).set_size_inches(18,10)
# plt.savefig('last_graph.png', bbox_inches="tight", dpi=200)
# plt.show()
# os.system("cp last_graph.png plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S').png")

for req_type in keywords:
    for measure in keywords2:
        subplot += 1
        gy = y[req_type][measure]
        ge = e[req_type][measure]
        plt.xticks(x)
        plt.ylim(0, max(gy)*1.1)
        plt.xlabel("Number of clients")
        plt.ylabel(measure)

        g = sns.lineplot(x, gy, marker='o')
        plt.fill_between(x,np.array(gy)-ge, np.array(gy)+ge, alpha=0.3, antialiased=True)
        plt.errorbar(x, gy, ge, linestyle='None', alpha=0.15, color="green", linewidth=1)

        if measure == "throughput":
            plt.title("Aggregated "+measure+" of memcached by number of clients for "+req_type.upper()+" operations")
            plt.plot(x, (1000/np.array(y[req_type]['latency']))*x, marker="x", linewidth=2, linestyle="dashed", alpha=0.8)
            plt.legend(["throughput", "inter. law"])
        else:
            plt.title("Average "+measure+" of memcached by number of clients for "+req_type.upper()+" operations")


        plt.suptitle(test['title'])
        sns.despine()
        plt.figure(1).set_size_inches(9,5)


        graph_name = 'last_graph_'+req_type+'_'+measure+'.png'
        plt.savefig(graph_name, bbox_inches="tight", dpi=200, format="png")
        os.system("cp "+graph_name+" plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S')_"+graph_name+".png")

        plt.show()
