// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            BadBytecode, CodeIterator

static abstract class orgPos
{

            void shift(int i, int j, boolean flag)
            {
/*1294*/        if(i < pos || i == pos && flag)
/*1295*/            pos += j;
            }

            static int shiftOffset(int i, int j, int k, int l, boolean flag)
            {
/*1300*/label0:
                {
/*1300*/            int i1 = i + j;
/*1301*/            if(i < k)
                    {
/*1302*/                if(k < i1 || flag && k == i1)
/*1303*/                    j += l;
/*1303*/                break label0;
                    }
/*1305*/            if(i == k)
                    {
/*1308*/                if(i1 >= k || !flag)
                        {
/*1310*/                    if(k < i1 && !flag)
/*1311*/                        j += l;
/*1311*/                    break label0;
                        }
                    } else
/*1314*/            if(i1 >= k && (flag || k != i1))
/*1315*/                break label0;
/*1315*/            j -= l;
                }
/*1317*/        return j;
            }

            boolean expanded()
            {
/*1320*/        return false;
            }

            int gapChanged()
            {
/*1321*/        return 0;
            }

            int deltaSize()
            {
/*1322*/        return 0;
            }

            abstract int write(int i, byte abyte0[], int j, byte abyte1[])
                throws BadBytecode;

            int pos;
            int orgPos;

            (int i)
            {
/*1292*/        pos = orgPos = i;
            }
}
