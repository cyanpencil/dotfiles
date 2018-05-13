// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ControlFlow.java

package javassist.bytecode.analysis;

import javassist.bytecode.stackmap.BasicBlock;

// Referenced classes of package javassist.bytecode.analysis:
//            ControlFlow

class er extends javassist.bytecode.stackmap.er
{

            protected BasicBlock makeBlock(int i)
            {
/*  68*/        return new ock(i, ControlFlow.access$000(ControlFlow.this));
            }

            protected BasicBlock[] makeArray(int i)
            {
/*  71*/        return new ock[i];
            }

            final ControlFlow this$0;

            er()
            {
/*  66*/        this$0 = ControlFlow.this;
/*  66*/        super();
            }
}
