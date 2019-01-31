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




# test = {"folder":"gets_1", "title":"Read-only load, non sharded", "instances":1, "from":"memtier"}
test = {"folder":"gets_2", "title":"Read-only load, sharded", "instances":1, "from":"memtier"}


# basefolder="./archivio/scp_logs/"+test['folder']+"_real/scp_logs/"+test['folder']
basefolder="./scp_logs/"+test['folder']

x = []
w = []

memtier_files = subprocess.run("find . -wholename '"+basefolder+"/*_*/*keys_6*.log'", shell=True, stdout=subprocess.PIPE).stdout.decode('ascii')
myson = json.load(open(memtier_files.split()[0]))
total = float(myson['ALL STATS']['Gets']['Ops/sec']) * 60.0

previous = 0
for cose in myson['ALL STATS']['GET']:
    if (float(cose["<=msec"]) < 14.0):
        x += [float(cose["<=msec"])]
        w += [6*((float(cose["percent"]) - previous)/100.0) * total]
        previous = float(cose["percent"])


for i in range(20):
    if (i/10) not in x:
        x = [i/10] + x
        w = [0] + w


print("Total", total)
print(x)
print(w)



sns.set(style='ticks', palette='Set2')

plt.xlabel("Response time")
plt.ylabel("Frequency")

# for work in workers:
    # gy = y[req_type][work][measure]
    # ge = e[req_type][work][measure]
    # g = sns.lineplot(x, gy, marker='o', legend='full')
    # if len(workers) > 1: plt.legend([str(c)+" workers" for c in sorted(workers)])
    # plt.fill_between(x,np.array(gy)-ge, np.array(gy)+ge, alpha=0.3, antialiased=True)
    # plt.errorbar(x, gy, ge, linestyle='None', alpha=0.15, color="green", linewidth=1)
# for i in range(80, 100, 1):
    # print(i)
# plt.hist(x, weights=w)
# plt.hist(x, weights=w, bins=86)

plt.hist(x, weights=w, bins=131)
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
plt.suptitle("Response time distribution, " + ("sharded" if test['folder'][-1] == "2" else "non_sharded"))
sns.despine()
plt.figure(1).set_size_inches(9,5)

graph_name = 'last_histogram.png'
plt.savefig(graph_name, bbox_inches="tight", dpi=200, format="png")
os.system("cp "+graph_name+" plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S')_"+graph_name+".png")

plt.show()
