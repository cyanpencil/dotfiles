#!/usr/bin/python3
import numpy as np
import matplotlib.pyplot as plt

def parse_data(raw: str) -> tuple:
    l = raw.strip().split('\n')
    tx, rx = [], []
    for r in l:
        if 'Tx' in r:
            tx.append(int(r.split(':')[-1]) // 1000)
        elif 'Rx' in r:
            rx.append(int(r.split(':')[-1]) // 1000)
        else:
            print('Parse error: {}'.format(r))
    return tx, rx

mcache_line_1_in = """
Tx: 9876724
Rx: 6355398
Tx: 7842174
Rx: 6714019
Tx: 7500754
Rx: 6053835
Tx: 8117161
Rx: 6861121
Tx: 7537375
Rx: 6372805
Tx: 8046108
Rx: 6810589
Tx: 7244361
Rx: 6497362
Tx: 8364679
Rx: 6766196
Tx: 8475020
Rx: 6526141
"""
mcache_line_16_in = """
Rx: 4507346
Tx: 6432004
Tx: 2040741
Rx: 1082412
Tx: 2624128
Rx: 1076349
Tx: 2448425
Rx: 1029369
Tx: 2645763
Rx: 1328904
Rx: 1001554
Tx: 2658860
Tx: 1766096
Rx: 1121875
Tx: 1967429
Rx: 1052341
Tx: 1915658
Rx: 996762
"""
mcache_line_32_in = """
Rx: 1550929
Tx: 3392499
Tx: 2529294
Rx: 1776561
Tx: 2471632
Rx: 1190766
Tx: 2268311
Rx: 1292640
Rx: 1577650
Tx: 2566738
Tx: 2153394
Rx: 1777307
Tx: 3018009
Rx: 1185671
Tx: 2784910
Rx: 1296593
Tx: 2480640
Rx: 1470846
"""
no_pad_in = """
Rx: 1550929
Tx: 3392499
Tx: 2529294
Rx: 1776561
Tx: 2471632
Rx: 1190766
Tx: 2268311
Rx: 1292640
Rx: 1577650
Tx: 2566738
Tx: 2153394
Rx: 1777307
Tx: 3018009
Rx: 1185671
Tx: 2784910
Rx: 1296593
Tx: 2480640
Rx: 1470846
"""

tx_mcache_line_1, rx_mcache_line_1 = parse_data(mcache_line_1_in)
tx_mcache_line_16, rx_mcache_line_16 = parse_data(mcache_line_16_in)
tx_mcache_line_32, rx_mcache_line_32 = parse_data(mcache_line_32_in) # This is the optimized version
tx_no_pad, rx_no_pad = parse_data(no_pad_in)

y_tx = [np.mean(tx_mcache_line_1), np.mean(tx_mcache_line_16), np.mean(tx_no_pad), np.mean(tx_mcache_line_32)]
e_tx = [np.std(tx_mcache_line_1), np.std(tx_mcache_line_16), np.std(tx_no_pad), np.std(tx_mcache_line_32)]
y_rx = [np.mean(rx_mcache_line_1), np.mean(rx_mcache_line_16), np.mean(rx_no_pad), np.mean(rx_mcache_line_32)]
e_rx = [np.std(rx_mcache_line_1), np.std(rx_mcache_line_16), np.std(rx_no_pad), np.std(rx_mcache_line_32)]

TESTS = ['MCACHE 1', 'MCACHE 16', 'NO PAD', 'MCACHE 32']
x_pos = np.arange(len(TESTS))

# Tx
# Build the plot
fig, ax = plt.subplots()
ax.bar(x_pos, y_tx, yerr=e_tx, align='center', alpha=0.5, ecolor='black')
ax.set_ylabel('Cycles to complete (x1000)')
ax.set_xticks(x_pos)
ax.set_xticklabels(TESTS)
ax.set_title('Cycles required to send 64K of data from one core to another')
ax.yaxis.grid(True)

# Save the figure and show
plt.tight_layout()
plt.savefig('tx_stats.png')
plt.show()

# Rx
# Build the plot
fig, ax = plt.subplots()
ax.bar(x_pos, y_rx, yerr=e_rx, align='center', alpha=0.5, ecolor='black')
ax.set_ylabel('Cycles to complete (x64000)')
ax.set_xticks(x_pos)
ax.set_xticklabels(TESTS)
ax.set_title('Cycles required to receive 64K of data from one core to another')
ax.yaxis.grid(True)

# Save the figure and show
plt.tight_layout()
plt.savefig('rx_stats.png')
plt.show()
