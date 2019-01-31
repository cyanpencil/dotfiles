// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, CodeIterator

static class offset extends offset
{

            void shift(int i, int j, boolean flag)
            {
/*1459*/        offset = shiftOffset(pos, offset, i, j, flag);
/*1460*/        super.shift(i, j, flag);
            }

            int write(int i, byte abyte0[], int j, byte abyte1[])
            {
/*1464*/        abyte1[j] = abyte0[i];
/*1465*/        ByteArray.write32bit(offset, abyte1, j + 1);
/*1466*/        return 5;
            }

            int offset;

            (int i, int j)
            {
/*1454*/        super(i);
/*1455*/        offset = j;
            }
}
