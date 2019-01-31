// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ArrayInit.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, Visitor, ASTree

public class ArrayInit extends ASTList
{

            public ArrayInit(ASTree astree)
            {
/*  26*/        super(astree);
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  29*/        visitor.atArrayInit(this);
            }

            public String getTag()
            {
/*  31*/        return "array";
            }
}
