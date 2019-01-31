// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicBlock.java

package javassist.bytecode.stackmap;


// Referenced classes of package javassist.bytecode.stackmap:
//            BasicBlock

public static class next
{

            public next next;
            public BasicBlock body;
            public int typeIndex;

            (BasicBlock basicblock, int i,  )
            {
/*  63*/        body = basicblock;
/*  64*/        typeIndex = i;
/*  65*/        next = ;
            }
}
