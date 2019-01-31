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




test = {"folder":"gets_1", "title":"Read-only load, non sharded", "instances":1, "from":"memtier"}
# test = {"folder":"gets_1", "title":"Read-only load, non sharded", "instances":1, "from":"middleware"}
# test = {"folder":"gets_2", "title":"Read-only load, sharded", "instances":1, "from":"memtier"}
# test = {"folder":"gets_2", "title":"Read-only load, sharded", "instances":1, "from":"middleware"}

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
workers = [25, 50, 75, 90, 99]
keywords2 = ['throughput', 'resp_time']
# keywords2 = ['throughput', 'resp_time', 'mainthread', 'queue_size', 'waiting']
# keywords2 = ['throughput', 'resp_time', 'waiting']

ppp = [25, 50, 75, 90, 99]


for client in os.listdir(basefolder):
    for log in os.listdir(os.path.join(basefolder, client)):
        req_type = 'set' if 'set' in log else 'get'
        numbers = [int(s) for s in re.split("[^0-9]", log) if s.isdigit()]
        no_clients = numbers[0]
        rep = numbers[1]
        # work = numbers[2] if len(numbers) > 1 else 0
        if req_type not in data:
            data[req_type] = {}
        if req_type not in keywords:
            keywords += [req_type]
        if no_clients not in data[req_type]:
            data[req_type][no_clients] = {}
        if 25 not in data[req_type][no_clients]:
            for p in workers:
                data[req_type][no_clients][p] = {}
        for p in workers:
            if rep not in data[req_type][no_clients][p]:
                # data[req_type][no_clients][work][rep] = {"throughput":[], "latency":[]}
                data[req_type][no_clients][p][rep] = dict((k, []) for k in keywords2)

        if test['from'] == 'middleware' and "_" not in client: #its a middleware
            no_lines = 0
            with open(os.path.join(os.path.join(basefolder, client, log)), "r") as mylog:
                for line in mylog.readlines():
                    no_lines += 1
            if (no_clients == 32) and (work == 64): print(log, float(no_lines),float(work))
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
        elif test['from'] == 'memtier' and "_" in client: #its a memtier
            myson = json.load(open(os.path.join(os.path.join(basefolder, client, log))))
            req_type_json = 'Gets' if 'get' in log else 'Sets'
            print(log)
            for p in workers:
                data[req_type][no_clients][p][rep]['throughput'] += [myson["ALL STATS"][req_type_json]["Ops/sec"]]
            # data[req_type][no_clients][work][rep]['resp_time'] += [myson["ALL STATS"][req_type_json]["Latency"]]
            for p in workers:
                for cose in myson["ALL STATS"]["GET"]:
                    if cose["percent"] > p:
                        data[req_type][no_clients][p][rep]['resp_time'] += [cose["<=msec"]]
                        break



print(data)

x = []
y = dict((e1,dict((w, dict((e2, []) for e2 in keywords2)) for w in workers)) for e1 in keywords)
e = dict((e1,dict((w, dict((e2, []) for e2 in keywords2)) for w in workers)) for e1 in keywords)


for no_clients in sorted(data[keywords[0]]):
    x += [no_clients*test["instances"]]
    for req_type in keywords:
        for work in workers:
            t_reps = [np.sum(data[req_type][no_clients][work][rep]['throughput']) for rep in data[req_type][no_clients][work]]
            y[req_type][work]['throughput'] += [np.mean(t_reps)]
            e[req_type][work]['throughput'] += [np.std(t_reps)]
            print(data[req_type][no_clients][work])

            for k in keywords2[1:]:
                if k == 'throughput':
                    reps = [np.sum(data[req_type][no_clients][work][rep][k]) for rep in data[req_type][no_clients][work]]
                else:
                    reps = [np.mean(data[req_type][no_clients][work][rep][k]) for rep in data[req_type][no_clients][work]]
                y[req_type][work][k] += [np.mean(reps)]
                e[req_type][work][k] += [np.std(reps)]


subplot = 221
sns.set(style='ticks', palette='Set2')

workers = sorted(workers)

# for req_type in keywords:
    # for measure in keywords2:
        # plt.subplot(subplot)
        # subplot += 1
        # print(y[req_type])
        # gy = [a[measure] for a in y[req_type].values()]
        # plt.xticks(x)
        # plt.ylim(0, max(sum(gy, []))*1.1)
        # plt.xlabel("Number of virtual clients per thread")
        # plt.ylabel(measure)
        # plt.title("Aggregated "+measure+" of memcached by number of clients for "+req_type.upper()+" operations")
        # for work in workers:
            # gy = y[req_type][work][measure]
            # ge = e[req_type][work][measure]
            # g = sns.lineplot(x, gy, marker='o', legend='full')
            # if len(workers) > 1: plt.legend([str(c)+" workers" for c in sorted(workers)])
            # plt.fill_between(x,np.array(gy)-ge, np.array(gy)+ge, alpha=0.3, antialiased=True)
            # plt.errorbar(x, gy, ge, linestyle='None', alpha=0.15, color="green", linewidth=1)
        # # if measure == "throughput":
            # # plt.plot(x, (1000/np.array(y[req_type][work]['latency']))*x, marker="x", linewidth=5, linestyle="dashed")

# sns.despine()
# plt.suptitle(test['title'] + " (measured on "+test['from']+")")
# plt.subplots_adjust(wspace=0.2,hspace=0.4)
# plt.figure(1).set_size_inches(18,10)
# plt.savefig('last_graph.png', bbox_inches="tight", dpi=200)
# plt.show()
# os.system("mkdir -p plots/"+test['folder']+"/")
# os.system("cp last_graph.png plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S').png")

for req_type in keywords:
    for measure in keywords2:
        print(y[req_type])
        gy = [a[measure] for a in y[req_type].values()]
        plt.xticks(x)
        # plt.ylim(0, max(sum(gy, []))*1.1)
        plt.ylim(0, 25)
        if test['folder'][:4] == "gets":
            plt.xlabel("Number of keys")
        else:
            plt.xlabel("Number of virtual clients per thread")
        plt.ylabel(measure)

        for work in workers:
            gy = y[req_type][work][measure]
            ge = e[req_type][work][measure]
            g = sns.lineplot(x, gy, marker='o', legend='full')
            if len(workers) > 1: plt.legend([str(c)+" percentile" for c in sorted(workers)])
            plt.fill_between(x,np.array(gy)-ge, np.array(gy)+ge, alpha=0.3, antialiased=True)
            plt.errorbar(x, gy, ge, linestyle='None', alpha=0.15, color="green", linewidth=1)

        plt.gca().set_prop_cycle(None)
        if measure == "throughput":
            plt.title("Aggregated "+measure+" of memcached by number of keys for "+req_type.upper()+" operations")
            for work in workers:
                plt.plot(x, (1000/(np.array(y[req_type][work]['resp_time']) + 1.0))*x, marker=" ", linewidth=1, linestyle="dashed")
        else:
            plt.title("Average "+measure+" of memcached by number of keys for "+req_type.upper()+" operations")

        plt.suptitle(test['title'])
        sns.despine()
        plt.figure(1).set_size_inches(9,5)

        graph_name = 'last_graph_'+req_type+'_'+measure+'.png'
        plt.savefig(graph_name, bbox_inches="tight", dpi=200, format="png")
        os.system("cp "+graph_name+" plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S')_"+graph_name+".png")

        plt.show()


# for req_type in keywords:
    # for measure in keywords2:
        # subplot += 1
        # gy = y[req_type][measure]
        # ge = e[req_type][measure]
        # plt.xticks(x)
        # plt.ylim(0, max(gy)*1.1)
        # plt.xlabel("Number of clients")
        # plt.ylabel(measure)

        # g = sns.lineplot(x, gy, marker='o')
        # plt.fill_between(x,np.array(gy)-ge, np.array(gy)+ge, alpha=0.3, antialiased=True)
        # plt.errorbar(x, gy, ge, linestyle='None', alpha=0.15, color="green", linewidth=1)

        # if measure == "throughput":
            # plt.title("Aggregated "+measure+" of memcached by number of clients for "+req_type.upper()+" operations")
            # plt.plot(x, (1000/np.array(y[req_type]['latency']))*x, marker="x", linewidth=2, linestyle="dashed", alpha=0.8)
            # plt.legend(["throughput", "inter. law"])
        # else:
            # plt.title("Average "+measure+" of memcached by number of clients for "+req_type.upper()+" operations")


        # plt.suptitle(test['title'])
        # sns.despine()
        # plt.figure(1).set_size_inches(9,5)


        # graph_name = 'last_graph_'+req_type+'_'+measure+'.png'
        # plt.savefig(graph_name, bbox_inches="tight", dpi=200, format="png")
        # os.system("cp "+graph_name+" plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S')_"+graph_name+".png")

 
