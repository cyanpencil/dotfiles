#! /usr/bin/python3
import os
import sys
import json
import csv
from functools import reduce
import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns
sns.set_style('whitegrid')

COLORS=['#535154', '#cc2529', '#396ab1', '#da7c30', '#3e9651']

sizes = [(0x200, 'bench512'), (0x400, 'bench1K'), (0x1000, 'bench4K'), (0x4000, 'bench16K'), (0x8000, 'bench32K'), (0x10000, 'bench64K')]
"""
for (s, name) in sizes:
    with open(name, 'w+') as f:
        f.write('x'*s)
"""
def size_str_to_bytes(size_str):
    for (s, name) in sizes:
        if size_str == name:
            return s
    raise Exception("{}: Not valid size string".format(size_str))

def plot_throughput(title, xlabel, ylabel, fs_d, dr_d, fig_name):
    plt.clf()
    plt.title(title)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)

    xticks_str = [k[len('bench'):] for k in fs_d.keys()]
    xticks = list(map(size_str_to_bytes, fs_d.keys()))
    plt.xticks(xticks, xticks_str)

    # Set the maximum y of all the different threads settings
    ymax = max(
            max( reduce(lambda a, b: a if max(a) > max(b) else b, (fs_d[t] for t in fs_d) ) ),
            max( reduce(lambda a, b: a if max(a) > max(b) else b, (dr_d[t] for t in dr_d) ) ),
            )
    print('Max: {}'.format(ymax))
    plt.ylim(0, ymax*1.1)

    for (i, (side, d)) in enumerate((('Filereader side', fs_d), ('SD driver side', dr_d))):
        x = xticks
        y = [np.mean(d[t]) for t in d]
        e = [np.std(d[t]) for t in d]
        plt.errorbar(x, y, e, marker='o', color=COLORS[i % len(COLORS)], label='{}'.format(side))

    plt.legend()
    #  plt.show()
    fig = plt.gcf()
    fig.savefig(fig_name, format='pdf', dpi=150)

def parse_data(filename):
    d = {}
    with open(filename, 'r') as f:
        for line in f:
            if ',' not in line:
                continue
            name, time = line.strip().split(',')
            name = name.strip()[name.rfind('/') + 1:]
            if name not in d:
                d[name] = []
            d[name].append(int(time))
    return d

if __name__ == '__main__':
    fs_d = parse_data('fs_side_read.csv')
    dr_d = parse_data('driver_side_read.csv')
    plot_throughput('Latency', 'Size', 'Time (ms)', fs_d, dr_d, os.path.join('graphs', 'fs_throughput.pdf'))

    #  fs_d = parse_data('data/no_last_clust.csv')
    #  dr_d = parse_data('data/last_clust.csv')
    #  plot_throughput('Last cluster caching', 'Size', 'Time (ms)', fs_d, dr_d, os.path.join('graphs', 'fs_last_clust.pdf'))
