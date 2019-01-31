// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMap.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMap

static class exclusive extends exclusive
{

            public int locals(int i, int j, int k)
            {
/* 394*/        if(exclusive ? where <= j : where < j)
/* 395*/            ByteArray.write16bit(j + gap, info, i - 4);
/* 397*/        return super.ocals(i, j, k);
            }

            public void uninitialized(int i, int j)
            {
/* 401*/        if(where <= j)
/* 402*/            ByteArray.write16bit(j + gap, info, i + 1);
            }

            private int where;
            private int gap;
            private boolean exclusive;

            public (StackMap stackmap, int i, int j, boolean flag)
            {
/* 387*/        super(stackmap);
/* 388*/        where = i;
/* 389*/        gap = j;
/* 390*/        exclusive = flag;
            }
}
