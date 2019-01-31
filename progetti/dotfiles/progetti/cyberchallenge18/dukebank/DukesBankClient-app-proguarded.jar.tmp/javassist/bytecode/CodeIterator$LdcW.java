// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, CodeIterator

static class state extends h
{

            boolean expanded()
            {
/*1340*/        if(state)
                {
/*1341*/            state = false;
/*1342*/            return true;
                } else
                {
/*1345*/            return false;
                }
            }

            int deltaSize()
            {
/*1348*/        return 1;
            }

            int write(int i, byte abyte0[], int j, byte abyte1[])
            {
/*1351*/        abyte1[j] = 19;
/*1352*/        ByteArray.write16bit(index, abyte1, j + 1);
/*1353*/        return 2;
            }

            int index;
            boolean state;

            h(int i, int j)
            {
/*1334*/        super(i);
/*1335*/        index = j;
/*1336*/        state = true;
            }
}
