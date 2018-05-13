// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SyntaxError.java

package javassist.compiler;


// Referenced classes of package javassist.compiler:
//            CompileError, Lex

public class SyntaxError extends CompileError
{

            public SyntaxError(Lex lex)
            {
/*  21*/        super((new StringBuilder("syntax error near \"")).append(lex.getTextAround()).append("\"").toString(), lex);
            }
}
