import pandas as pd
import math, sys
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt
import matplotlib.cbook as cbook

pf = pd.read_csv("csv.csv")
str_input = "sqft_living"
str_output = "price"
pf_input = pf[str_input].tolist()
pf_output = pf[str_output].tolist()
max_input = max(pf_input)
intercept, slope = 0, 0
result_rss = 0

def get_mean_std_deviation(input_feature):
    mean, std_deviation = 0, 0
    for i in input_feature:
        mean += i
    mean /= len(input_feature)
    for i in input_feature:
        std_deviation += (i - mean) ** 2
    std_deviation /= len(input_feature)
    std_deviation = math.sqrt(std_deviation)
    return (mean, std_deviation)

def standardize(input_feature):
    mean, std_deviation = get_mean_std_deviation(input_feature)
    res = [(x_i - mean) / std_deviation for x_i in input_feature]
    return res

def draw_graph():
    ax = plt.subplot(111)
    ax.plot(pf_input, pf_output, "g.", markersize=3)
    ax.plot([0, max_input], [intercept, (max_input*slope + intercept)], "k--", lw=2)
    ax.set_xlabel(str_input)
    ax.set_ylabel(str_output)
    ax.set_title("Machine learning plot")
    ax.title.set_weight("bold")
    ax.grid("on")
    ax.xaxis.set_tick_params(size=0)
    ax.yaxis.set_tick_params(size=0)
    ax.xaxis.get_label().set_style("italic")
    ax.yaxis.get_label().set_style("italic")
    ax.xaxis.get_label().set_size(10)
    ax.yaxis.get_label().set_size(10)
    ax.spines['right'].set_color((.8,.8,.8))
    ax.spines['top'].set_color((.8,.8,.8))
    plt.show()


def simple_linear_regression(input_feature, output):
    global slope, intercept
    sum_x, sum_y, sum_xy, sum_xx = 0,0,0,0
    for i in range(len(output)):
        sum_x += input_feature[i]
        sum_y += output[i]
        sum_xy += input_feature[i] * output[i]
        sum_xx += input_feature[i] ** 2
    slope = (sum_xy - (sum_x * sum_y) / len(output)) / (sum_xx - (sum_x**2) / len(output))
    intercept = (sum_y - slope * sum_x) / len(output)

def get_RSS(input_feature, output):
    res = 0
    for idx, x in enumerate(input_feature):
        res += (output[idx] - (intercept + slope*x))**2
    return res

def gradient_descent(input_feature, output):
    global intercept, slope
    intercept, slope = 0, 0
    step_size = 0.05
    prev_err, prev_err2, prev_rss = [sys.maxsize] * 3
    for t in range(1500):
        err, err2, rss = 0, 0, 0
        for idx, x in enumerate(input_feature):
            a = (output[idx] - (intercept + slope*x))
            err  += a
            err2 += a * x
        # k = step_size/(t + 1)
        k = step_size
        # intercept += k * err
        # slope += k * err2
        if (err ** 2 + err2 ** 2) < 0.1:
            break
        # if abs(step_size * err) < abs(intercept / 100000) and abs(step_size * err2) < abs(slope / 100000):
            # break
        rss = get_RSS(input_feature, output)
        if abs(rss) > abs(prev_rss):
            step_size /= 2
            intercept -= step_size * prev_err
            slope -= step_size * prev_err2
            print(t, "- ", "s:",slope," i:", intercept," k:", k," e:", err, " e2:", err2, " RSS:", rss, " diff:", result_rss - rss)
            continue
        else:
            step_size *= 1.4
        intercept += step_size * err
        slope += step_size * err2
        prev_err, prev_err2, prev_rss = err, err2, rss
        print(t, ": ", "s:",slope," i:", intercept," k:", k," e:", err, " e2:", err2, " RSS:", rss, " diff:", rss - result_rss)

def destandardize(input_feature, output):
    global intercept, slope
    mean_x, std_deviation_x = get_mean_std_deviation(input_feature)
    mean_y, std_deviation_y = get_mean_std_deviation(output)
    intercept = (intercept - slope * (mean_x / std_deviation_x)) * std_deviation_y + mean_y
    slope = (std_deviation_y / std_deviation_x) * slope



b_input = standardize(pf_input)
b_output = standardize(pf_output)


simple_linear_regression(pf_input, pf_output)
print("Target rss: ", get_RSS(pf_input, pf_output))

simple_linear_regression(b_input, b_output)
result_rss = get_RSS(b_input, b_output)

gradient_descent(b_input, b_output)
destandardize(pf_input, pf_output)
print("Result rss: ", get_RSS(pf_input, pf_output))
# print("Result rss_standardized: ", get_RSS(b_input, b_output))

# Y = mX + q
# (y - mY) / sY = m(x - mX) / sX + q

# q = (q - m * (mX / sX)) * sY + mY
# m = (sY / sX) * m

# a_intercept, a_slope = intercept, slope
# simple_linear_regression(b_input, b_output)
# print(intercept, slope)
# print(get_RSS(pf_input, pf_output))



draw_graph()

