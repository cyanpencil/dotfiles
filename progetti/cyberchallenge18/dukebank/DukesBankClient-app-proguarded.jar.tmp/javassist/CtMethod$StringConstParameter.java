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
/* 424*/        bytecode.addLdc(param);
/* 425*/        return 1;
            }

            String descriptor()
            {
/* 429*/        return "([Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;";
            }

            String constDescriptor()
            {
/* 433*/        return "([Ljava/lang/Object;Ljava/lang/String;)V";
            }

            String param;

            (String s)
            {
/* 420*/        param = s;
            }
}
