// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeData.java

package javassist.bytecode.stackmap;

import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData

public static class <init> extends <init>
{

            public <init> copy()
            {
/* 773*/        return new <init>(getName());
            }

            public int getTypeTag()
            {
/* 776*/        return 6;
            }

            public int getTypeData(ConstPool constpool)
            {
/* 780*/        return 0;
            }

            public String toString()
            {
/* 783*/        return "uninit:this";
            }

            (String s)
            {
/* 770*/        super(-1, s);
            }
}
