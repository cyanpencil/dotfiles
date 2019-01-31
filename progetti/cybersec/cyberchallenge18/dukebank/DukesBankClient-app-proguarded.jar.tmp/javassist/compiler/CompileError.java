// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CompileError.java

package javassist.compiler;

import javassist.CannotCompileException;
import javassist.NotFoundException;

// Referenced classes of package javassist.compiler:
//            Lex

public class CompileError extends Exception
{

            public CompileError(String s, Lex lex1)
            {
/*  27*/        reason = s;
/*  28*/        lex = lex1;
            }

            public CompileError(String s)
            {
/*  32*/        reason = s;
/*  33*/        lex = null;
            }

            public CompileError(CannotCompileException cannotcompileexception)
            {
/*  37*/        this(cannotcompileexception.getReason());
            }

            public CompileError(NotFoundException notfoundexception)
            {
/*  41*/        this((new StringBuilder("cannot find ")).append(notfoundexception.getMessage()).toString());
            }

            public Lex getLex()
            {
/*  44*/        return lex;
            }

            public String getMessage()
            {
/*  47*/        return reason;
            }

            public String toString()
            {
/*  51*/        return (new StringBuilder("compile error: ")).append(reason).toString();
            }

            private Lex lex;
            private String reason;
}
