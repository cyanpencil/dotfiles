// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Operations.java

package com.owlike.genson;

import java.lang.reflect.Array;

public final class Operations
{

            public Operations()
            {
            }

            public static transient Object[] union(Class class1, Object aobj[][])
            {
/*   7*/        int i = 0;
                Object aobj1[][];
/*   8*/        int j = (aobj1 = aobj).length;
/*   8*/        for(int k = 0; k < j; k++)
                {
/*   8*/            Object aobj2[] = aobj1[k];
/*   9*/            i += aobj2.length;
                }

/*  10*/        aobj1 = ((Object [][]) ((Object[])class1.cast(Array.newInstance(class1.getComponentType(), i))));
/*  11*/        j = 0;
/*  11*/        int l = 0;
/*  11*/        for(; j < aobj.length; j++)
                {
/*  12*/            System.arraycopy(((Object) (aobj[j])), 0, ((Object) (aobj1)), l, aobj[j].length);
/*  11*/            l += aobj[j].length;
                }

/*  13*/        return ((Object []) (aobj1));
            }

            public static byte[] expandArray(byte abyte0[], int i, double d)
            {
/*  17*/        if(i >= abyte0.length)
                {
/*  18*/            i = new byte[(int)((double)abyte0.length * d)];
/*  19*/            System.arraycopy(abyte0, 0, i, 0, abyte0.length);
/*  20*/            return i;
                } else
                {
/*  21*/            return abyte0;
                }
            }

            public static byte[] truncateArray(byte abyte0[], int i)
            {
/*  25*/        if(i < abyte0.length)
                {
/*  26*/            byte abyte1[] = new byte[i];
/*  27*/            System.arraycopy(abyte0, 0, abyte1, 0, i);
/*  28*/            return abyte1;
                } else
                {
/*  29*/            return abyte0;
                }
            }

            public static transient void checkNotNull(Object aobj[])
            {
/*  33*/        int i = (aobj = aobj).length;
/*  33*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*  33*/            if((obj = aobj[j]) == null)
/*  34*/                throw new IllegalArgumentException("Null not allowed!");
                }

            }
}
