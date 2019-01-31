// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NoFieldException.java

package javassist.compiler;

import javassist.compiler.ast.ASTree;

// Referenced classes of package javassist.compiler:
//            CompileError

public class NoFieldException extends CompileError
{

            public NoFieldException(String s, ASTree astree)
            {
/*  28*/        super((new StringBuilder("no such field: ")).append(s).toString());
/*  29*/        fieldName = s;
/*  30*/        expr = astree;
            }

            public String getField()
            {
/*  35*/        return fieldName;
            }

            public ASTree getExpr()
            {
/*  39*/        return expr;
            }

            private String fieldName;
            private ASTree expr;
}
