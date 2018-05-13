// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ControlFlow.java

package javassist.bytecode.analysis;

import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.stackmap.BasicBlock;

// Referenced classes of package javassist.bytecode.analysis:
//            ControlFlow

public static class eIndex
{

            public eIndex block()
            {
/* 485*/        return node;
            }

            public String type()
            {
/* 492*/        if(typeIndex == 0)
/* 493*/            return "java.lang.Throwable";
/* 495*/        else
/* 495*/            return node.thod.getConstPool().getClassInfo(typeIndex);
            }

            private typeIndex node;
            private int typeIndex;

            (javassist.bytecode.stackmap. )
            {
/* 478*/        node = (node).y;
/* 479*/        typeIndex = .eIndex;
            }
}
