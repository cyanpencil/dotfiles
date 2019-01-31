// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongVector.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ConstInfo

final class LongVector
{

            public LongVector()
            {
/*  27*/        objects = new ConstInfo[8][];
/*  28*/        elements = 0;
            }

            public LongVector(int i)
            {
/*  32*/        i = (i >> 7 & -8) + 8;
/*  33*/        objects = new ConstInfo[i][];
/*  34*/        elements = 0;
            }

            public final int size()
            {
/*  37*/        return elements;
            }

            public final int capacity()
            {
/*  39*/        return objects.length << 7;
            }

            public final ConstInfo elementAt(int i)
            {
/*  42*/        if(i < 0 || elements <= i)
/*  43*/            return null;
/*  45*/        else
/*  45*/            return objects[i >> 7][i & 0x7f];
            }

            public final void addElement(ConstInfo constinfo)
            {
/*  49*/        int i = elements >> 7;
/*  50*/        int j = elements & 0x7f;
/*  51*/        int k = objects.length;
/*  52*/        if(i >= k)
                {
/*  53*/            ConstInfo aconstinfo[][] = new ConstInfo[k + 8][];
/*  54*/            System.arraycopy(objects, 0, aconstinfo, 0, k);
/*  55*/            objects = aconstinfo;
                }
/*  58*/        if(objects[i] == null)
/*  59*/            objects[i] = new ConstInfo[128];
/*  61*/        objects[i][j] = constinfo;
/*  62*/        elements++;
            }

            static final int ASIZE = 128;
            static final int ABITS = 7;
            static final int VSIZE = 8;
            private ConstInfo objects[][];
            private int elements;
}
