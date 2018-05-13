// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RankedProvider.java

package org.glassfish.jersey.model.internal;

import java.util.Set;
import javax.annotation.Priority;

public class RankedProvider
{

            public RankedProvider(Object obj)
            {
/*  71*/        provider = obj;
/*  72*/        rank = computeRank(obj, -1);
/*  73*/        contractTypes = null;
            }

            public RankedProvider(Object obj, int i)
            {
/*  83*/        this(obj, i, null);
            }

            public RankedProvider(Object obj, int i, Set set)
            {
/*  94*/        provider = obj;
/*  95*/        rank = computeRank(obj, i);
/*  96*/        contractTypes = set;
            }

            private int computeRank(Object obj, int i)
            {
/* 100*/        if(i > 0)
/* 101*/            return i;
/* 103*/        if(obj.getClass().isAnnotationPresent(javax/annotation/Priority))
/* 104*/            return ((Priority)obj.getClass().getAnnotation(javax/annotation/Priority)).value();
/* 106*/        else
/* 106*/            return 5000;
            }

            public Object getProvider()
            {
/* 112*/        return provider;
            }

            public int getRank()
            {
/* 116*/        return rank;
            }

            public Set getContractTypes()
            {
/* 126*/        return contractTypes;
            }

            public String toString()
            {
/* 131*/        return provider.getClass().getName();
            }

            private final Object provider;
            private final int rank;
            private final Set contractTypes;
}
