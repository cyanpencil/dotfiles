// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtMethod.java

package javassist;

import javassist.bytecode.Bytecode;

// Referenced classes of package javassist:
//            CannotCompileException, CtMethod

static class param extends param
{

            int compile(Bytecode bytecode)
                throws CannotCompileException
            {
/* 382*/        bytecode.addIconst(param);
/* 383*/        return 1;
            }

            String descriptor()
            {
/* 387*/        return "([Ljava/lang/Object;I)Ljava/lang/Object;";
            }

            String constDescriptor()
            {
/* 391*/        return "([Ljava/lang/Object;I)V";
            }

            int param;

            (int i)
            {
/* 378*/        param = i;
            }
}
