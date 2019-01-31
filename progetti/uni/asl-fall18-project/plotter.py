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

sns.set(style='ticks', palette='Set2')



x = [6,12,24,48,96,192]
plt.xticks([6,12,24,48,96,192])
plt.xlabel("Number of jobs in the system")
plt.ylabel("Throughput")

plt.title("Network of queues throughput")

y = [3342.97000, 6410.25667, 8643.36667, 11576.25667, 14134.89333, 15426.13333]
y2 = [1899.233,3770.766, 7386.345,13405.747,15994.003,16000.000]

y = [3264.3033333333333, 6237.97, 8846.643333333335, 10688.693333333333, 12467.846666666665, 12952.863333333333]
y2 = [2700.046, 5287.421, 9714.286,12336.865,12357.273,12357.273]

plt.ylim(0, 1.04*max(y + y2))

plt.plot(x, y2, marker=" ", linewidth=1, linestyle="dashed")
plt.plot(x, y, marker=" ", linewidth=1)

plt.legend(["predicted", "measured"])

sns.despine()
plt.figure(1).set_size_inches(9,5)

graph_name = 'last_graph.png'
plt.savefig(graph_name, bbox_inches="tight", dpi=200, format="png")
# os.system("cp "+graph_name+" plots/"+test['folder']+"/$(date +'%Y-%m-%d_%H:%M:%S')_"+graph_name+".png")

plt.show()
