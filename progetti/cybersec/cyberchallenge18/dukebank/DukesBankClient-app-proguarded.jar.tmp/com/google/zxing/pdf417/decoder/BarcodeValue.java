// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BarcodeValue.java

package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.*;

final class BarcodeValue
{

            BarcodeValue()
            {
            }

            final void setValue(int i)
            {
                Integer integer;
/*  37*/        if((integer = (Integer)values.get(Integer.valueOf(i))) == null)
/*  39*/            integer = Integer.valueOf(0);
/*  41*/        integer = Integer.valueOf(integer.intValue() + 1);
/*  42*/        values.put(Integer.valueOf(i), integer);
            }

            final int[] getValue()
            {
/*  50*/        int i = -1;
/*  51*/        ArrayList arraylist = new ArrayList();
/*  52*/        Iterator iterator = values.entrySet().iterator();
/*  52*/        do
                {
/*  52*/            if(!iterator.hasNext())
/*  52*/                break;
                    java.util.Map.Entry entry;
/*  52*/            if(((Integer)(entry = (java.util.Map.Entry)iterator.next()).getValue()).intValue() > i)
                    {
/*  54*/                i = ((Integer)entry.getValue()).intValue();
/*  55*/                arraylist.clear();
/*  56*/                arraylist.add(entry.getKey());
                    } else
/*  57*/            if(((Integer)entry.getValue()).intValue() == i)
/*  58*/                arraylist.add(entry.getKey());
                } while(true);
/*  61*/        return PDF417Common.toIntArray(arraylist);
            }

            public final Integer getConfidence(int i)
            {
/*  65*/        return (Integer)values.get(Integer.valueOf(i));
            }

            private final Map values = new HashMap();
}
