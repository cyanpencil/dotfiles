// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypedBlock.java

package javassist.bytecode.stackmap;


// Referenced classes of package javassist.bytecode.stackmap:
//            BasicBlock, TypedBlock

public static class  extends 
{

            protected BasicBlock makeBlock(int i)
            {
/* 114*/        return new TypedBlock(i);
            }

            protected BasicBlock[] makeArray(int i)
            {
/* 118*/        return new TypedBlock[i];
            }

            public ()
            {
            }
}
