// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, CodeIterator

static class h16 extends h16
{

            int deltaSize()
            {
/*1425*/        return state != 2 ? 0 : 5;
            }

            void write32(int i, byte abyte0[], int j, byte abyte1[])
            {
/*1429*/        abyte1[j] = (byte)opcode(abyte0[i] & 0xff);
/*1430*/        abyte1[j + 1] = 0;
/*1431*/        abyte1[j + 2] = 8;
/*1432*/        abyte1[j + 3] = -56;
/*1433*/        ByteArray.write32bit(offset - 3, abyte1, j + 4);
            }

            int opcode(int i)
            {
/*1437*/        if(i == 198)
/*1438*/            return 199;
/*1439*/        if(i == 199)
/*1440*/            return 198;
/*1442*/        if((i - 153 & 1) == 0)
/*1443*/            return i + 1;
/*1445*/        else
/*1445*/            return i - 1;
            }

            h16(int i, int j)
            {
/*1421*/        super(i, j);
            }
}
