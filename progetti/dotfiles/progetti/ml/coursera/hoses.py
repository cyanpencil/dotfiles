import pandas as pd
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt
import matplotlib.cbook as cbook

pf = pd.read_csv("csv.csv")
str_input = "sqft_living"
pf_input = pf[str_input]
pf_output = pf["price"]
intercept, slope = 0, 0
max_input, med_input, med_output = 0, 0, 0
for i in pf_input:
    max_input = max(max_input, i)
    med_input += i
for i in pf_output:
    med_output += i
med_input /= len(pf_input)
med_output /= len(pf_output)
result_rss = 0


def draw_graph():
    ax = plt.subplot(111)
    ax.plot(pf_input, pf["price"], "g.", markersize=1)
    ax.plot([0, max_input], [intercept, (max_input*slope + intercept)], "k--", lw=2)
    ax.set_xlabel(str_input)
    ax.set_ylabel("price")
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
    print("Initial values: ", slope, intercept)
    print(get_RSS(pf_input, pf["price"]))
    # intercept, slope = 0, 1
    # step_size = 0.00000000006
    # step_size_sqft_living = 00000000029
    # step_size_sqft_lot = 00000000000029
    # step_size_bathrooms = 0.00029
    # step_size_bedrooms = 0.000029
    # step_size_condition = 0.000029
    # step_size = 0.000000000000001
    # step_size = 1
    step_size = 0.00000001
    prev_err, prev_err2, prev_rss = 9**99, 9**99, 9**99
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
        rss = get_RSS(pf_input, pf_output)
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
        print(t, ": ", "s:",slope," i:", intercept," k:", k," e:", err, " e2:", err2, " RSS:", rss, " diff:", result_rss - rss)


simple_linear_regression(pf_input, pf["price"])
result_rss = get_RSS(pf_input, pf["price"])
print(intercept, slope)
print(result_rss)
gradient_descent(pf_input, pf["price"])
print(get_RSS(pf_input, pf["price"]))


draw_graph()
