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
            if category not in dummies_removed.keys():
                dummies_removed[category] = df[category][1]
            example = dummies_removed[category]
            example = str(df[category][1])
            dummies = pd.get_dummies(df[category]).rename(columns=lambda x: category + '_' + str(x))
            dummies_categories = dummies_categories | set(dummies.columns)
            df = pd.concat([df, dummies], axis=1)
            df.drop([category, category + '_' + example], inplace=True, axis=1)
    return df


pf = pd.read_csv("train.csv")
tf = pd.read_csv("test.csv")
vf = pd.read_csv("validation.csv")


pf = adjust_input(pf)
tf = adjust_input(tf)
vf = adjust_input(vf)

dataframes = [pf, tf, vf]

for e,df in enumerate(dataframes):
    for column in dummies_categories:
        if column not in df.columns:
            df[column] = np.zeros(len(df.index))

pf.to_csv("train_adjusted.csv", index=False)
tf.to_csv("test_adjusted.csv", index=False)
vf.to_csv("validation_adjusted.csv", index=False)
