// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMap

static class gap extends gap
{

            public int locals(int i, int j, int k)
            {
/* 423*/        if(where == i + j)
/* 424*/            ByteArray.write16bit(j - gap, info, i - 4);
/* 425*/        else
/* 425*/        if(where == i)
/* 426*/            ByteArray.write16bit(j + gap, info, i - 4);
/* 428*/        return super.info(i, j, k);
            }

            private int where;
            private int gap;

            public (StackMap stackmap, int i, int j)
            {
/* 417*/        super(stackmap);
/* 418*/        where = i;
/* 419*/        gap = j;
            }
}
