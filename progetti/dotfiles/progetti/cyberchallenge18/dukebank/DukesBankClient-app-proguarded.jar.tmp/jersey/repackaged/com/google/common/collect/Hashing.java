// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Hashing.java

package jersey.repackaged.com.google.common.collect;


final class Hashing
{

            static int smear(int i)
            {
/*  47*/        return 0x1b873593 * Integer.rotateLeft(i * 0xcc9e2d51, 15);
            }

            static int closedTableSize(int i, double d)
            {
/*  59*/        int j = Integer.highestOneBit(i = Math.max(i, 2));
/*  62*/        if(i > (int)(d * (double)j))
                {
/*  63*/            if((j <<= 1) > 0)
/*  64*/                return j;
/*  64*/            else
/*  64*/                return MAX_TABLE_SIZE;
                } else
                {
/*  66*/            return j;
                }
            }

            private static int MAX_TABLE_SIZE = 0x40000000;

}
