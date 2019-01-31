// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BadBytecode.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ConstPool, MethodInfo

public class BadBytecode extends Exception
{

            public BadBytecode(int i)
            {
/*  24*/        super((new StringBuilder("bytecode ")).append(i).toString());
            }

            public BadBytecode(String s)
            {
/*  28*/        super(s);
            }

            public BadBytecode(String s, Throwable throwable)
            {
/*  32*/        super(s, throwable);
            }

            public BadBytecode(MethodInfo methodinfo, Throwable throwable)
            {
/*  36*/        super((new StringBuilder()).append(methodinfo.toString()).append(" in ").append(methodinfo.getConstPool().getClassName()).append(": ").append(throwable.getMessage()).toString(), throwable);
            }
}
