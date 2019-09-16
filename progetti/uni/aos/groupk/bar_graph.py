#! /usr/bin/python3
import os
import sys
import json
import csv
from functools import reduce
from statistics import mean
import progressbar
from collections import OrderedDict
import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns

COLORS=['#3e9651', '#da7c30', '#396ab1', '#cc2529', '#535154']
BAR_COLORS = ['#84ba5b', '#e1974c', '#7293cb', '#d35e60', '#808585']
if __name__ == '__main__':
    plot_throughput_with_il('Middleware WO Throughput', 'Clients', 'Ops/sec', midd_set_ops, midd_set_lat, THINK_TIME, os.path.join('graphs', 'middleware_write_only_throughput.pdf'))
