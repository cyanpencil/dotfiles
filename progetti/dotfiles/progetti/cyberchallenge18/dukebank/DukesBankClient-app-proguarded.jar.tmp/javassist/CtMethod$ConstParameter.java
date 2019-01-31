// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtMethod.java

package javassist;

import javassist.bytecode.Bytecode;

// Referenced classes of package javassist:
//            CannotCompileException, CtMethod

public static class meter
{

            public static meter integer(int i)
            {
/* 316*/        return new er(i);
            }

            public static er integer(long l)
            {
/* 325*/        return new ter(l);
            }

            public static ter string(String s)
            {
/* 334*/        return new meter(s);
            }

            int compile(Bytecode bytecode)
                throws CannotCompileException
            {
/* 343*/        return 0;
            }

            String descriptor()
            {
/* 347*/        return defaultDescriptor();
            }

            static String defaultDescriptor()
            {
/* 354*/        return "([Ljava/lang/Object;)Ljava/lang/Object;";
            }

            String constDescriptor()
            {
/* 363*/        return defaultConstDescriptor();
            }

            static String defaultConstDescriptor()
            {
/* 370*/        return "([Ljava/lang/Object;)V";
            }

            meter()
            {
            }
}
