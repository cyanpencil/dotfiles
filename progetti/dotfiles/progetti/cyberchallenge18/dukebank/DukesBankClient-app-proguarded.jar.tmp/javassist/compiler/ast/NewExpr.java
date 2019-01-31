// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NewExpr.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.TokenId;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, ASTree, ArrayInit, Visitor

public class NewExpr extends ASTList
    implements TokenId
{

            public NewExpr(ASTList astlist, ASTList astlist1)
            {
/*  30*/        super(astlist, new ASTList(astlist1));
/*  31*/        newArray = false;
/*  32*/        arrayType = 307;
            }

            public NewExpr(int i, ASTList astlist, ArrayInit arrayinit)
            {
/*  36*/        super(null, new ASTList(astlist));
/*  37*/        newArray = true;
/*  38*/        arrayType = i;
/*  39*/        if(arrayinit != null)
/*  40*/            append(this, arrayinit);
            }

            public static NewExpr makeObjectArray(ASTList astlist, ASTList astlist1, ArrayInit arrayinit)
            {
/*  45*/        (astlist = new NewExpr(astlist, astlist1)).newArray = true;
/*  47*/        if(arrayinit != null)
/*  48*/            append(astlist, arrayinit);
/*  50*/        return astlist;
            }

            public boolean isArray()
            {
/*  53*/        return newArray;
            }

            public int getArrayType()
            {
/*  57*/        return arrayType;
            }

            public ASTList getClassName()
            {
/*  59*/        return (ASTList)getLeft();
            }

            public ASTList getArguments()
            {
/*  61*/        return (ASTList)getRight().getLeft();
            }

            public ASTList getArraySize()
            {
/*  63*/        return getArguments();
            }

            public ArrayInit getInitializer()
            {
                ASTree astree;
/*  66*/        if((astree = getRight().getRight()) == null)
/*  68*/            return null;
/*  70*/        else
/*  70*/            return (ArrayInit)astree.getLeft();
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  73*/        visitor.atNewExpr(this);
            }

            protected String getTag()
            {
/*  76*/        if(newArray)
/*  76*/            return "new[]";
/*  76*/        else
/*  76*/            return "new";
            }

            protected boolean newArray;
            protected int arrayType;
}
