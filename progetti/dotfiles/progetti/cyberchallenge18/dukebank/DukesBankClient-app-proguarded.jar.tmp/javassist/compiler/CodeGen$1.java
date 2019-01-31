// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeGen.java

package javassist.compiler;

import javassist.bytecode.Bytecode;

// Referenced classes of package javassist.compiler:
//            CodeGen

class turnHook extends turnHook
{

            protected boolean doit(Bytecode bytecode, int i)
            {
/* 659*/        bytecode.addAload(val$var);
/* 660*/        bytecode.addOpcode(195);
/* 661*/        return false;
            }

            final int val$var;
            final CodeGen this$0;

            turnHook(int i)
            {
/* 657*/        this$0 = final_codegen;
/* 657*/        val$var = i;
/* 657*/        super(CodeGen.this);
            }
}
