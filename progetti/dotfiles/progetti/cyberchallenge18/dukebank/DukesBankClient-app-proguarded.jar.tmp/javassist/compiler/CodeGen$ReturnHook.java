// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeGen.java

package javassist.compiler;

import javassist.bytecode.Bytecode;

// Referenced classes of package javassist.compiler:
//            CodeGen

public static abstract class 
{

            protected abstract boolean doit(Bytecode bytecode, int i);

            protected void remove(CodeGen codegen)
            {
/*  70*/        codegen.returnHooks = next;
            }

            next next;

            protected (CodeGen codegen)
            {
/*  65*/        next = codegen.returnHooks;
/*  66*/        codegen.returnHooks = this;
            }
}
