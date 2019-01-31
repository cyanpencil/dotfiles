// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, CodeIterator

static class high extends er
{

            int write2(int i, byte abyte0[])
            {
/*1561*/        ByteArray.write32bit(low, abyte0, i);
/*1562*/        ByteArray.write32bit(high, abyte0, i + 4);
/*1563*/        int j = offsets.length;
/*1564*/        i += 8;
/*1565*/        for(int k = 0; k < j; k++)
                {
/*1566*/            ByteArray.write32bit(offsets[k], abyte0, i);
/*1567*/            i += 4;
                }

/*1570*/        return 8 + 4 * j;
            }

            int tableSize()
            {
/*1573*/        return 8 + 4 * offsets.length;
            }

            int low;
            int high;

            rs(int i, int j, int k, int l, int ai[], rs rs)
            {
/*1555*/        super(i, j, ai, rs);
/*1556*/        low = k;
/*1557*/        high = l;
            }
}
