#TODO
# - Trovare un bel modo per modellare le dummy variables
# - cross validation a stecca
# - Ricercare: alcuni dicono che SalePrice è skewed a destra, e bisogna fare il log. Attenzione.
# - Pensare alla possibilità di feature combinate
# - vedere bene come usare i nan
# - Aggiungendo un altra volta la colonna degli id ottengo 0.19!!

# ,Id,MSSubClass,LotFrontage,LotArea,OverallQual,OverallCond,YearBuilt,YearRemodAdd,MasVnrArea,BsmtFinSF1,BsmtFinSF2,BsmtUnfSF,TotalBsmtSF,1stFlrSF,2ndFlrSF,LowQualFinSF,GrLivArea,BsmtFullBath,BsmtHalfBath,FullBath,HalfBath,BedroomAbvGr,KitchenAbvGr,TotRmsAbvGrd,Fireplaces,GarageYrBlt,GarageCars,GarageArea,WoodDeckSF,OpenPorchSF,EnclosedPorch,3SsnPorch,ScreenPorch,PoolArea,MiscVal,MoSold,YrSold,SalePrice,MSZoning_C (all),MSZoning_FV,MSZoning_RH,MSZoning_RM,Street_Grvl,Alley_Grvl,Alley_Pave,LotShape_IR1,LotShape_IR2,LotShape_IR3,LandContour_Bnk,LandContour_HLS,LandContour_Low,Utilities_NoSeWa,LotConfig_Corner,LotConfig_CulDSac,LotConfig_FR3,LotConfig_Inside,LandSlope_Mod,LandSlope_Sev,Neighborhood_Blmngtn,Neighborhood_Blueste,Neighborhood_BrDale,Neighborhood_BrkSide,Neighborhood_ClearCr,Neighborhood_CollgCr,Neighborhood_Crawfor,Neighborhood_Edwards,Neighborhood_Gilbert,Neighborhood_IDOTRR,Neighborhood_MeadowV,Neighborhood_Mitchel,Neighborhood_NAmes,Neighborhood_NPkVill,Neighborhood_NWAmes,Neighborhood_NoRidge,Neighborhood_NridgHt,Neighborhood_OldTown,Neighborhood_SWISU,Neighborhood_Sawyer,Neighborhood_SawyerW,Neighborhood_Somerst,Neighborhood_StoneBr,Neighborhood_Timber,Condition1_Artery,Condition1_Norm,Condition1_PosA,Condition1_PosN,Condition1_RRAe,Condition1_RRAn,Condition1_RRNe,Condition1_RRNn,Condition2_Artery,Condition2_Feedr,Condition2_PosA,Condition2_PosN,Condition2_RRAe,Condition2_RRAn,Condition2_RRNn,BldgType_2fmCon,BldgType_Duplex,BldgType_Twnhs,BldgType_TwnhsE,HouseStyle_1.5Fin,HouseStyle_1.5Unf,HouseStyle_2.5Fin,HouseStyle_2.5Unf,HouseStyle_2Story,HouseStyle_SFoyer,HouseStyle_SLvl,RoofStyle_Flat,RoofStyle_Gambrel,RoofStyle_Hip,RoofStyle_Mansard,RoofStyle_Shed,RoofMatl_ClyTile,RoofMatl_Membran,RoofMatl_Metal,RoofMatl_Roll,RoofMatl_Tar&Grv,RoofMatl_WdShake,RoofMatl_WdShngl,Exterior1st_AsbShng,Exterior1st_AsphShn,Exterior1st_BrkComm,Exterior1st_BrkFace,Exterior1st_CBlock,Exterior1st_CemntBd,Exterior1st_HdBoard,Exterior1st_ImStucc,Exterior1st_Plywood,Exterior1st_Stone,Exterior1st_Stucco,Exterior1st_VinylSd,Exterior1st_Wd Sdng,Exterior1st_WdShing,Exterior2nd_AsbShng,Exterior2nd_AsphShn,Exterior2nd_Brk Cmn,Exterior2nd_BrkFace,Exterior2nd_CBlock,Exterior2nd_CmentBd,Exterior2nd_HdBoard,Exterior2nd_ImStucc,Exterior2nd_Other,Exterior2nd_Plywood,Exterior2nd_Stone,Exterior2nd_Stucco,Exterior2nd_VinylSd,Exterior2nd_Wd Sdng,Exterior2nd_Wd Shng,MasVnrType_BrkCmn,MasVnrType_BrkFace,MasVnrType_Stone,MasVnrType_none,ExterQual_Ex,ExterQual_Fa,ExterQual_Gd,ExterCond_Ex,ExterCond_Fa,ExterCond_Gd,ExterCond_Po,Foundation_BrkTil,Foundation_PConc,Foundation_Slab,Foundation_Stone,Foundation_Wood,BsmtQual_Ex,BsmtQual_Fa,BsmtQual_TA,BsmtQual_none,BsmtCond_Fa,BsmtCond_Gd,BsmtCond_Po,BsmtCond_none,BsmtExposure_Av,BsmtExposure_Mn,BsmtExposure_No,BsmtExposure_none,BsmtFinType1_BLQ,BsmtFinType1_GLQ,BsmtFinType1_LwQ,BsmtFinType1_Rec,BsmtFinType1_Unf,BsmtFinType1_none,BsmtFinType2_ALQ,BsmtFinType2_BLQ,BsmtFinType2_GLQ,BsmtFinType2_LwQ,BsmtFinType2_Rec,BsmtFinType2_none,Heating_Floor,Heating_GasW,Heating_Grav,Heating_OthW,Heating_Wall,HeatingQC_Fa,HeatingQC_Gd,HeatingQC_Po,HeatingQC_TA,CentralAir_N,Electrical_FuseA,Electrical_FuseF,Electrical_FuseP,Electrical_Mix,Electrical_none,KitchenQual_Ex,KitchenQual_Fa,KitchenQual_Gd,Functional_Maj1,Functional_Maj2,Functional_Min1,Functional_Min2,Functional_Mod,Functional_Sev,FireplaceQu_Ex,FireplaceQu_Fa,FireplaceQu_Gd,FireplaceQu_Po,FireplaceQu_none,GarageType_2Types,GarageType_Basment,GarageType_BuiltIn,GarageType_CarPort,GarageType_Detchd,GarageType_none,GarageFinish_Fin,GarageFinish_Unf,GarageFinish_none,GarageQual_Ex,GarageQual_Fa,GarageQual_Gd,GarageQual_Po,GarageQual_none,GarageCond_Ex,GarageCond_Fa,GarageCond_Gd,GarageCond_Po,GarageCond_none,PavedDrive_N,PavedDrive_P,PoolQC_Ex,PoolQC_Fa,PoolQC_Gd,Fence_GdPrv,Fence_GdWo,Fence_MnPrv,Fence_MnWw,MiscFeature_Gar2,MiscFeature_Othr,MiscFeature_Shed,MiscFeature_TenC,SaleType_COD,SaleType_CWD,SaleType_Con,SaleType_ConLD,SaleType_ConLI,SaleType_ConLw,SaleType_New,SaleType_Oth,SaleCondition_Abnorml,SaleCondition_AdjLand,SaleCondition_Alloca,SaleCondition_Family,SaleCondition_Partial,Neighborhood_Veenker,Utilities_AllPub,LotShape_Reg,KitchenQual_none,PavedDrive_Y,RoofStyle_Gable,BsmtExposure_Gd,RoofMatl_CompShg,HouseStyle_1Story,PoolQC_none,Exterior2nd_MetalSd,Functional_none,CentralAir_Y,GarageType_Attchd,Fence_none,Condition1_Feedr,LandContour_Lvl,FireplaceQu_TA,LandSlope_Gtl,KitchenQual_TA,BldgType_1Fam,BsmtFinType1_ALQ,ExterCond_TA,Exterior1st_MetalSd,MiscFeature_none,SaleCondition_Normal,MSZoning_none,ExterQual_TA,Alley_none,Electrical_SBrkr,Functional_Typ,BsmtQual_Gd,Utilities_none,MSZoning_RL,Heating_GasA,Street_Pave,SaleType_none,GarageCond_TA,BsmtFinType2_Unf,Foundation_CBlock,MasVnrType_None,HeatingQC_Ex,Condition2_Norm,LotConfig_FR2,Exterior2nd_none,GarageQual_TA,SaleType_WD,GarageFinish_RFn,BsmtCond_TA,Exterior1st_none




import pandas as pd
import math, sys
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt
import matplotlib.cbook as cbook
from pandas.tools.plotting import scatter_matrix



str_graph_x = "Id"
str_output = "SalePrice"


#Roba vecchia, non dovrebbe servire più
# pf_input = np.array(pf[str_graph_x].tolist())

def draw_graph():
    ax = plt.subplot(111)

    # ax.plot(pf["long"].tolist(), pf[str_output].tolist(), "g.", markersize=3)
    # ax.plot(b_input, b_output, "g.", markersize=3)
    # ax.plot(b_input, predict_output(feature_matrix, weights), "k.", lw=2)

    # ax.plot(rf[str_graph_x], rf[str_output], "g.", markersize=3)
    # ax.plot(rf[str_graph_x], predict_non_standardized_output(feature_matrix, weights), "k.", lw=2)

    ax.plot(rvf[str_graph_x], rvf[str_output], "g.", markersize=3)
    ax.plot(rvf[str_graph_x], predict_non_standardized_output(validation_matrix, weights), "k.", lw=2)

    ax.set_xlabel(str_graph_x)
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

def draw_scatterplot_matrix():
    scatter_matrix(data.ix[:,:10], alpha=0.2, figsize=(6, 6), diagonal='kde')
    plt.show()

def draw_scatterplots():
    for c in data.columns:
        data.plot.scatter(x=c, y='SalePrice')
        plt.show()


def predict_output(input_features, weights):
    return np.dot(input_features, weights)

def predict_non_standardized_output(features, weights):
    return (np.dot(features, weights) * rf[str_output].std()) + rf[str_output].mean()


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
    for t in range(350):
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
    array = np.array(input_feature)
    mean, std_deviation = array.mean(), array.std()
    res = [(x_i - mean) / std_deviation for x_i in input_feature]
    return res

def create_feature_matrix(features):
    n = len(features)
    # feature_matrix = np.zeros(len(pf_input) * n)
    # feature_matrix.shape = (len(pf_input), n)
    feature_matrix = np.zeros(len(featurs[-1]) * n) #potenzialmente sbagliato
    feature_matrix.shape = (len(featurs[-1]), n)
    for i in range(n):
        feature_matrix[:, i] = features[i]
    return feature_matrix

#Attenzione! la distione tra test e train sta nel fatto che devo calcolare media e std solo della train!
def create_standardized_feature_matrix(train_features, test_features):
    columns, rows = len(test_features), len(test_features[-1]) #Uso -1 perchè se uso 0 non so ma mi da errore
    print(columns, rows)
    feature_matrix = np.zeros(columns * rows)
    feature_matrix.shape = (rows, columns)
    for i in range(1,columns):
        if train_features[i].std() != 0: #per evitare la prima colonna piena di 1
            values, counts = np.unique(train_features[i], return_counts=True)
            if not (len(counts) > 1 and counts[0] + counts[1] == len(train_features[i])): #se non è una dummy variable
                feature_matrix[:, i] = ((test_features[i] - train_features[i].mean()) / train_features[i].std())
            else:
                feature_matrix[:, i] = test_features[i]

    return feature_matrix

def write_solution():
    out_file = open("sol.csv", "w")
    out_file.write("Id,SalePrice")
    out_file.write("\n")
    predictions = predict_non_standardized_output(test_matrix, weights)
    for e, p in enumerate(predictions):
        out_file.write(str(rtf["Id"][e]) + "," + str(p) + "\n")

def add_features(feature_list, df):
    sin_features       =                      ["MoSold","YrSold"]
    cubic_features     =                      ["BedroomAbvGr"]
    quadratic_features = cubic_features +     ["OverallQual","YearBuilt","BsmtUnfSF","FullBath","BedroomAbvGr","TotRmsAbvGrd","GarageYrBlt","GarageCars"]
    linear_features    = quadratic_features + ["LotArea","YearRemodAdd","MasVnrArea","BsmtFinSF2","GrLivArea","Fireplaces","GarageArea"]

    feature_list += [np.ones(len(df.index))]
    for c in sin_features:
        feature_list += [np.sin(np.array(df[c]))]
    # for c in cubic_features:
        # feature_list += [np.array(df[c])**3]
    # for c in quadratic_features:
        # feature_list += [np.array(df[c])*+2]
    # for c in linear_features:
        # feature_list += [np.array(df[c])]
    # for c in dummy_categories:
        # feature_list += [np.array(df[c])]

    for e,c in enumerate(df.columns):
        if e < 959 and c != str_output and df[c].dtype != object and c not in dummy_categories:
            feature_list += [np.array(df[c])]
            # feature_list += [np.sin(np.array(df[c]) + 1)]
            # feature_list += [np.array(df[c])**2]
            # # feature_list += [np.array(df[c])**3]
            # # feature_list += [1/(np.array(df[c])**3 + 1)]
            # feature_list += [np.sin(np.array(df[c])**3 + 1)]
            # feature_list += [np.cos(np.array(df[c])**3 + 1)]
    for c in dummy_categories:
        feature_list += [np.array(df[c])]
        # feature_list += [np.sin(np.array(df[c]) + 1)]

def fit(pf, vf, validating=True):
    global test_matrix, weights, rf, rtf
    rf = pf.copy()
    rtf = vf.copy()

    #NON STANDARDIZZARE DUMMY VARIABLES
    for df in [pf, vf]:
        for c in df.columns:
            if df[c].dtype != object and rf[c].std() != 0 and c not in dummy_categories:
                df[c] = (df[c] - rf[c].mean()) / rf[c].std()

    train_features = []
    validation_features = []
    add_features(train_features, pf)
    add_features(validation_features, vf)

    feature_matrix = create_standardized_feature_matrix(train_features, train_features)
    test_matrix = create_standardized_feature_matrix(train_features, validation_features)
    weights = np.zeros(len(train_features))

    gradient_descent_multiple(weights, feature_matrix, pf[str_output].tolist())
    print("###############################")
    if validating:
        temp_rss = get_RSS_matrix(weights, test_matrix, vf[str_output].tolist())
        return temp_rss

def calc_mean_rmse():
    global weights
    rss_sum = 0
    for k in range(10):
        tf, vf, tf2= np.split(data, [int((k/10.0)*len(data)), int(((k+1)/10.0)*len(data))])
        pf = tf.copy().append(tf2.copy(), ignore_index=True)
        rf = pf.copy()
        rvf = vf.copy()

        # for df in [pf, vf]:
            # for c in df.columns:
                # if df[c].dtype != object and rf[c].std() != 0:
                    # df[c] = (df[c] - rf[c].mean()) / rf[c].std()

        # features = []
        # validation_features = []
        # add_features(features, pf)
        # add_features(validation_features, vf)

        # feature_matrix = create_standardized_feature_matrix(features, features)
        # validation_matrix = create_standardized_feature_matrix(features, validation_features)
        # weights = np.zeros(len(features))

        # gradient_descent_multiple(weights, feature_matrix, pf[str_output].tolist())

        # temp_rss = get_RSS_matrix(weights, validation_matrix, vf[str_output].tolist())
        # rss_sum += temp_rss
        temp_rss = fit(pf.copy(), vf.copy(), validating=True)
        # temp_rss = get_RSS_matrix(weights, test_matrix, vf[str_output].tolist())
        rss_sum += temp_rss
        print("rss temporaneo: ", temp_rss)

    print("###############################")
    print("Mean_rss: ", rss_sum/10)



data = pd.read_csv("train_adjusted.csv")
test_frame = pd.read_csv("test_adjusted.csv")
#controllo delle dummy. Dopo sostituire il nome con Dummy_*
dummy_categories = []
for column in data.columns:
    valori = data[column].value_counts()
    if 0 in valori and 1 in valori and int(valori[0]) + int(valori[1]) == len(data.index):
        dummy_categories += [column]

pf, vf = np.split(data, [int(0.85*len(data))])
rf = pf.copy()
rvf = vf.copy()
tf = pd.read_csv("test_adjusted.csv")
rtf = tf.copy()


#Standardizzo l'input. Prima di aggiungerlo alle feature. Poi riaggiusto dentro predict_non_standardized_output
# TODO Attenzione! Come dice applied regression analysis and generalized linear models, non vanno standardizzate le variabili dummy!
# Molto molto importante! Infatti, se standardizzo le dummy, succederà che non posso modellarle bene. Mi servono 1 e 0, non altri valori.
for df in [pf, tf, vf]:
    for c in df.columns:
        if df[c].dtype != object and rf[c].std() != 0 and c not in dummy_categories:
            df[c] = (df[c] - rf[c].mean()) / rf[c].std()



features = []
test_features = []
validation_features = []
add_features(features, pf)
add_features(test_features, tf)
add_features(validation_features, vf)


feature_matrix = create_standardized_feature_matrix(features, features)
test_matrix = create_standardized_feature_matrix(features, test_features)
weights = np.zeros(len(features))


gradient_descent_multiple(weights, feature_matrix, pf[str_output].tolist())
write_solution()


# fit(data.copy(), test_frame.copy(), validating=False)
# write_solution()

# calc_mean_rmse()


# draw_graph()
# draw_scatterplot_matrix()
draw_scatterplots()
