// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FieldDecl.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, Declarator, Visitor, ASTree

public class FieldDecl extends ASTList
{

            public FieldDecl(ASTree astree, ASTList astlist)
            {
/*  23*/        super(astree, astlist);
            }

            public ASTList getModifiers()
            {
/*  26*/        return (ASTList)getLeft();
            }

            public Declarator getDeclarator()
            {
/*  28*/        return (Declarator)tail().head();
            }

            public ASTree getInit()
            {
/*  30*/        return sublist(2).head();
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  33*/        visitor.atFieldDecl(this);
            }
}
