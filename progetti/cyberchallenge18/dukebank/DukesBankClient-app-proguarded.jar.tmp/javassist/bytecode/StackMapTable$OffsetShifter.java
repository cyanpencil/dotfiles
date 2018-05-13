// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteArray, StackMapTable

static class gap extends gap
{

            public void objectOrUninitialized(int i, int j, int k)
            {
/* 821*/        if(i == 8 && where <= j)
/* 823*/            ByteArray.write16bit(j + gap, info, k);
            }

            int where;
            int gap;

            public (StackMapTable stackmaptable, int i, int j)
            {
/* 815*/        super(stackmaptable);
/* 816*/        where = i;
/* 817*/        gap = j;
            }
}
