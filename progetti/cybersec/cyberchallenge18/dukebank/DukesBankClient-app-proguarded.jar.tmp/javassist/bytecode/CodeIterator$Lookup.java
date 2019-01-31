// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, CodeIterator

static class matches extends r
{

            int write2(int i, byte abyte0[])
            {
                int j;
/*1585*/        ByteArray.write32bit(j = matches.length, abyte0, i);
/*1587*/        i += 4;
/*1588*/        for(int k = 0; k < j; k++)
                {
/*1589*/            ByteArray.write32bit(matches[k], abyte0, i);
/*1590*/            ByteArray.write32bit(offsets[k], abyte0, i + 4);
/*1591*/            i += 8;
                }

/*1594*/        return 4 + j * 8;
            }

            int tableSize()
            {
/*1597*/        return 4 + 8 * matches.length;
            }

            int matches[];

            s(int i, int j, int ai[], int ai1[], s s)
            {
/*1580*/        super(i, j, ai1, s);
/*1581*/        matches = ai;
            }
}
