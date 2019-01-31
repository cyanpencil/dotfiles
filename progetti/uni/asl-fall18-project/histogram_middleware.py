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




test = {"folder":"gets_1", "title":"Response time distribution, sharded", "instances":1, "from":"memtier"}


myfile="non_sharded.log"
# myfile="sharded.log"


# basefolder="./archivio/scp_logs/"+test['folder']+"_real/scp_logs/"+test['folder']
basefolder="./resp_time_distributions/"

memtier_files = subprocess.run("find . -wholename '"+basefolder+"/*_*/*.log'", shell=True, stdout=subprocess.PIPE).stdout.decode('ascii')
# test_time = json.load(open(memtier_files.split()[0]))['configuration']['test_time']
# print("\x1b[31;1mTest time:", test_time, "\x1b[0m")

data = {}
keywords = []
workers = [25, 50, 75, 90, 99]
keywords2 = ['throughput', 'resp_time']

x = []
w = []


with open("./resp_time_distributions/"+myfile, "r") as f:
    for line in f.readlines()[3:]:
        numbers = line.split()
        if (float(numbers[1]) < 14.0):
            x += [float(numbers[1])]
            w += [2*float(numbers[2])]



sns.set(style='ticks', palette='Set2')

workers = sorted(workers)

plt.xlabel("Response time")
plt.ylabel("Frequency")

# for work in workers:
    # gy = y[req_type][work][measure]
    # ge = e[req_type][work][measure]
    # g = sns.lineplot(x, gy, marker='o', legend='full')
    # if len(workers) > 1: plt.legend([str(c)+" workers" for c in sorted(workers)])
    # plt.fill_between(x,np.array(gy)-ge, np.array(gy)+ge, alpha=0.3, antialiased=True)
    # plt.errorbar(x, gy, ge, linestyle='None', alpha=0.15, color="green", linewidth=1)
# for i in range(90, 100):
# print(i)
plt.hist(x, weights=w, bins=len(x), width=0.09)
plt.xticks(range(15))
# plt.hist(x, weights=w)

# plt.gca().set_prop_cycle(None)
# if measure == "throughput":
    # plt.title("Aggregated "+measure+" of memcached by number of keys for "+req_type.upper()+" operations")
    # for work in workers:
        # plt.plot(x, (1000/(np.array(y[req_type][work]['resp_time']) + 1.0))*x, marker=" ", linewidth=1, linestyle="dashed")
# else:
    # plt.title("Average "+measure+" of memcached by number of keys for "+req_type.upper()+" operations")

plt.ylim(0, 7000)
plt.xlim(0, 14)
plt.suptitle("Response time distribution, "+myfile[:-4])
sns.despine()
plt.figure(1).set_size_inches(9,5)

graph_name = 'last_histogram.png'
plt.savefig(graph_name, bbox_inches="tight", dpi=200, format="png")
os.system("cp "+graph_name+" plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S')_"+graph_name+".png")

plt.show()


