// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ControlFlow.java

package javassist.bytecode.analysis;

import javassist.bytecode.stackmap.BasicBlock;

// Referenced classes of package javassist.bytecode.analysis:
//            ControlFlow

class de extends cess
{

            BasicBlock[] exits(de de)
            {
/* 148*/        return de.access._mth100(de).getExit();
            }

            BasicBlock[] entrances(de de)
            {
/* 149*/        return de.access._mth100(de).entrances;
            }

            final ControlFlow this$0;

            de(de ade[])
            {
/* 147*/        this$0 = ControlFlow.this;
/* 147*/        super(ade);
            }
}
