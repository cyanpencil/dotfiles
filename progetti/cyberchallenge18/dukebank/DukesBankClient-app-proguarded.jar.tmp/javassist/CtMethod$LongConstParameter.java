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
/* 403*/        bytecode.addLconst(param);
/* 404*/        return 2;
            }

            String descriptor()
            {
/* 408*/        return "([Ljava/lang/Object;J)Ljava/lang/Object;";
            }

            String constDescriptor()
            {
/* 412*/        return "([Ljava/lang/Object;J)V";
            }

            long param;

            (long l)
            {
/* 399*/        param = l;
            }
}
