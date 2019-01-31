// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, CodeIterator

static abstract class state extends state
{

            void shift(int i, int j, boolean flag)
            {
/*1371*/        offset = shiftOffset(pos, offset, i, j, flag);
/*1372*/        super.ift(i, j, flag);
/*1373*/        if(state == 0 && (offset < -32768 || 32767 < offset))
/*1375*/            state = 1;
            }

            boolean expanded()
            {
/*1379*/        if(state == 1)
                {
/*1380*/            state = 2;
/*1381*/            return true;
                } else
                {
/*1384*/            return false;
                }
            }

            abstract int deltaSize();

            abstract void write32(int i, byte abyte0[], int j, byte abyte1[]);

            int write(int i, byte abyte0[], int j, byte abyte1[])
            {
/*1391*/        if(state == 2)
                {
/*1392*/            write32(i, abyte0, j, abyte1);
                } else
                {
/*1394*/            abyte1[j] = abyte0[i];
/*1395*/            ByteArray.write16bit(offset, abyte1, j + 1);
                }
/*1398*/        return 3;
            }

            int offset;
            int state;
            static final int BIT16 = 0;
            static final int EXPAND = 1;
            static final int BIT32 = 2;

            (int i, int j)
            {
/*1365*/        super(i);
/*1366*/        offset = j;
/*1367*/        state = 0;
            }
}
