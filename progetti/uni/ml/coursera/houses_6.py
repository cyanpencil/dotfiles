import pandas as pd
import math, sys
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt
import matplotlib.cbook as cbook

pf = pd.read_csv("csv.csv")
pf = (pf - pf.mean()) / pf.std()
str_input = "sqft_living"
str_output = "price"
pf_input = np.array(pf[str_input].tolist())
# pf_input = np.log(pf_input + 1)
# pf_input = np.sqrt(pf_input)
# pf_input = (pf_input - pf_input.min()) / (pf_input.max() - pf_input.min())
# pf_input = (pf_input - pf_input.mean()) / pf_input.std()

pf_output = pf[str_output].tolist()
max_input = max(pf_input)
result_rss = 0
intercept, slope = 0, 0

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


def draw_graph():
    ax = plt.subplot(111)
    # ax.plot(pf["sqft_living"].tolist(), pf_output, "g.", markersize=3)
    ax.plot(b_input, b_output, "g.", markersize=3)
    ax.plot(b_input, predict_output(b_feature_matrix, weights), "k.", lw=2)
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

def predict_output(input_features, weights):
    return np.dot(input_features, weights)

def gradient_descent(input_feature, output):
    global intercept, slope
    intercept, slope = 50000, 0
    step_size = 0.05
    prev_err, prev_err2, prev_rss = [sys.maxsize] * 3
    for t in range(150):
        err, err2, rss = 0, 0, 0
        for idx, x in enumerate(input_feature):
            a = (output[idx] - (intercept + slope*x))
            err  += a
            err2 += a * x
        if (err ** 2 + err2 ** 2) < 0.00001:
            break
        rss = get_RSS(input_feature, output)
        if abs(rss) > abs(prev_rss):
            step_size /= 2
            intercept -= step_size * prev_err
            slope -= step_size * prev_err2
            print(t, "- ", "s:",slope," i:", intercept," step:", step_size," e:", err, " e2:", err2, " RSS:", rss, " diff:", rss - result_rss)
            continue
        else:
            step_size *= 1.4
            intercept += step_size * err
            slope += step_size * err2
            prev_err, prev_err2, prev_rss = err, err2, rss
            print(t, ": ", "s:",slope," i:", intercept," step:", step_size," e:", err, " e2:", err2, " RSS:", rss, " diff:", rss - result_rss)

def get_RSS_matrix(weights, feature_matrix, output):
    p = np.dot(feature_matrix, weights)
    rss = (output - p) ** 2
    return rss.sum()


def gradient_descent_multiple(weights, feature_matrix, output):
    step_size = 1
    prev_err = [sys.maxsize] * len(weights)
    prev_rss = sys.maxsize
    for t in range(11500):
        #aggiorno i weight
        magnitude = 0
        predictions = np.dot(feature_matrix, weights)
        err = output - predictions
        for k, w in enumerate(weights):
            gradient = np.dot(err, feature_matrix[:, k])
            weights[k] += step_size * 2 * gradient
            prev_err[k] = gradient
            magnitude += gradient**2
        #aggiornati i weights, faccio il controllo
        rss = get_RSS_matrix(weights, feature_matrix, output)
        while (abs(rss) > abs(prev_rss)):
            if step_size == 0:
                break
            step_size /= 2
            for i in range(len(weights)):
                weights[i] -= step_size * 2 *  prev_err[i]
            # print("Sforato; ",weights, " step:", step_size, " prev_err:", prev_err)
            print("Sforato; ",weights, " step:", step_size)
            rss = get_RSS_matrix(weights, feature_matrix, output)
            continue
        step_size *= 1.3
        prev_rss = rss
        print(t, " w:", weights, "step: ", step_size, " RSS:", rss, " Mag:", magnitude)
        if magnitude < 10**-9 or step_size == 0:
            break



def standardize(input_feature):
    mean, std_deviation = get_mean_std_deviation(input_feature)
    res = [(x_i - mean) / std_deviation for x_i in input_feature]
    return res

def standardize_matrix(feature_matrix):
    sol = feature_matrix.copy()
    for i in range(len(feature_matrix[0])):
        feature = feature_matrix[:,i]
        print(feature)
        mean, std_deviation = get_mean_std_deviation(feature)
        if std_deviation == 0:
            continue
        print(mean, std_deviation)
        res = [(x_i - mean) / std_deviation for x_i in feature]
        sol[:, i] = res
    return sol

def standardize_whole_matrix(feature_matrix):
    sol = feature_matrix.copy()
    for i in range(len(feature_matrix[0])):
        feature = feature_matrix[:,i]
        mean, std_deviation = get_mean_std_deviation(feature)
        if std_deviation == 0:
            continue
        res = np.array([(x_i - mean) / (std_deviation) for x_i in feature])
        sol[:, i] = res
        print("Mean:", mean, " std_deviation:", std_deviation)
    return sol



def destandardize(input_feature, output):
    global intercept, slope
    mean_x, std_deviation_x = get_mean_std_deviation(input_feature)
    mean_y, std_deviation_y = get_mean_std_deviation(output)
    intercept = (intercept - slope * (mean_x / std_deviation_x)) * std_deviation_y + mean_y
    slope = (std_deviation_y / std_deviation_x) * slope

    # Y = mX + q
    # (y - mY) / sY = m(x - mX) / sX + q

    # q = (q - m * (mX / sX)) * sY + mY
    # m = (sY / sX) * m

def destandardize2(weights, input_feature, output):
    mean_x, std_deviation_x = get_mean_std_deviation(input_feature)
    mean_y, std_deviation_y = get_mean_std_deviation(output)
    for i in range(len(weights)):
        weights[i] = (std_deviation_y / std_deviation_x) * weights[i]
    # intercept = (intercept - slope * (mean_x / std_deviation_x)) * std_deviation_y + mean_y
    # slope = (std_deviation_y / std_deviation_x) * slope



# simple_linear_regression(pf_input, pf_output)
# print ("Optimal intercept and slope:", intercept, slope)
# print("Target rss: ", get_RSS(pf_input, pf_output))

b_input = standardize(pf_input)
b_output = standardize(pf_output)

# simple_linear_regression(b_input, b_output)
# result_rss = get_RSS(b_input, b_output)
# print("Traget rss: ", result_rss)

# gradient_descent(b_input, b_output)
# destandardize(pf_input, pf_output)

# print("Result rss: ", get_RSS(b_input, b_output))
# draw_graph()

X = np.array(pf_input)
# X = np.log(np.array(pf_input) + 1)
Y = np.array(pf["grade"])
C = np.array(pf["condition"])
S = np.array(pf["sqft_lot"])
# print(X)
# X = X / X.max()
features = []
features += [np.ones(len(X))]
features += [X]
features += [X**2]
features += [X**3]
features += [X**4]
# features += [X**5]
# features += [C]
# features += [Y]
# features += [Y**2]
# features += [Y**3]
# features += [Y**4]
# features += [Y**7]
# features += [1/S]
# features += [S]
# features += [S**2]
# features += [S**3]
# features += [S**4]
# features += [S**5]
# features += [S**6]
# features += [S**7]
# features += [S**8]
# features += [C**5]
# features += [X**3]
# features += [X**4]
# features += [X**5]
# features += [np.array(pf["grade"])]
# features += [np.array(pf["grade"])**2]
# features += [np.array(pf["grade"])**3]
# features += [np.array(pf["grade"])**5]


n = len(features)
feature_matrix = np.zeros(len(pf_input) * n)
feature_matrix.shape = (len(pf_input), n)
for i in range(n):
    feature_matrix[:, i] = features[i]

weights = np.zeros(n)

# b_feature_matrix = feature_matrix
b_feature_matrix = standardize_whole_matrix(feature_matrix)


for i in range(len(b_feature_matrix[0])):
    feature = b_feature_matrix[:, i]
    print(i, feature.max(), feature.min())

gradient_descent_multiple(weights, b_feature_matrix, b_output)
# draw_graph()

# print(b_feature_matrix.max())
