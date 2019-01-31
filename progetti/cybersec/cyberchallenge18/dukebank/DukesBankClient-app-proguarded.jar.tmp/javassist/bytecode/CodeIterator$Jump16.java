// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, CodeIterator

static class _cls6 extends _cls6
{

            int deltaSize()
            {
/*1409*/        return state != 2 ? 0 : 2;
            }

            void write32(int i, byte abyte0[], int j, byte abyte1[])
            {
/*1413*/        abyte1[j] = (byte)((abyte0[i] & 0xff) != 167 ? '\311' : 200);
/*1414*/        ByteArray.write32bit(offset, abyte1, j + 1);
            }

            _cls6(int i, int j)
            {
/*1405*/        super(i, j);
            }
}
