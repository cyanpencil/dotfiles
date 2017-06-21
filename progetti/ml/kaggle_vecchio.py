
#TODO
# 1) usare i test set e train set
# 2) convertire le stringhe in numeri
# 3) esplorare meglio come sono correlati i dati
# 4) vedere bene come usare i nan



import pandas as pd
import math, sys
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt
import matplotlib.cbook as cbook

dummies_removed = {}
dummies_categories = set([])


def adjust_input(df):
    global dummies_categories
    global dummies_removed
    for category in df.columns:
        if df[category].dtype != object:
            #se la colonna è fatta di numeri, sostituisco i nan con la media
            df.fillna(df.mean(), inplace=True)
        else:
            #altrimenti, se sono stringhe, creo le dummy variables
            df[category].fillna("none", inplace=True)
            # if category not in dummies_removed.keys():
                # dummies_removed[category] = df[category][1]
            # example = dummies_removed[category]
            # example = str(df[category][1])
            # dummies = pd.get_dummies(df[category]).rename(columns=lambda x: category + '_' + str(x))
            # dummies_categories = dummies_categories | set(dummies.columns)
            # df = pd.concat([df, dummies], axis=1)
            # df.drop([category, category + '_' + example], inplace=True, axis=1)
    return df


# pf = pd.read_csv("train_adjusted.csv")
# rf = pd.read_csv("real_train_adjusted.csv")
# tf = pd.read_csv("test_adjusted.csv")
# rtf = pd.read_csv("real_test_adjusted.csv")

rf = pd.read_csv("train.csv")
pf = pd.read_csv("train.csv")
tf = pd.read_csv("test.csv")
rtf = pd.read_csv("test.csv")


pf = adjust_input(pf)
rf = adjust_input(rf)
tf = adjust_input(tf)
rtf = adjust_input(rtf)

pf.to_csv("out.csv")
tf.to_csv("out2.csv")

dataframes = [pf, tf, rf, rtf]

for e,df in enumerate(dataframes):
    for column in dummies_categories:
        if column not in df.columns:
            df[column] = np.zeros(len(df.index))



#Standardizzo l'input. Prima di aggiungerlo alle feature. Poi riaggiusto dentro predict_non_standardized_output
for c in pf.columns:
    if pf[c].dtype != object and rf[c].std() != 0:
        pf[c] = (pf[c] - rf[c].mean()) / rf[c].std()

for c in tf.columns:
    if tf[c].dtype != object and rf[c].std() != 0:
        tf[c] = (tf[c] - rf[c].mean()) / rf[c].std()



#Roba vecchia, non dovrebbe servire più
str_input = "LotArea"
str_output = "SalePrice"
pf_input = np.array(pf[str_input].tolist())
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
    # ax.plot(pf["long"].tolist(), pf_output, "g.", markersize=3)
    # ax.plot(b_input, b_output, "g.", markersize=3)
    # ax.plot(b_input, predict_output(feature_matrix, weights), "k.", lw=2)
    ax.plot(rf[str_input], rf[str_output], "g.", markersize=3)
    ax.plot(rf[str_input], predict_non_standardized_output(feature_matrix, weights), "k.", lw=2)
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

def predict_non_standardized_output(features, weights):
    return (np.dot(features, weights) * rf[str_output].std()) + rf[str_output].mean()

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
    momentum = 0.995
    step_size = 0.00005
    prev_err = [sys.maxsize] * len(weights)
    last_delta = [0] * len(weights)
    last_weights = [0] * len(weights)
    prev_rss = sys.maxsize
    for t in range(1500):
        #aggiorno i weight
        magnitude = 0
        predictions = np.dot(feature_matrix, weights)
        err = output - predictions
        for k, w in enumerate(weights):
            gradient = np.dot(err, feature_matrix[:, k])
            last_weights[k] = weights[k]
            weights[k] += momentum * last_delta[k] + step_size * 2 * gradient
            last_delta[k] = momentum * last_delta[k] + step_size * 2 * gradient
            # last_delta[k] = step_size * 2 * gradient
            prev_err[k] = gradient
            magnitude += gradient**2
        #aggiornati i weights, faccio il controllo
        rss = get_RSS_matrix(weights, feature_matrix, output)
        while (abs(rss) > abs(prev_rss)):
            if step_size == 0:
                break
            step_size /= 2
            last_delta = [0] * len(weights)
            for i in range(len(weights)):
                weights[i] = last_weights[i] + step_size * 2 *  prev_err[i]
            print("Sforato; ", " step:", step_size, " RSS:", rss)
            rss = get_RSS_matrix(weights, feature_matrix, output)
            continue
        step_size *= 1.01
        prev_rss = rss
        # print(t, " w:", weights, "step: ", step_size, " RSS:", rss, " Mag:", magnitude)
        print(t, "rss: ", rss, " Mag:", magnitude, " step:", step_size)
        if magnitude < 10**-4 or step_size == 0:
            break


def standardize(input_feature):
    mean, std_deviation = get_mean_std_deviation(input_feature)
    res = [(x_i - mean) / std_deviation for x_i in input_feature]
    return res

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


def create_feature_matrix(features):
    n = len(features)
    feature_matrix = np.zeros(len(pf_input) * n)
    feature_matrix.shape = (len(pf_input), n)
    for i in range(n):
        feature_matrix[:, i] = features[i]
    return feature_matrix

#Attenzione! la distione tra test e train sta nel fatto che devo calcolare media e std solo della train!
def create_standardized_feature_matrix(train_features, test_features):
    columns, rows = len(test_features), len(test_features[-1]) #Uso -1 perchè se uso 0 non so ma mi da errore
    print(columns, rows)
    feature_matrix = np.zeros(columns * rows)
    feature_matrix.shape = (rows, columns)
    for i in range(1,columns): #per evitare la prima colonna piena di 1
        if train_features[i].std() != 0:
            feature_matrix[:, i] = ((test_features[i] - train_features[i].mean()) / train_features[i].std())
    return feature_matrix

def write_solution():
    out_file = open("sol.csv", "w")
    out_file.write("Id,SalePrice")
    out_file.write("\n")
    predictions = predict_non_standardized_output(test_matrix, weights)
    for e, p in enumerate(predictions):
        out_file.write(str(rtf["Id"][e]) + "," + str(p) + "\n")



X = np.array(pf_input)


features = []
features += [np.ones(len(X))]
for e,c in enumerate(pf.columns):
    if e < 959 and c != str_output and pf[c].dtype != object:
        features += [np.array(pf[c])]
        features += [np.array(pf[c])**2]
        # features += [np.array(pf[c])**3]
        # features += [1/(np.array(pf[c])**3 + 1)]
        # features += [np.sin(np.array(pf[c])**3 + 1)]

test_features = []
test_features += [np.ones(len(X))]
for e,c in enumerate(tf.columns):
    if e < 959 and c != str_output and tf[c].dtype != object:
        test_features += [np.array(tf[c])]
        test_features += [np.array(tf[c])**2]
        # test_features += [np.array(tf[c])**3]
        # test_features += [1/(np.array(tf[c])**3 + 1)]
        # test_features += [np.sin(np.array(tf[c])**3 + 1)]

print(features)


b_input = standardize(pf_input)
b_output = standardize(pf_output)


# feature_matrix = create_feature_matrix(features)
feature_matrix = create_standardized_feature_matrix(features, features)
test_matrix = create_standardized_feature_matrix(features, test_features)
weights = np.zeros(len(features))


gradient_descent_multiple(weights, feature_matrix, b_output)


print(weights)

write_solution()

draw_graph()
